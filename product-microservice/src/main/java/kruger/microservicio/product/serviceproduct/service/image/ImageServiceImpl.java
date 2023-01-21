package kruger.microservicio.product.serviceproduct.service.image;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kruger.microservicio.product.serviceproduct.entity.Image;
import kruger.microservicio.product.serviceproduct.repository.ImageRepository;

@Service
public class ImageServiceImpl implements IImageService{

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> listAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image createImage(Image image) {
        image.setCreated(new Date());

        return imageRepository.save(image);
    }

    @Override
    public Image updateImage(Image image) {
        Image imageDB = getImage(image.getId());
        if(imageDB == null){
            return null;
        }
        imageDB.setUri(image.getUri());
        
        return imageRepository.save(imageDB);
    }

    @Override
    public void deleteImage(Long id) {
        Image imageDB = getImage(id);
        imageRepository.delete(imageDB);
    }
    
}
