package id.transferapp.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import id.transferapp.entity.Role;
import id.transferapp.entity.User;
import id.transferapp.repo.RoleRepo;
import id.transferapp.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InitDefaultUserAuth {
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@PostConstruct
	public void index() {
		Role roleOperator = new Role();
		Role roleAdmin = new Role();
		Role roleCustomerService = new Role();
		
		roleOperator.setRole("operator");
		roleAdmin.setRole("admin");
		roleCustomerService.setRole("cs");
		this.roleRepo.save(roleOperator);
		this.roleRepo.save(roleCustomerService);
		this.roleRepo.save(roleAdmin);
		
		List<Role> adminRole = new ArrayList<>();
		List<Role> operatorRole = new ArrayList<>();
		List<Role> csRole = new ArrayList<>();
		adminRole.add(roleAdmin);
		operatorRole.add(roleOperator);
		csRole.add(roleCustomerService);
		
		User userAdmin = new User();
		userAdmin.setUsername("admin");
		userAdmin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
		userAdmin.setRole(roleAdmin);
		
		User userOperator = new User();
		userOperator.setUsername("operator");
		userOperator.setPassword(new BCryptPasswordEncoder().encode("op123"));
		userOperator.setRole(roleOperator);
		
		
		User userCS = new User();
		userCS.setUsername("customerservice");
		userCS.setPassword(new BCryptPasswordEncoder().encode("cs123"));
		userCS.setRole(roleCustomerService);
		
		this.userRepo.save(userAdmin);
		this.userRepo.save(userOperator);
		this.userRepo.save(userCS);
	}

}
