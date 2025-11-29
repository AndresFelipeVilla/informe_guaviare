package com.informeguaviare.mi_informe_guaviare.infrastructure.security.mapper;

import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.security.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsMapper {

    public UserDetails toUserDetails(User user) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        return new CustomUserDetails(
                user.getEmail().getValue(),
                user.getPasswordHash(),
                authorities,
                user.getUserId().getValue().toString()
        );
    }

}

