package id.transferapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.Tagihan;
import id.transferapp.repo.TagihanRepo;

@Service
public class TagihanServiceImpl implements TagihanService {

    @Autowired
    TagihanRepo tagihanRepo;

    @Override
    public List<Tagihan> getAllTagihan() {
        // TODO Auto-generated method stub
        return tagihanRepo.findAll();
    }

    @Override
    public List<Tagihan> getTagihanByJenisTagihan(String jenisTagihan) {
        // TODO Auto-generated method stub
        return tagihanRepo.findByJenisTagihan(jenisTagihan);
    }

    @Override
    public Tagihan getTagihanById(Long id) {
        // TODO Auto-generated method stub
        return tagihanRepo.findById(id).orElse(null);
    }

    @Override
    public Tagihan saveTagihan(Tagihan tagihan) {
        // TODO Auto-generated method stub
        return tagihanRepo.save(tagihan);
    }

    @Override
    public void deleteTagihan(Long id) {
        // TODO Auto-generated method stub
        tagihanRepo.deleteById(id);
    }

}
