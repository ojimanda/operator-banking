package id.transferapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.ProviderCard;
import id.transferapp.repo.ProviderCardRepo;

@Service
public class ProviderCardServiceImpl implements ProviderCardService {

    @Autowired
    ProviderCardRepo providerCardRepo;

    @Override
    public List<ProviderCard> getAllProvider() {
        // TODO Auto-generated method stub
        return providerCardRepo.findAll();
    }

    @Override
    public ProviderCard getProviderById(Long id) {
        // TODO Auto-generated method stub
        return providerCardRepo.findById(id).orElse(null);
    }

    @Override
    public ProviderCard saveProvider(ProviderCard providerCard) {
        // TODO Auto-generated method stub
        return providerCardRepo.save(providerCard);
    }

    @Override
    public void deleteProvider(Long id) {
        // TODO Auto-generated method stub
        providerCardRepo.deleteById(id);
    }

}
