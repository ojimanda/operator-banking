package id.transferapp.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "nasabah")
public class Nasabah {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull @NotEmpty @NotBlank
	private String namaLengkap;
	@NotNull @NotEmpty @NotBlank
	private String noIdentitas;
	@NotNull @NotEmpty @NotBlank
	private String tipeIdentitas;
	@NotNull @NotEmpty @NotBlank
	private String email;
	@NotNull @NotEmpty @NotBlank
	private String phoneNum;
	@JsonIgnore
	@OneToMany(mappedBy = "nasabah", cascade = CascadeType.ALL)
	private List<Rekening> reks;
}
