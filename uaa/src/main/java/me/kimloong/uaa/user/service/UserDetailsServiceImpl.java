package me.kimloong.uaa.user.service;

import me.kimloong.uaa.user.model.User;
import me.kimloong.uaa.user.model.UserDetailsImpl;
import me.kimloong.uaa.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 用户Security Service
 * @author KimLoong
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findOne(username);

        UserDetailsImpl userDetails = new UserDetailsImpl();

        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        userDetails.setAuthorities(Collections.singleton(grantedAuthority));
        return userDetails;
    }
}
