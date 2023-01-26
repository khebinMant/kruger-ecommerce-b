package krugers.microservicio.auth.authmicroservice.seeders.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import krugers.microservicio.auth.authmicroservice.entity.Address;
import krugers.microservicio.auth.authmicroservice.entity.Role;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.service.user.UserServiceImpl;

@Component
public class AdminSeeder {

    @Autowired
    UserServiceImpl userServiceImpl;

    public void fillAdminsToDB(){

        List<Address> addressesKev =  new ArrayList<>();
        addressesKev.add(new Address(7L,"Pichincha","Quito","Av Hernan Gmoiner","Calle 14",true,4L,"CREATED",new Date(), new Date()));
        addressesKev.add(new Address(8L,"Pichincha","Quito","Es dirección del trabajo","Calle 14",false,4L,"CREATED",new Date(), new Date()));

        List<Address> addressesKen =  new ArrayList<>();
        addressesKen.add(new Address(9L,"Guayas","Guayaquil","Av casa de kenan","Calle 14",true,5L,"CREATED",new Date(), new Date()));
        addressesKen.add(new Address(10L,"Guayas","Guayaquil","Es dirección del trabajo","Calle 14",false,5L,"CREATED",new Date(), new Date()));

        List<Address> addressesJos =  new ArrayList<>();
        addressesJos.add(new Address(11L,"Tungurahua","Ambato","Av casa de Jose Andrés","Calle 14",true,6L,"CREATED",new Date(), new Date()));
        addressesJos.add(new Address(12L,"Tungurahua","Ambato","Es dirección del trabajo","Calle 14",false,6L,"CREATED",new Date(), new Date()));

        userServiceImpl.save(new User(4L,"kevin@admin.com","12345678","Kevin","Mantilla","0986261197",new Date(),new Date(), "https://avatars.githubusercontent.com/u/33032880?v=4",1L,addressesKev, true, Role.ADMIN));
        userServiceImpl.save(new User(5L,"kenan@admin.com","12345678","Kenan","Al-jaber","0954157784",new Date(),new Date(), "https://avatars.githubusercontent.com/u/52118245?v=4",1L,addressesKen, true, Role.ADMIN));
        userServiceImpl.save(new User(6L,"jose@admin.com","12345678","Jose Andres","Gavilanes","0987412236",new Date(),new Date(), "https://avatars.githubusercontent.com/u/76002851?v=4",1L,addressesJos, true, Role.ADMIN));
    
    }
}
