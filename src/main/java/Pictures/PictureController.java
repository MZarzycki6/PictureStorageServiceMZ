package Pictures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class PictureController {

	private final PictureService pictureService;

	@Autowired
	public PictureController(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	//testowy zwrot obrazu - do implementacji zwrot wszystkich obiektów w bazie
	@GetMapping("/getAllPictures")
	public List<Picture> getAllPictures(){
		return pictureService.getAllPictures();
	}



}
