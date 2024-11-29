package ironcycle.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ironcycle.api.model.admin.AdminRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AdminRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    
	    String path = request.getRequestURI(); // Obtém o caminho da requisição
	    if (path.equals("/auth/register") || path.equals("/auth/login")) {
	        filterChain.doFilter(request, response); // Ignora o filtro para essas rotas
	        return;
	    }
	    
	    var tokenJWT = retrieveToken(request);
	    if (tokenJWT != null) {
	        var subject = tokenService.getSubject(tokenJWT);
	        var user = userRepository.findByUsername(subject);
	        
	        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    }

	    filterChain.doFilter(request, response);
	}

	private String retrieveToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		
		return null;
	}

}
