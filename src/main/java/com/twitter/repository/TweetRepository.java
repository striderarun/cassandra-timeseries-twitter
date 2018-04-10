package com.twitter.repository;

import com.datastax.driver.core.Session;
import com.twitter.entity.Tweet;

import java.util.List;

public interface TweetRepository {

    boolean insert(Tweet tweet);

    List<Tweet> findAll();
}
