package id.transferapp.auth.handler;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException{
		boolean hasAdminRole = false;
		boolean hasCSRole = false;
		boolean hasOperatorRole = false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority grantedAuthority : authorities) {
			if(grantedAuthority.getAuthority().equals("admin")) {
				hasAdminRole = true;
				break;
			}
			else if(grantedAuthority.getAuthority().equals("operator")) {
				hasOperatorRole = true;
				break;
			}
			else if(grantedAuthority.getAuthority().equals("cs")) {
				hasCSRole = true;
				break;
			}
		}
		if(hasAdminRole) {
			redirectStrategy.sendRedirect(request, response, "/admin");
		}else if(hasOperatorRole) {
			redirectStrategy.sendRedirect(request, response, "/operator");
		}else if(hasCSRole) {
			redirectStrategy.sendRedirect(request, response, "/cs");
		}else {
			throw new IllegalStateException();
		}
	}
}
