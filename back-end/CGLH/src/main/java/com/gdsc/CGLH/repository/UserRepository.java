package com.gdsc.CGLH.repository;

import com.gdsc.CGLH.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByLoginId(String loginId);
}
