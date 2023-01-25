package id.transferapp.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "invoicetiketpesawat")
public class InvoiceTiketPesawat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Date tglBerangkat;
	
	private int jumlahTiket;
	
	private double harga;
	
	private String noRek;
	
	private String email;
	

}