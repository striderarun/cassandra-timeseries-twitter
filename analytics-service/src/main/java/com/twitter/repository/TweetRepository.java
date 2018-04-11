package com.twitter.repository;

import com.twitter.domain.Tweet;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CassandraRepository<Tweet, String> {


}
