package com.twitter.service.impl;

import com.twitter.domain.Tweet;
import com.twitter.repository.TweetRepository;
import com.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public void insertTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }
}
