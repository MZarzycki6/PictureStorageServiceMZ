package Tools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

//@ https://www.baeldung.com/java-base64-image-string
public class ImageConverter {

    static public String imageConversion() throws IOException {
        String filePath= "C:\\Users\\zarzy\\Desktop\\praca\\ZadanieProjektowe\\projektRekrutacyjny\\src\\main\\resources\\Kwiaty.jpg";
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }
}
