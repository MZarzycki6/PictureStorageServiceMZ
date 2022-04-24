package Tools;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@ContextConfiguration(classes = {Base64ToImage.class, ImageToBase64.class, ThumbnailCreator.class})
public class ToolsTests {
    @Test
    public void converterToolsTest() throws IOException {

        ThumbnailCreator creator = new ThumbnailCreator();
        creator.rescaleImage("C:\\Users\\zarzy\\Desktop\\praca\\ZadanieProjektowe\\PictureStorageServiceMZ\\src\\main\\resources\\Czekolada.jpg");

        ImageToBase64 imageEncoder = new ImageToBase64();
        Base64ToImage decoder = new Base64ToImage();

        String encoded = imageEncoder.imageConversion("newThumbnail.png");
        decoder.Base64Converison(encoded,"testImage.png");
        File file1 = FileUtils.getFile("newThumbnail.png");
        File file2 = FileUtils.getFile("testImage.png");
        Assertions.assertTrue(FileUtils.contentEquals(file1,file2), "Difference detected");
    }
}


