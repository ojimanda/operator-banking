package id.transferapp.service.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.transferapp.entity.Role;


@Service
public class LoginUserDetail implements UserDetailsService{

	@Autowired
	private LoginService loginService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		id.transferapp.entity.User userApp = this.loginService.findUserbyUsername(username);
		List<GrantedAuthority> auths = new ArrayList<>();
		
		if(userApp!=null) {
			Role role = userApp.getRole();
			auths.add(new SimpleGrantedAuthority(role.getRole()));
			UserDetails user = User.withUsername(userApp.getUsername())
					.password(userApp.getPassword())
					.authorities(auths).build();
			return user;
		}
		else {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		
	}
}
