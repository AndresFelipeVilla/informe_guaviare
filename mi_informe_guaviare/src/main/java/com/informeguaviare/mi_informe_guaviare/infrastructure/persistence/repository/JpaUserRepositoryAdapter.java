package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.repository;

import com.informeguaviare.mi_informe_guaviare.domain.port.out.UserRepositoryOutPort;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.springdata.SpringDataUserRepository;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryOutPort {

    private final SpringDataUserRepository springDataUserRepository;
    private final UserPersistenceMapper userMapper;

    public JpaUserRepositoryAdapter(SpringDataUserRepository springDataUserRepository, UserPersistenceMapper userMapper) {
        this.springDataUserRepository = springDataUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedUserEntity = springDataUserRepository.save(userEntity);
        return userMapper.toDomain(savedUserEntity);
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return springDataUserRepository.findById(userId).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByBossCode(String bossCode) {
        return springDataUserRepository.findByBossCode(bossCode).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email).map(userMapper::toDomain);
    }
}
