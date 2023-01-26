package id.transferapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.transferapp.entity.SellProduct;

@Repository
public interface SellProductRepo extends JpaRepository<SellProduct, Long> {

}
