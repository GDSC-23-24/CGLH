package com.gdsc.CGLH.repository;

import com.gdsc.CGLH.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<Member,Long> {
    Optional<Member> findByLoginId(String loginId);
}
