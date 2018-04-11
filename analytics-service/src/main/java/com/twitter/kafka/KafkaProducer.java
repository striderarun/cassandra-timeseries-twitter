package com.twitter.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.beans.TweetMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserMentionEntity;

import java.util.Date;
import java.util.List;

@Component
public class KafkaProducer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${tweet.user.mentions}")
    private List<String> userMentionList;

    @Value("${tweet.kafka.topic}")
    private String topic;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<byte[], byte[]> kafkaTemplate;

    public void twitterStreamingFeedProducer() {
        StatusListener listener = new StatusListener(){

            @Override
            public void onException(Exception e) {
                LOGGER.error(e.getMessage());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg) { }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) { }

            @Override
            public void onStallWarning(StallWarning warning) { }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) { }

            @Override
            public void onStatus(Status status) {
                UserMentionEntity[] userMentionEntities = status.getUserMentionEntities();
                for(UserMentionEntity userMentionEntity : userMentionEntities) {
                    if (userMentionList.contains(userMentionEntity.getScreenName())) {
                        TweetMessage tweet = new TweetMessage();
                        tweet.setUserMention(userMentionEntity.getScreenName());
                        tweet.setUser(status.getUser().getScreenName());
                        tweet.setText(status.getText());
                        tweet.setLanguage(status.getLang());
                        Date date = status.getCreatedAt();
                        tweet.setTime(date.getTime());
                        tweet.setDate(date);
                        try {
                            byte[] messageBytes = objectMapper.writeValueAsBytes(tweet);
                            kafkaTemplate.send(topic, messageBytes);
                        } catch (JsonProcessingException e) {
                            LOGGER.error(e.getMessage());
                        }
                    }
                }
            }
        };
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);
        twitterStream.sample();

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        twitterStreamingFeedProducer();
    }
}
