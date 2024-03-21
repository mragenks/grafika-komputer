import java.awt.*;

public class ImageLoadingExample extends Frame{
    private Image theImage;

    public ImageLoadingExample(){
        addWindowListener(new MyFinishWindow());

        theImage = new javax.swing.ImageIcon("meme cocote.JPEG").getImage();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(theImage, 50, 50, null);
    }

    public static void main(String[] args) {
        ImageLoadingExample f = new ImageLoadingExample();
        f.setTitle("Image Loading");
        f.setSize(800, 800);
        f.setVisible(true);
    }
}
