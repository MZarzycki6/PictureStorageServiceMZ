package Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

// @ https://www.baeldung.com/java-resize-image
public class ThumbnailCreator {

    public void rescaleImage(String imgDirectory) throws IOException {
        Image img = ImageIO.read(new File(imgDirectory))
                .getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH);
        File file= new File("newThumbnail.png");
        ImageIO.write((RenderedImage) img, "png",file);
    }
}


