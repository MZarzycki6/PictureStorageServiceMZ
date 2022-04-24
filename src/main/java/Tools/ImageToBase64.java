package Tools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

//@ https://www.baeldung.com/java-base64-image-string
public class ImageToBase64 {

    public String imageConversion(String filename) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filename));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        System.out.println(encodedString);
        return encodedString;
    }
}
