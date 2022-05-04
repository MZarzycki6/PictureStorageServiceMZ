package Api;

import Tools.Base64ToImage;
import Tools.ImageToBase64;
import Tools.ThumbnailCreator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.directory.InvalidAttributeValueException;
import javax.transaction.Transactional;
import java.io.IOException;
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
    public void addPicture(Picture picture) throws IOException {
        ImageToBase64 encoder = new ImageToBase64();
        Base64ToImage decoder = new Base64ToImage();
        ThumbnailCreator creator= new ThumbnailCreator();

        String pictureBase= picture.getEncodedImage();
        decoder.Base64Converison(pictureBase,"convertedPicture.png");
        creator.rescaleImage("convertedPicture.png");
        String encodedThumbnail = encoder.imageConversion("newThumbnail.png");

        Picture newPicture = new Picture();
        newPicture.setName(picture.getName());
        newPicture.setId(picture.getId());
        newPicture.setDescription(picture.getDescription());
        newPicture.setEncodedImage(encodedThumbnail);
        pictureRepository.save(newPicture);}


    //pobieranie wybranego obrazu z DB po id - z przykladowa domyslna obsluga wyjatkow
    @Cacheable("pictures")
    @Transactional
    public Picture getPicture(long id) throws InvalidAttributeValueException {
        Optional<Picture> PicturebyId= pictureRepository.findPictureById(id);
        if (!PicturebyId.isPresent())throw new InvalidAttributeValueException("brak obrazka z podanym id");
        return pictureRepository.findById(id).get();
    }

    //usuwanie wybranego obrazu z DB po id
    public void deletePicture(long id){pictureRepository.deleteById(id);}

}
