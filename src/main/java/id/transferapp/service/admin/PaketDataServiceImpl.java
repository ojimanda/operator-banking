package id.transferapp.service.admin;

import id.transferapp.entity.PaketData;
import id.transferapp.repo.PaketDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaketDataServiceImpl implements PaketDataService{

    @Autowired
    PaketDataRepository paketDataRepository;

    @Override
    public List<PaketData> getAllPaketData() {
        return paketDataRepository.findAll();
    }

    @Override
    public List<PaketData> findPaketDataByProvider(String provider) {
        return paketDataRepository.findByProviderName(provider);
    }

    @Override
    public PaketData savePaketData(PaketData paketData) {
        return paketDataRepository.save(paketData);
    }

    @Override
    public void deletePaketData(Long id) {
        paketDataRepository.deleteById(id);
    }
}
