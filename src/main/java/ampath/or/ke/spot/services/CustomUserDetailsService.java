package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement logic to retrieve user details from the database based on the given username.
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Create and return a UserDetails object based on your User entity.
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                // You can provide the user's authorities/roles here if applicable.
                Collections.emptyList()
        );
    }
}
