package Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.write;

// @ https://www.baeldung.com/java-resize-image
public class ThumbnailCreator {

    public void rescaleImage(String imgDirectory) throws IOException {
       BufferedImage img = new BufferedImage(100,100, BufferedImage.TYPE_INT_RGB);
       img.createGraphics().drawImage(ImageIO.read(new File(imgDirectory))
               .getScaledInstance(100,100,Image.SCALE_SMOOTH),0,0,null);
        File file= new File("newThumbnail.png");
        write(img,"png",file);
    }
}


