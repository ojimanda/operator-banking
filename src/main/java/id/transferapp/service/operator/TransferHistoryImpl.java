package id.transferapp.service.operator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.HistoryTransfer;
import id.transferapp.repo.HistoryTransactionRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferHistoryImpl implements TransferHistoryService{

	@Autowired
	HistoryTransactionRepo historyTransactionRepo;
	
	@Override
	public List<HistoryTransfer> getAll() {
		// TODO Auto-generated method stub
		return this.historyTransactionRepo.findAll();
	}

	@Override
	public void save(HistoryTransfer history) {
		// TODO Auto-generated method stub
		this.historyTransactionRepo.save(history);
	}

}
