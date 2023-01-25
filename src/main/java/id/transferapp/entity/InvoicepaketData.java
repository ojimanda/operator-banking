package id.transferapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "invoicepaketdata")
public class InvoicepaketData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String noRek;
	
	private String namaProvider;
	
	private String jenisPaket;
	
	private String phoneNum;
	
	private double price;
}
