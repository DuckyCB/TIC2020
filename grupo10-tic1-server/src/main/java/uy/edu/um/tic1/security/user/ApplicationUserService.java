package uy.edu.um.tic1.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.users.AppUser;
import uy.edu.um.tic1.entities.users.Client;
import uy.edu.um.tic1.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;


@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username))
                );
    }

    public AppUser getAppUser(String username){
        Optional<AppUser> optionalAppUser = userRepository.findByUsername(username);

        if (optionalAppUser.isPresent())
            return optionalAppUser.get();

        return null;
    }

    public AppUser getUserFromHeader(String header){
        String credentials = null;
        String[] values = null;
        if (header != null && header.toLowerCase().startsWith("basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = header.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            values = credentials.split(":", 2);
        }

        return this.getAppUser(values[0]);
    }

    public void save(AppUser appUser) {

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.initUser();
        userRepository.save(appUser);

    }
}
