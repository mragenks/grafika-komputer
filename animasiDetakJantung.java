import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class animasiDetakJantung extends JFrame {
    private int x = 50;
    private int y = 250;
    private int direction = 1;
    private Timer timer;

    public animasiDetakJantung() {
        setTitle("Animasi Detak Jantung");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHeart();
                repaint();
            }
        });

        timer.start();
    }

    private void moveHeart() {
        x += direction * 2;

        if (x <= 50 || x >= 750) {
            direction *= -1;
        }

        y = 250 + (int) (10 * Math.sin((System.currentTimeMillis() % 2000) * 0.002 * Math.PI));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawHeart(g);
    }

    private void drawHeart(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        g2d.fillArc(x, y - 20, 40, 40, 0, 180);
        g2d.fillArc(x + 40, y - 20, 40, 40, 0, 180);
        g2d.fillRect(x, y - 10, 80, 40);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new animasiDetakJantung().setVisible(true);
            }
        });
    }
}
