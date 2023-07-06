import java.util.ArrayList;

public class MainGame {
    //public variables for this class
    private static int height;
    private static int width;
    /**
     * representation: 
     *  +ve values -> turns left til snake moves on
     *  -1 -> apple
     *  -2 -> border block
     */
    private static int[][] board_pos; 
    private static int snake_length;
    //current position of the head of the snake
    private static int pos_x;
    private static int pos_y;
    //current position of the apple
    private static int aaplpos;
    private static GameFrame f;
    private static KeyInput g;
    //keep the game running?
    private static boolean update = true;

    public static void main(String[] args) throws InterruptedException {
        //initial variables that should be manipulable
        height = 20;
        width = 30;
        int refresh_speed = 90;

        //set init starting pos to middle
        snake_length = 1;
        pos_x = width/2;
        pos_y = height/2;

        //initialise all starting values
        board_pos = new int[height][width];
        board_pos[pos_y][pos_x] = 1;
        aaplpos = place_aapl();

        f = new GameFrame(width,height);
        f.setUpGUI();
        f.showBoard(board_pos);
        g = new KeyInput();
        f.addKeyListener(g);

        //start game
        while(update) {
            Thread.sleep(refresh_speed);
            g.vprev = g.velocity();
            update_board(g.velocity());
            f.showBoard(board_pos);
            // System.out.println(pos_x + " " + pos_y + " " + board_pos[pos_y][pos_x]);
        }
    }

    private static void end_game() {
        update = false;
        f.end_game(snake_length);
        // f.getContentPane().removeAll();
    }

    //check for if the game needs to end
    private static boolean endcondition(int x, int y) {
        //border conditions
        if (x == -1 || x == width) return true;
        if (y == -1 || y == height) return true;
        //hit itself? (check snake has began moving)
        if (board_pos[y][x] > 0 && (g.velocity().x + g.velocity().y != 0)) return true;
        return false;
    } 

    private static void update_board(Direction d) {
        //update head
        pos_x += d.x;
        pos_y += d.y;
        if (endcondition(pos_x,pos_y)) { //game needs to end
            end_game();
        }
        else if (pos_y*width + pos_x == aaplpos) { //snake encountered apple! yay
            snake_length++;
            board_pos[pos_y][pos_x] = snake_length;
            aaplpos = place_aapl();
        }
        else {//nothing fun (just normal motion)
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    if (board_pos[i][j] > 0) {
                        board_pos[i][j]--;
                    } 
                }
            }
            board_pos[pos_y][pos_x] = snake_length;
        }
    }
    //method to randomly place an apple on the board
    private static int place_aapl() {
        ArrayList<Integer> al = new ArrayList<Integer>();
        //list of places with nothing
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if (board_pos[i][j] == 0) al.add(i*width+j);
            }
        }    
        int max = al.size(); // Maximum value of range
        //randomly places apple on the board with nothing
        int v = al.get((int)Math.floor(Math.random() * (max)));

        //place apple on board
        board_pos[v/width][v%width] = -1;

        return v;
    }
}
