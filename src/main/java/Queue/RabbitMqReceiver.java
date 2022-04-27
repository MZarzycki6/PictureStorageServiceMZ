package Queue;

import Api.Picture;
import Api.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.directory.InvalidAttributeValueException;
import java.io.IOException;

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
    private final PictureService pictureService;
    @Autowired
    public RabbitMqReceiver(PictureService pictureService) {
        this.pictureService = pictureService;

    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void addPicture(Picture picture) throws IOException {
        pictureService.addPicture(picture);
        logger.info("operacja dodawania obrazu wykonana prawidlowo");
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void deletePicture(long id){
        pictureService.deletePicture(id);
        logger.info("operacja usuwania obrazu wykonana prawidlowo");
    }

}

