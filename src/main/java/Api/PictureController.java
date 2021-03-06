package Api;

import Queue.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.naming.directory.InvalidAttributeValueException;
import java.util.List;

@SpringBootApplication
@ComponentScan({"Api", "Queue","Tools","Security"})
@RestController
public class PictureController {

	private final PictureService pictureService;
	private final RabbitMqSender rabbitMqSender;

	@Autowired
	public PictureController(PictureService pictureService, RabbitMqSender rabbitMqSender) {
		this.pictureService = pictureService;
		this.rabbitMqSender = rabbitMqSender;
	}

	@Value("Wyslano żądanie do kolejki")
	private String message;

	//wyswietlanie wszystkiego w DB
	@GetMapping(path="/getAllPictures")
	@RolesAllowed("admin")
	public List<Picture> getAllPictures(){
		return pictureService.getAllPictures();
	}

	//dodawanie nowego obrazka z wykorzystaniem rabbitmq jako brokera pomiedzy metodą rest a serwisem
	@PostMapping(path="/addPicture")
	@RolesAllowed({"user","admin"})
	public String addPicture(@RequestBody Picture picture){
		rabbitMqSender.send(picture);
	return message;}

	//pobieranie obrazu z DB po id
	@GetMapping(path="/getPicture")
	@RolesAllowed({"user,admin"})
	public Picture getPicture(@RequestParam long id) throws InvalidAttributeValueException {
		return pictureService.getPicture(id);}

	//usuwanie obrazu z DB po id
	@DeleteMapping(path="/deletePicture")
	@RolesAllowed({"user,admin"})
	public ResponseEntity deletePicture(@RequestParam long id){
		rabbitMqSender.delete(id);
		return ResponseEntity.ok().body("Operacja wykonana prawidłowo - usunięto obraz o id "+id);
	}

	// TODO: tworzenie linka dostepu do wybranego obrazka


}
