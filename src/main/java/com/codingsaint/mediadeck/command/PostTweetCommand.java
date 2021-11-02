package com.codingsaint.mediadeck.command;

import com.codingsaint.mediadeck.models.Deck;
import com.codingsaint.mediadeck.service.TwitterPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTweetCommand implements Runnable{
    private final Deck deck;
    private final TwitterPostService postService;
    private final Logger LOG= LoggerFactory.getLogger(PostTweetCommand.class);

    public PostTweetCommand(Deck deck, TwitterPostService postService) {
        this.deck = deck;
        this.postService = postService;
    }

    @Override
    public void run() {
        LOG.info("Running scheduled Task --> {}", deck.getTitle() );
        try {
            postService.post(deck);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
