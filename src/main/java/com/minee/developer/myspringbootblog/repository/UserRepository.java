package com.minee.developer.myspringbootblog.repository;

import com.minee.developer.myspringbootblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
