package dev.erik.twitter_simplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.erik.twitter_simplificado.models.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
