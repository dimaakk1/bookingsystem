package org.example.user.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.example.user.model.UserEntity;
import org.example.user.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<UserEntity> getAll() {
        return userRepository.listAll();
    }

    public UserEntity getById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public UserEntity create(UserEntity user) {
        user.id = null;
        userRepository.persist(user);
        return user;
    }

    @Transactional
    public UserEntity update(Long id, UserEntity updates) {
        UserEntity user = userRepository.findById(id);

        if (user == null) {
            return null;
        }

        user.name = updates.name;
        user.email = updates.email;

        return user;
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.deleteById(id);
    }
}
