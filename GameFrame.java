import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GameFrame extends JFrame {

    private int width;
    private int height;
    Container c = getContentPane();

    public GameFrame (int w, int h) {
        width = w;
        height = h;
        setLocation(300,30);
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setUpGUI() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // add(new JLabel(""+(i*size+j)));
                JPanel pn = new JPanel();
                pn.setBorder(new LineBorder(Color.black,1,true));

                c.add(pn);
            }
        }
        c.setLayout(new GridLayout(height,width));
        setSize(width*18,height*18+20);
        setVisible(true);
    }

    public void showBoard(int[][] a) {
        // System.out.println(c.getComponentCount());
        int cnt = 0;
        for (Component pn : c.getComponents()) {
            if (pn instanceof JPanel) {
                int i = cnt/width;
                int j = cnt%width;

                if (a[i][j] == -1) {
                    pn.setBackground(Color.RED);
                }
                else if (a[i][j] > 0) {
                    pn.setBackground(Color.GREEN);
                }
                else {
                    pn.setBackground(Color.WHITE);
                }
                
                cnt++;
            }
        }
        // System.out.println(cnt);
        // revalidate();
        repaint();
    }

    public void end_game(int score) {
        JLabel l = new JLabel();
        l.setText("<html><center>You died...<br>Final Score: " + score + "</center></html>");
        l.setBackground(Color.WHITE);
        l.setOpaque(true);
        l.setBorder(new LineBorder(Color.black,2,true));
        l.setFont(new Font("Arial", Font.PLAIN, 22));

        ((JPanel)getGlassPane()).add(l);
        getGlassPane().setVisible(true);
    }   
}