CREATE TABLE tweet (
user_mention text,
tweet_text text,
tweet_date date,
tweet_time time,
tweet_language text,
tweet_user text,
PRIMARY KEY ((user_mention, tweet_date), tweet_time))
 WITH CLUSTERING ORDER BY (tweet_time DESC);
