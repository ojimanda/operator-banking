package id.transferapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.TiketPesawat;
import id.transferapp.repo.TiketPesawatRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TiketPesawatServiceImpl implements TiketPesawatService {

    @Autowired
    TiketPesawatRepo tiketPesawatRepo;

    @Override
    public List<TiketPesawat> getAllTiketPesawat() {
        // TODO Auto-generated method stub
        return tiketPesawatRepo.findAll();
    }

    @Override
    public TiketPesawat addTiketPesawat(TiketPesawat tiketPesawat) {
        // TODO Auto-generated method stub
        return tiketPesawatRepo.save(tiketPesawat);
    }

    @Override
    public void deleteTiketPesawat(Long id) {
        // TODO Auto-generated method stub
        tiketPesawatRepo.deleteById(id);
    }

}
