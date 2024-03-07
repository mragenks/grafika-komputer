import java.awt.*;
import java.awt.geom.*;

/**
 * An example for the use of a GeneralPath to draw a rectangle with rounded corners.
 *
 * @author Frank Klawonn
 *         Last change 07.01.2005
 */
public class GeneralPathRectangle extends Frame {
    // Constructor
    GeneralPathRectangle() {
        // Enables the closing of the window.
        addWindowListener(new MyFinishWindow());
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Use of antialiasing to have nicer lines.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // The lines should have a thickness of 3.0 instead of 1.0.
        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        // The GeneralPath to describe the rectangle with rounded corners.
        GeneralPath gp = new GeneralPath();

        float x = 60;
        float y = 60;
        float width = 130;
        float height = 80;
        float arcWidth = 20;
        float arcHeight = 20;

        gp.moveTo(x, y + arcHeight);
        gp.lineTo(x, y + height - arcHeight);
        gp.quadTo(x, y + height, x + arcWidth, y + height);
        gp.lineTo(x + width - arcWidth, y + height);
        gp.quadTo(x + width, y + height, x + width, y + height - arcHeight);
        gp.lineTo(x + width, y + arcHeight);
        gp.quadTo(x + width, y, x + width - arcWidth, y);
        gp.lineTo(x + arcWidth, y);
        gp.quadTo(x, y, x, y + arcHeight);

        // Draw the rectangle with rounded corners.
        g2d.draw(gp);

        g2d.setStroke(new BasicStroke(1.0f));
        // Draw a coordinate system.
        drawSimpleCoordinateSystem(200, 150, g2d);
    }

    /**
     * Draws a coordinate system (according to the window coordinates).
     *
     * @param xmax x-coordinate to which the x-axis should extend.
     * @param ymax y-coordinate to which the y-axis should extend.
     * @param g2d  Graphics2D object for drawing.
     */
    public static void drawSimpleCoordinateSystem(int xmax, int ymax, Graphics2D g2d) {
        int xOffset = 30;
        int yOffset = 50;
        int step = 20;
        String s;
        // Remember the actual font.
        Font fo = g2d.getFont();
        // Use a small font.
        g2d.setFont(new Font("sansserif", Font.PLAIN, 9));
        // x-axis.
        g2d.drawLine(xOffset, yOffset, xmax, yOffset);
        // Marks and labels for the x-axis.
        for (int i = xOffset + step; i <= xmax; i = i + step) {
            g2d.drawLine(i, yOffset - 2, i, yOffset + 2);
            g2d.drawString(String.valueOf(i), i - 7, yOffset - 7);
        }

        // y-axis.
        g2d.drawLine(xOffset, yOffset, xOffset, ymax);

        // Marks and labels for the y-axis.
        s = "  "; // for indention of numbers < 100
        for (int i = yOffset + step; i <= ymax; i = i + step) {
            g2d.drawLine(xOffset - 2, i, xOffset + 2, i);
            if (i > 99) {
                s = "";
            }
            g2d.drawString(s + String.valueOf(i), xOffset - 25, i + 5);
        }

        // Reset to the original font.
        g2d.setFont(fo);
    }

    public static void main(String[] argv) {
        GeneralPathRectangle f = new GeneralPathRectangle();
        f.setTitle("GeneralPath example with rounded corners");
        f.setSize(250, 200);
        f.setVisible(true);
    }
}
