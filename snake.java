import javax.swing.;
import java.awt.;
import java.awt.event.;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private final int SIZE = 30;
    private final int DOT_SIZE = 20;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private ArrayList<Integer> x = new ArrayList<Integer>();
    private ArrayList<Integer> y = new ArrayList<Integer>();

    private ArrayList<Integer> apple_x = new ArrayList<Integer>();
    private ArrayList<Integer> apple_y = new ArrayList<Integer>();

    private int dots;
    private int apple_x; 
    private int apple_y;
    private char direction;

    private boolean inGame = true;
    private Timer timer;

    public SnakeGame() {
        addKeyListener(this);
        setPreferredSize(new Dimension(SIZE DOT_SIZE, SIZE * DOT_SIZE));
        setBackground(Color.black);
        setFocusable(true);
        initGame();
    }

    public void initGame() {
        dots = 3;
        direction = 'R';

        for(int i = 0; i < dots; i++) {
            x.add(SIZE / 2 - i);
            y.add(SIZE / 2);
        }

        locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void locateApple() {
        Random rand = new Random();
        int r = rand.nextInt(RAND_POS);
        apple_x = r * DOT_SIZE;

        r = rand.nextInt(RAND_POS);
        apple_y = r * DOT_SIZE;
    }

    public void checkApple() {
        if(x.get(0) == apple_x && y.get(0) == apple_y) {
            dots++;
            locateApple();
        }
    }

    public void checkCollision() {
        for(int i = dots; i > 0; i--) {
            if(i > 4 && x.get(0) == x.get(i) && y.get(0) == y.get(i)) {
                inGame = false;
            }
        }

        if(x.get(0) >= SIZE * DOT_SIZE || x.get
VAYU CHAT-GPT
BOT
 — Yesterday at 12:18 PM
(0) < 0  y.get(0) >= SIZE * DOT_SIZE  y.get(0) < 0) {
            inGame = false;
        }

        if(!inGame) {
            timer.stop();
        }
    }

    public void move() {
        for(int z = dots; z > 0; z--) {
            x.set(z, x.get(z - 1));
            y.set(z, y.get(z - 1));
        }

        if(direction == 'U') {
            y.set(0, y.get(0) - DOT_SIZE);
        }

        if(direction == 'D') {
            y.set(0, y.get(0) + DOT_SIZE);
        }

        if(direction == 'L') {
            x.set(0, x.get(0) - DOT_SIZE);
        }

        if(direction == 'R') {
            x.set(0, x.get(0) + DOT_SIZE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g) {
        if(inGame) {
            g.setColor(Color.red);
            g.fillRect(apple_x, apple_y, DOT_SIZE, DOT_SIZE);

            for (int i = 0; i < dots; i++) {
                if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x.get(i), y.get(i), DOT_SIZE, DOT_SIZE);
                } else {
                    g.setColor(Color.yellow);
                    g.fillRect(x.get(i), y.get(i), DOT_SIZE, DOT_SIZE);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (SIZE * DOT_SIZE - metr.stringWidth(msg)) / 2, SIZE * DOT_SIZE / 2);
    }

    @Override
    public void keyPressed(
VAYU CHAT-GPT
BOT
 — Yesterday at 12:18 PM
KeyEvent e) {
        int key = e.getKeyCode();

        if((key == KeyEvent.VK_LEFT) && (direction != 'R')) {
            direction = 'L';
        }

        if((key == KeyEvent.VK_RIGHT) && (direction != 'L')) {
            direction = 'R';
        }

        if((key == KeyEvent.VK_UP) && (direction != 'D')) {
            direction = 'U';
        }

        if((key == KeyEvent.VK_DOWN) && (direction != 'U')) {
            direction = 'D';
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
