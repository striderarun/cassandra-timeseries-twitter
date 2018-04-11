package com.twitter.domain;

import com.datastax.driver.core.LocalDate;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Table
public class Tweet implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKeyColumn(name = "user_mention", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userMention;

    @PrimaryKeyColumn(name = "tweet_date", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private LocalDate tweetDate;

    @PrimaryKeyColumn(name = "tweet_time", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Long tweetTime;

    @Column("tweet_user")
    private String tweetUser;

    @Column("tweet_text")
    private String tweetText;

    @Column("tweet_language")
    private String tweetLanguage;

    public String getUserMention() {
        return userMention;
    }

    public void setUserMention(String userMention) {
        this.userMention = userMention;
    }

    public LocalDate getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(LocalDate tweetDate) {
        this.tweetDate = tweetDate;
    }

    public Long getTweetTime() {
        return tweetTime;
    }

    public void setTweetTime(Long tweetTime) {
        this.tweetTime = tweetTime;
    }

    public String getTweetUser() {
        return tweetUser;
    }

    public void setTweetUser(String tweetUser) {
        this.tweetUser = tweetUser;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public String getTweetLanguage() {
        return tweetLanguage;
    }

    public void setTweetLanguage(String tweetLanguage) {
        this.tweetLanguage = tweetLanguage;
    }
}