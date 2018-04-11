package com.twitter.kafka;

import com.datastax.driver.core.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.beans.TweetMessage;
import com.twitter.domain.Tweet;
import com.twitter.service.TweetService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private TweetService tweetService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "myTopic")
    public void kafkaConsumer(ConsumerRecord<byte[], byte[]> cr) {
        try {
            TweetMessage tweet = objectMapper.readValue(cr.value(), TweetMessage.class);
            Tweet tweetObj = new Tweet();
            tweetObj.setUserMention(tweet.getUserMention());
            tweetObj.setTweetUser(tweet.getUser());
            tweetObj.setTweetText(tweet.getText());
            tweetObj.setTweetLanguage(tweet.getLanguage());
            tweetObj.setTweetDate(LocalDate.fromMillisSinceEpoch(tweet.getDate().getTime()));
            tweetObj.setTweetTime(tweet.getDate().getTime());
            tweetService.insertTweet(tweetObj);
            LOGGER.info("Consumed Tweet: " + tweet.getText());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
