package kruger.microservicio.product.serviceproduct.service.image;

import java.util.List;

import kruger.microservicio.product.serviceproduct.entity.Image;

public interface IImageService {

    public List<Image> listAllImages();
    public Image getImage(Long id);

    public Image createImage(Image image);
    public Image updateImage(Image image);
    public void deleteImage(Long id);

}
