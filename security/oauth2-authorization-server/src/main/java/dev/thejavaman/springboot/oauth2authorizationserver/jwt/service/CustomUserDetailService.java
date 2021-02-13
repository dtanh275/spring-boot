package dev.thejavaman.springboot.oauth2authorizationserver.jwt.service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dev.thejavaman.springboot.oauth2authorizationserver.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!StringUtils.hasLength(email)) {
            throw new UsernameNotFoundException("Not found user " + email);
        }

        var user = userRepository.findByEmail(email)
                                 .orElseThrow(() -> new UsernameNotFoundException("Not found user " + email));

        return new User(user.getEmail(),
                        user.getPassword(),
                        user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                            .collect(Collectors.toList()));
    }
}
