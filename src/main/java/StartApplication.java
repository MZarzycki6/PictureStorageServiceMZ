import Api.PictureController;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(PictureController.class, args);
    }
}
