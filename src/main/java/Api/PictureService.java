package Api;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.directory.InvalidAttributeValueException;
import java.util.List;
import java.util.Optional;

/*funkcjonalnosci api */
@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {this.pictureRepository = pictureRepository; }

    //zwracanie wszystkich obrazkow w DB
    public List<Picture> getAllPictures(){return pictureRepository.findAll(); }

    //dodawanie nowego obrazu do DB
    public void addPicture(Picture picture){pictureRepository.save(picture);}

    //pobieranie wybranego obrazu z DB po id - z przykladowa domyslna obsluga wyjatkow
    public Picture getPicture(long id) throws InvalidAttributeValueException {
        Optional<Picture> PicturebyId= pictureRepository.findPictureById(id);
        if (!PicturebyId.isPresent())throw new InvalidAttributeValueException("brak obrazka z podanym id");
        return pictureRepository.findById(id).get();
    }

    //usuwanie wybranego obrazu z DB po id
    public void deletePicture(long id){pictureRepository.deleteById(id);}

}
