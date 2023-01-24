package id.transferapp.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import id.transferapp.entity.Rekening;

public interface RekeningRepo extends JpaRepository<Rekening, Long>{

	Rekening findByNoRek(String rekening);
}
