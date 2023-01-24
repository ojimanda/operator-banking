package id.transferapp.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.User;
import id.transferapp.repo.UserRepo;

@Service
public class LoginService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	public User findUserbyUsername(String username) {
		return this.userRepo.findByUsername(username);
	}
	
	public void adduser(User user) {
		this.userRepo.save(user);
	}
	
	public List<User> findAll(){
		return this.userRepo.findAll();
	}

}
