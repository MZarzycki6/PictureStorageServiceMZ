import Api.PictureController;
import Tools.Base64ToImage;
import Tools.ImageToBase64;
import Tools.ThumbnailCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;

@EnableCaching
public class StartApplication {
    public static void main(String[] args){
        SpringApplication.run(PictureController.class, args);
    }
}
