package com.twitter.service;

import com.twitter.entity.Tweet;

import java.util.List;

public interface TweetService {

    boolean insertTweet(Tweet tweet);

    List<Tweet> findTweets();
}
