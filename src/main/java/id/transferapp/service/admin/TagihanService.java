package id.transferapp.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import id.transferapp.entity.Tagihan;

@Service
public interface TagihanService {

    List<Tagihan> getAllTagihan();

    List<Tagihan> getTagihanByJenisTagihan(String jenisTagihan);

    Tagihan getTagihanById(Long id);

    Tagihan saveTagihan(Tagihan tagihan);

    void deleteTagihan(Long id);
}
