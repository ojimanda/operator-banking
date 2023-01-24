package id.transferapp.service.operator;

import java.util.List;
import java.util.Optional;

import id.transferapp.entity.Transfer;

public interface TransferService {
	public List<Transfer> getAll();
	public void save(Transfer trf);
	public Optional<Transfer> getTransferById(Long Id);

}
