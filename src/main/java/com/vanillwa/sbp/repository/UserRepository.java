package com.vanillwa.sbp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanillwa.sbp.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByNickname(String nickname);
}
