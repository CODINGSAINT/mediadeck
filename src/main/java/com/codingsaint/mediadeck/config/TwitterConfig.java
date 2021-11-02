package com.codingsaint.mediadeck.config;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.signature.TwitterCredentials;

public class TwitterConfig {

    private static TwitterClient twitterClient;

    private TwitterConfig() {
    }

    public static TwitterClient twitterClient(){
        return twitterClient;
    }
    public static void twitterClient(TwitterConfigProperties properties) {
        twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken(properties.getAccessToken())
                .accessTokenSecret(properties.getAccessTokenSecret())
                .apiKey(properties.getKey())
                .apiSecretKey(properties.getKey())
                .build());
    }
}
