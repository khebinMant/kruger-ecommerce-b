package krugers.microservicio.auth.authmicroservice.service.address;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import krugers.microservicio.auth.authmicroservice.entity.Address;
import krugers.microservicio.auth.authmicroservice.repository.AddressRepository;

@Service
public class AddressServiceImpl implements IAddressService{

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> findAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address createAddress(Address address, Long userId) {
        address.setUserId(userId);
        address.setStatus("CREATED");
        address.setCreateAt(new Date());
        address.setUpdateAt(new Date());
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        Address addressDB = getAddress(address.getId());
        if(addressDB == null){
            return null;
        }
        addressDB.setCity(address.getCity());
        addressDB.setProvince(address.getProvince());
        addressDB.setStreet(address.getStreet());
        addressDB.setAddress(address.getAddress());
        addressDB.setUpdateAt(new Date());
        

        return addressRepository.save(addressDB);
    }

    @Override
    public Address deleteAddress(Long id) {
        Address addressDB = getAddress(id);
        if(addressDB ==  null){
            return null;
        }
        addressDB.setStatus("DELETED");
        return addressRepository.save(addressDB);
    }

    @Override
    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public Address updateMatrizAddress(Address address, Boolean isMatriz) {
        Address addressDB = getAddress(address.getId());
        addressDB.setIsMatriz(isMatriz);
        addressRepository.save(addressDB);
        return addressDB;
    }

  
}
