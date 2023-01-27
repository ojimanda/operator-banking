package id.transferapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.transferapp.entity.Tagihan;

@Repository
public interface TagihanRepo extends JpaRepository<Tagihan, Long> {

    List<Tagihan> findByJenisTagihan(String jenisTagihan);
    void deleteByNoTagihan(String noTagihan);
}
