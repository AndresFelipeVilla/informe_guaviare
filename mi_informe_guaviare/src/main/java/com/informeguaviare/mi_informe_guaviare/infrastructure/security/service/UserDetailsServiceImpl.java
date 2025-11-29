package com.informeguaviare.mi_informe_guaviare.infrastructure.security.service;

import com.informeguaviare.mi_informe_guaviare.application.exceptions.UserNotFoundException;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import com.informeguaviare.mi_informe_guaviare.infrastructure.security.mapper.UserDetailsMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepositoryOutPort userRepositoryOutPort;
    private final UserDetailsMapper userDetailsMapper;

    public UserDetailsServiceImpl(UserRepositoryOutPort userRepositoryOutPort, UserDetailsMapper userDetailsMapper){
        this.userRepositoryOutPort = userRepositoryOutPort;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositoryOutPort.findByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("Usuario no encontrado con el email: " + username));
        return userDetailsMapper.toUserDetails(user);
    }
}
