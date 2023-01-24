package id.transferapp.repo;


import org.springframework.data.jpa.repository.JpaRepository;


import id.transferapp.entity.BankProvider;

public interface BankProviderRepo extends JpaRepository<BankProvider, Long>{
	
	BankProvider findByBankname(String bankname);

}
