import java.awt.*;
// import java.awt.geom.*;

public class StrokingExample extends Frame {
    StrokingExample() {
        addWindowListener(new MyFinishWindow());
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        BasicStroke bsThickLine = new BasicStroke(3.0f);
        g2d.setStroke(bsThickLine);
        g2d.drawLine(30, 50, 30, 250);

        float[] dashPattern;
        float dashPhase = 0.0f;

        dashPattern = new float[10];
        dashPattern[0] = 4.0f;
        dashPattern[1] = 5.0f;
        dashPattern[2] = 8.0f;
        dashPattern[3] = 5.0f;
        dashPattern[4] = 12.0f;
        dashPattern[5] = 5.0f;
        dashPattern[6] = 16.0f;
        dashPattern[7] = 5.0f;
        dashPattern[8] = 20.0f;
        dashPattern[9] = 5.0f;

        BasicStroke bsIncreasing = new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 2.0f,
                dashPattern, dashPhase);
        g2d.setStroke(bsIncreasing);
        g2d.drawLine(60, 50, 60, 250);

        dashPattern = new float[2];
        dashPattern[0] = 20;
        dashPattern[1] = 10;

        BasicStroke bsDashed = new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 2.0f,
                dashPattern, dashPhase);
        g2d.setStroke(bsDashed);

        g2d.drawLine(120, 50, 120, 250);

        g2d.drawLine(150, 50, 350, 250);

        dashPhase = 10.0f;

        BasicStroke bsDashedShifted = new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 2.0f,
                dashPattern, dashPhase);

        g2d.setStroke(bsDashedShifted);
        g2d.drawLine(90, 50, 90, 250);
    }

    public static void main(String[] args) {
        StrokingExample f = new StrokingExample();
        f.setTitle("Stroking Example");
        f.setSize(400, 300);
        f.setVisible(true);
    }
}
