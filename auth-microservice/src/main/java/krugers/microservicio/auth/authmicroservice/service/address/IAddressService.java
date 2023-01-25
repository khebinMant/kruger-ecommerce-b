package krugers.microservicio.auth.authmicroservice.service.address;

import java.util.List;

import krugers.microservicio.auth.authmicroservice.entity.Address;


public interface IAddressService {

    public List<Address> findAddresses();
    public Address createAddress(Address address, Long userId);
    public Address updateAddress(Address address);//Me sirve para actualizar también la matriz del customer
    public Address deleteAddress(Long id);
    public Address getAddress(Long id);
    public List<Address> findByUserId(Long userId);
    public Address updateMatrizAddress(Address address, Boolean isMatriz);
}
