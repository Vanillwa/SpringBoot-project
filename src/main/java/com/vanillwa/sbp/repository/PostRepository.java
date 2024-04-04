package com.vanillwa.sbp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.domain.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByOrderByCreatedAtDesc();

	Page<Post> findAll(Pageable pageable);

	List<Post> findAllByUser_UserId(Long userId);
}
