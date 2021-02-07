package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.InnerUser;
import com.stankin.machine.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public InnerUser save(@NotNull InnerUser innerUser){
        log.trace(">>save... user={}", innerUser);
       Optional<InnerUser> userOptional = userRepository.findById(innerUser.getId());
        if(userOptional.isPresent()){
            log.trace("update existing user");
            innerUser.setUpdatedAt(new Date());
            return userRepository.save(innerUser);
        }
        log.trace("create new user");
        innerUser.setId(null);
        innerUser.setCreatedAt(new Date());
        innerUser.setUpdatedAt(new Date());
        return userRepository.save(innerUser);
    }

    public List<InnerUser> findAll(){
        log.trace(">>findAll...");
        return userRepository.findAll();
    }

    public Optional<InnerUser> findById(@NotNull Long id){
        log.trace(">>findById... id={}", id);
        return userRepository.findById(id);
    }

    public void delete(@NotNull InnerUser innerUser){
        log.trace(">>delete... user={}", innerUser);
        userRepository.delete(innerUser);
    }

    public Optional<InnerUser> findByLogin(String login){
        InnerUser innerUser = userRepository.findByLogin(login);
        return Optional.of(innerUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InnerUser innerUser =  userRepository.findByLogin(username);
        if(innerUser == null){
            throw new UsernameNotFoundException("Unknown user " + username);
        }
        UserDetails userDetails = User.builder()
                .username(innerUser.getLogin())
                .password(innerUser.getPassword())
                .roles(innerUser.getRole()).build();
        return userDetails;
    }
}
