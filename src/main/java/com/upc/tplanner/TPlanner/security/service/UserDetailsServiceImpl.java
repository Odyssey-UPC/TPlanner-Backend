package com.upc.tplanner.TPlanner.security.service;

import com.upc.tplanner.TPlanner.user.model.User;
import com.upc.tplanner.TPlanner.user.repository.UserRepository;
import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetails = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_".concat(userDetails.getRole())));
        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),
                userDetails.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
