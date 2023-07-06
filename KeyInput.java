import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Direction {
    int x;
    int y;
    public Direction(int lr, int ud) {
        x = lr;
        y = ud;
    }

    // @Override
    public boolean equals(Direction o) {
        return this.x == o.x && this.y == o.y;
    }   
}

public class KeyInput extends KeyAdapter {
    Direction vel = new Direction(0,0);
    Direction vprev = new Direction(0,0);
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP && vprev.y == 0) {
            vel = new Direction(0,-1);
        }
        else if (keyCode == KeyEvent.VK_DOWN 
        && vprev.y == 0) {
            vel = new Direction(0,1);
        }
        else if (keyCode == KeyEvent.VK_LEFT && vprev.x == 0) {
            vel = new Direction(-1,0);
        }
        else if (keyCode == KeyEvent.VK_RIGHT && vprev.x == 0) {
            vel = new Direction(1,0);
        }
    }

    public Direction velocity() {
        return vel;
    }
}