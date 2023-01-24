package id.transferapp.service.operator;

import java.util.List;

import id.transferapp.entity.HistoryTransfer;



public interface TransferHistoryService {
	public List<HistoryTransfer> getAll();
	public void save(HistoryTransfer history);
}
