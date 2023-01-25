package id.transferapp.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import id.transferapp.entity.ProviderCard;

@Service
public interface ProviderCardService {

    List<ProviderCard> getAllProvider();

    ProviderCard getProviderById(Long id);

    ProviderCard saveProvider(ProviderCard providerCard);

    void deleteProvider(Long id);
}
