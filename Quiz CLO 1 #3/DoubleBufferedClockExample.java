import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class DoubleBufferedClockExample extends TimerTask {

    private BufferedImageDrawer buffId;
    private BufferedImage bg;
    private double frameSize;
    private double handLength;
    private double handWidth;
    private Rectangle2D.Double clockFrame;
    private Rectangle2D.Double clockHand;
    private AffineTransform singleRotation;
    private AffineTransform accumulatedRotation;
    private AffineTransform singleTranslation;
    private AffineTransform accumulatedTranslation;
    private AffineTransform handTransform;

    DoubleBufferedClockExample(BufferedImageDrawer bid, BufferedImage backGroud, int heigh, int delay) {
        buffId = bid;
        buffId.g2dbi.setStroke(new BasicStroke(3.0f));
        buffId.g2dbi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AffineTransform yUp = new AffineTransform();
        yUp.setToScale(1, -1);

        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(0, heigh);
        yUp.preConcatenate(translate);

        buffId.g2dbi.transform(yUp);
        buffId.g2dbi.setPaint(Color.BLACK);

        bg = backGroud;

        frameSize = 100;
        handLength = 40;
        handWidth = 5;

        clockFrame = new Rectangle2D.Double(-frameSize / 2, -frameSize / 2, frameSize, frameSize);
        clockHand = new Rectangle2D.Double(-handWidth / 2, 0, handWidth, handLength);

        singleRotation = new AffineTransform();
        singleRotation.setToRotation(-delay * Math.PI / 30000);

        accumulatedRotation = new AffineTransform();

        singleTranslation = new AffineTransform();
        singleTranslation.setToTranslation(2, 1);

        accumulatedTranslation = new AffineTransform();
        accumulatedTranslation.setToTranslation(150, 150);

        handTransform = new AffineTransform();
    }

    public void run() {
        handTransform.setTransform(accumulatedRotation);
        handTransform.preConcatenate(accumulatedTranslation);
        buffId.g2dbi.drawImage(bg, 0, 0, null);
        buffId.g2dbi.draw(accumulatedTranslation.createTransformedShape(clockFrame));
        buffId.repaint();
        accumulatedTranslation.preConcatenate(singleTranslation);
        accumulatedRotation.preConcatenate(singleRotation);
    }

    public static void main(String[] args) {
        int width = 780;
        int heigh = 530;
        int delay = 100;
        BufferedImage bi = new BufferedImage(width, heigh, BufferedImage.TYPE_INT_RGB);
        BufferedImage backGroud = new BufferedImage(width, heigh, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2dBackGround = backGroud.createGraphics();
        g2dBackGround.setStroke(new BasicStroke(3.0f));
        g2dBackGround.setPaint(Color.WHITE);
        g2dBackGround.fill(new Rectangle(0,  0 , width, heigh));
        Rectangle2D.Double windowFrame = new Rectangle2D.Double(50,  50, 100, 100);
        g2dBackGround.setPaint(Color.BLACK);
        g2dBackGround.draw(windowFrame);
        BufferedImageDrawer bid = new BufferedImageDrawer(bi, width, heigh);
        DoubleBufferedClockExample dbce = new DoubleBufferedClockExample(bid, backGroud, heigh, delay);

        Timer t = new Timer();
        t.scheduleAtFixedRate(dbce, 0, delay);
    }
}
