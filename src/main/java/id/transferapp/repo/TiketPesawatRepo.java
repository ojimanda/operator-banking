package id.transferapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.transferapp.entity.TiketPesawat;

@Repository
public interface TiketPesawatRepo extends JpaRepository<TiketPesawat, Long> {

}
