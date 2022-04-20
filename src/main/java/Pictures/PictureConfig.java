package Pictures;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PictureConfig {
    @Bean
    CommandLineRunner commandLineRunner(PictureRepository repository){
        return args ->{
            Picture first = new Picture(1L,"kwiat","kwiatek","1b2b3b4n");
            Picture second = new Picture (2L,"kaczka","ptaszydło","0n98v7c");
            repository.saveAll(List.of(first,second));
        };

    }
}
