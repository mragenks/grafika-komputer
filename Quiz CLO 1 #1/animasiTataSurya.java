import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class animasiTataSurya extends JFrame {
    private static final int lebar = 800;
    private static final int tinggi = 600;
    private static final int radiusMatahari = 50;
    private static final int radiusPlanet = 10;
    private static final int radiusOrbit = 200;

    private double sudutPlanet = 0;

    public animasiTataSurya() {
        setTitle("Animasi Tata Surya");
        setSize(lebar, tinggi);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();
    }

    private void update() {
        sudutPlanet += 2 * Math.PI / 365;
        if (sudutPlanet >= 2 * Math.PI) {
            sudutPlanet -= 2 * Math.PI;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        gambarTataSurya((Graphics2D) g);
    }

    private void gambarTataSurya(Graphics2D g) {
        g.setColor(Color.orange);
        g.fillOval(lebar / 2 - radiusMatahari, tinggi / 2 - radiusMatahari, 2 * radiusMatahari, 2 * radiusMatahari);

        int planetX = (int) (lebar / 2 + radiusOrbit * Math.cos(sudutPlanet));
        int planetY = (int) (tinggi / 2 - radiusOrbit * Math.sin(sudutPlanet));

        g.setColor(Color.blue);
        g.fillOval(planetX - radiusPlanet, planetY - radiusPlanet, 2 * radiusPlanet, 2 * radiusPlanet);

        // Draw a line from the sun to the planet
        g.setColor(Color.WHITE);
        g.drawLine(lebar / 2, tinggi / 2, planetX, planetY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                animasiTataSurya solarSystem = new animasiTataSurya();
                solarSystem.setVisible(true);
            }
        });
    }
}
