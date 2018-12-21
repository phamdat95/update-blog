package com.codegym.cms.repository;

import com.codegym.cms.model.Category;
import com.codegym.cms.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findAllByTitleContaining(String title, Pageable pageable);

    Iterable<User> findAllByCategory(Category category);
}
