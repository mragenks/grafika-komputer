import java.awt.*;
import java.awt.image.*;
import java.io.FileOutputStream;

public class ImageSavingExample extends Frame{
    private BufferedImage theImage;

    public ImageSavingExample(){
        addWindowListener(new MyFinishWindow());
        
        theImage = new BufferedImage(200, 300, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2dImage = theImage.createGraphics();

        g2dImage.setPaint(Color.RED);
        g2dImage.drawString("Try Saving", 30, 230);

        try {
            FileOutputStream fos = new FileOutputStream("test.PNG");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(theImage, 50, 50, null);
    }

    public static void main(String[] args) {
        ImageSavingExample f = new ImageSavingExample();
        f.setTitle("Save");
        f.setSize(600, 600);
        f.setVisible(true);
    }
}
