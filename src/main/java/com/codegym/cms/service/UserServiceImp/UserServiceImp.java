package com.codegym.cms.service.UserServiceImp;

import com.codegym.cms.model.Category;
import com.codegym.cms.model.User;
import com.codegym.cms.repository.UserRepository;
import com.codegym.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findAllByTitle(String title, Pageable pageable) {
        return userRepository.findAllByTitleContaining(title, pageable);
    }

    @Override
    public Iterable<User> findAllByCategory(Category category) {
        return userRepository.findAllByCategory(category);
    }

}
