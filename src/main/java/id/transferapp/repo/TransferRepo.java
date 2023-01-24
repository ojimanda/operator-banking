package id.transferapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import id.transferapp.entity.Transfer;

public interface TransferRepo extends JpaRepository<Transfer, Long>{

}
