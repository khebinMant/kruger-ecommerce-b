package krugers.microservicio.auth.authmicroservice.seeders.customer;

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
public class CustomerSeeder {
   
    @Autowired
    UserServiceImpl userServiceImpl;

    public void fillCustomersToDB(){


        List<Address> addressesKev =  new ArrayList<>();
        addressesKev.add(new Address(1L,"Pichincha","Quito","Av Hernan Gmoiner","Calle 14",true,1L,"CREATED",new Date(), new Date()));
        addressesKev.add(new Address(2L,"Pichincha","Quito","Es dirección del trabajo","Calle 14",false,1L,"CREATED",new Date(), new Date()));

        List<Address> addressesKen =  new ArrayList<>();
        addressesKen.add(new Address(3L,"Guayas","Guayaquil","Av casa de kenan","Calle 14",true,2L,"CREATED",new Date(), new Date()));
        addressesKen.add(new Address(4L,"Guayas","Guayaquil","Es dirección del trabajo","Calle 14",false,2L,"CREATED",new Date(), new Date()));

        List<Address> addressesJos =  new ArrayList<>();
        addressesJos.add(new Address(5L,"Tungurahua","Ambato","Av casa de Jose Andrés","Calle 14",true,3L,"CREATED",new Date(), new Date()));
        addressesJos.add(new Address(6L,"Tungurahua","Ambato","Es dirección del trabajo","Calle 14",false,3L,"CREATED",new Date(), new Date()));


        userServiceImpl.save(new User(1L,"kevin@customer.com","12345678","Kevin","Mantilla","0986261197",new Date(),new Date(), "https://avatars.githubusercontent.com/u/33032880?v=4",1L,addressesKev, true, Role.CUSTOMER));
        userServiceImpl.save(new User(2L,"kenan@customer.com","12345678","Kenan","Al-jaber","0954157784",new Date(),new Date(), "https://avatars.githubusercontent.com/u/52118245?v=4",1L,addressesKen, true, Role.CUSTOMER));
        userServiceImpl.save(new User(3L,"jose@customer.com","12345678","Jose Andres","Gavilanes","0987412236",new Date(),new Date(), "https://avatars.githubusercontent.com/u/76002851?v=4",1L,addressesJos, true, Role.CUSTOMER));
    
    } 
}
