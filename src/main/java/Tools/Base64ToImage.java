package Tools;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.write;

public class Base64ToImage {
    public void Base64Converison(String base64Image, String newFileName) throws IOException {
        byte[] imageBytes= DatatypeConverter.parseBase64Binary(base64Image);
        BufferedImage img= ImageIO.read(new ByteArrayInputStream(imageBytes));
        File file = new File(newFileName);
        write(img,"png",file);
    }
}
