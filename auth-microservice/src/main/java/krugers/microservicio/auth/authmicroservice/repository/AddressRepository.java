package krugers.microservicio.auth.authmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import krugers.microservicio.auth.authmicroservice.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    public List<Address> findByUserId(Long id);
}
