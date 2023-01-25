package id.transferapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import id.transferapp.entity.PaketData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaketDataRepository extends JpaRepository<PaketData, Long> {

    List<PaketData> findByProviderName(String providerName);

}
