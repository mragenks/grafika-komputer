import java.awt.*;
import java.awt.image.*;

public class BufferedImageDrawer extends Frame{
    public BufferedImage bi;
    public Graphics2D g2dbi;
    private Graphics2D g2d;

    public BufferedImageDrawer(BufferedImage buffIm, int width, int heigh){
        bi = buffIm;
        g2dbi = bi.createGraphics();
        addWindowListener(new MyFinishWindow());
        this.setTitle("Double-Buffered");
        this.setSize(width,heigh);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        update(g);
    }

    public void update(Graphics g){
        g2d = (Graphics2D) g;
        g2d.drawImage(bi, 0, 0, null);
    }
}
