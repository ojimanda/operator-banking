package id.transferapp.service.admin;

import id.transferapp.entity.PaketData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaketDataService {

    List<PaketData> getAllPaketData();
    List<PaketData> findPaketDataByProvider(String provider);
    PaketData savePaketData(PaketData paketData);
    void deletePaketData(Long id);
}
