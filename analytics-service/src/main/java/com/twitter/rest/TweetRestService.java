package com.twitter.rest;

import com.datastax.driver.core.LocalDate;
import com.twitter.domain.Tweet;
import com.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/services/tweet")
public class TweetRestService {

    @Autowired
    private TweetService tweetService;

    @RequestMapping(method= RequestMethod.POST)
    public void save() {
        Tweet tweet = new Tweet();
        tweet.setUserMention("abc");
        tweet.setTweetDate(LocalDate.fromMillisSinceEpoch(System.currentTimeMillis()));
        tweet.setTweetTime(System.currentTimeMillis());
        tweet.setTweetText("def");
        tweet.setTweetLanguage("en");
        tweet.setTweetUser("ghi");
        tweetService.insertTweet(tweet);
    }
}

