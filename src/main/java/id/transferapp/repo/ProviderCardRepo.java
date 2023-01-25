package id.transferapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.transferapp.entity.ProviderCard;

@Repository
public interface ProviderCardRepo extends JpaRepository<ProviderCard, Long> {
    
}
