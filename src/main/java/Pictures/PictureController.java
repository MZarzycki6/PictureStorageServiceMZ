package Pictures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public void addPicture(@RequestBody Picture picture){pictureService.addPicture(picture);}

	//pobieranie obrazu z DB po id
	@GetMapping(path="/getPicture")
	public Picture getPicture(@RequestParam long id){return pictureService.getPicture(id);}



}
