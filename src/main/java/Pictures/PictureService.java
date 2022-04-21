package Pictures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    //zwracanie wszystkich obrazkow w DB
    public List<Picture> getAllPictures(){
               return pictureRepository.findAll();
    }

    //dodawanie nowego obrazu do DB
    public void addPicture(Picture picture){pictureRepository.save(picture);}
}
