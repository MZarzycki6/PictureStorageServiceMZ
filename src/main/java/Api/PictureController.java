package Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributeValueException;
import java.util.List;

/* controller dla zapytań REST */
@SpringBootApplication
@RestController
public class PictureController {

	private final PictureService pictureService;

	@Autowired
	public PictureController(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	//wyswietlanie wszystkiego w DB
	@GetMapping(path="/getAllPictures")
	public List<Picture> getAllPictures(){
		return pictureService.getAllPictures();
	}

	//dodawanie nowego obrazka
	@PostMapping(path="/addPicture")
	public ResponseEntity addPicture(@RequestBody Picture picture){
		pictureService.addPicture(picture);
	return ResponseEntity.ok().body("Operacja wykonana prawidłowo - obraz dodany");	}

	//pobieranie obrazu z DB po id
	@GetMapping(path="/getPicture")
	public Picture getPicture(@RequestParam long id) throws InvalidAttributeValueException {return pictureService.getPicture(id);}

	//usuwanie obrazu z DB po id
	@DeleteMapping(path="/deletePicture")
	public ResponseEntity deletePicture(@RequestParam long id){
		pictureService.deletePicture(id);
		return ResponseEntity.ok().body("Operacja wykonana prawidłowo - usunięto obraz o id "+id);
	}

	//tworzenie linka dostepu do wybranego obrazka


}
