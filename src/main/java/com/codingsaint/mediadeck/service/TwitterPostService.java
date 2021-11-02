package com.codingsaint.mediadeck.service;

import com.codingsaint.mediadeck.config.TwitterConfig;
import com.codingsaint.mediadeck.models.Deck;
import com.codingsaint.mediadeck.models.Element;
import io.github.redouane59.twitter.dto.tweet.MediaCategory;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterPostService implements PostService{
    private final MediaDeckService service;
    private final FirebaseFileService firebaseFileService;

    private final Logger logger= LoggerFactory.getLogger(TwitterPostService.class);
    public TwitterPostService(MediaDeckService service, FirebaseFileService firebaseFileService
                              ) {
        this.service = service;
        this.firebaseFileService = firebaseFileService;

    }

    @Override
    public String post(Deck deck)throws Exception  {
        var elements= service.elementsByDeckId(deck.getId());
        // There are two possible scenario , its a tweet or a thread
        boolean isTweet=elements.size()==1;
        if(isTweet){
            postTweet(elements.get(0), null);
        }else {
            postThread(elements);
        }



        return "Success";
    }

    private Tweet postThread(List<Element> elements) throws IOException {
        String statusId=null;
        Tweet lastTweet=null;
        for(Element element: elements){
            lastTweet= postTweet(element , statusId);
            statusId=lastTweet.getId();
        }
        return lastTweet;
    }

    Tweet postTweet(Element element , String statusId) throws IOException {
        var mediaURLs= element.getMediaURLs();
        var tweeterImageIds=new ArrayList<String>();
        // Post Images and Collect Ids
        for(String url: mediaURLs){
            var file=firebaseFileService.download(url);
            var mediaResponse=
                    TwitterConfig.twitterClient().uploadMedia(url,
                            file,
                            MediaCategory.TWEET_IMAGE);
            tweeterImageIds.add(mediaResponse.getMediaId());
        }
        String mediaIds=String.join(",", tweeterImageIds);
        var tweetResponse= TwitterConfig.twitterClient().postTweet(element.getDesc(),
                statusId, mediaIds,
                null);
        return tweetResponse;
    }

}
