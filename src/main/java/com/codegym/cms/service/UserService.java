package com.codegym.cms.service;

import com.codegym.cms.model.Category;
import com.codegym.cms.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface UserService {
    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    void remove(Long id);

    void save(User user);

    Page<User> findAllByTitle(String title, Pageable pageable);

    Iterable<User> findAllByCategory(Category category);
}
