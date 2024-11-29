package ironcycle.api.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = (Admin) adminRepository.findByUsername(username);
        
        if (admin == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        
        return admin;  // Retorna o usuário encontrado
    }
}