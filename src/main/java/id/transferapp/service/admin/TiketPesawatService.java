package id.transferapp.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import id.transferapp.entity.TiketPesawat;

@Service
public interface TiketPesawatService {

    List<TiketPesawat> getAllTiketPesawat();

    TiketPesawat addTiketPesawat(TiketPesawat tiketPesawat);

    void deleteTiketPesawat(Long id);
}
