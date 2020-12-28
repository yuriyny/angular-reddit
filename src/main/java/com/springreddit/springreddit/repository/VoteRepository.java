package com.springreddit.springreddit.repository;

import com.springreddit.springreddit.model.Post;
import com.springreddit.springreddit.model.User;
import com.springreddit.springreddit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}