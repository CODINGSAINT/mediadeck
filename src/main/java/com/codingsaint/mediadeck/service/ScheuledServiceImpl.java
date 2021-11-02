package com.codingsaint.mediadeck.service;

import com.codingsaint.mediadeck.command.PostTweetCommand;
import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import com.codingsaint.mediadeck.models.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public final class ScheuledServiceImpl implements ScheduleService {
    private static final Logger LOG = LoggerFactory.getLogger(ScheuledServiceImpl.class);
     private final TwitterPostService twitterPostService;
    private final TaskScheduler taskScheduler;
    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();
    public ScheuledServiceImpl( TwitterPostService twitterPostService, TaskScheduler taskScheduler) {
        this.twitterPostService = twitterPostService;
        this.taskScheduler = taskScheduler;
    }


    @Override
    public boolean scheduled(Deck deck) throws MediaDeckExceptions {
        boolean isScheduled = true;
        try {
            var when = LocalDateTime.parse
                    (deck.getScheduledDate(), DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                    ));
            var scheduled=taskScheduler.schedule(new PostTweetCommand(deck, twitterPostService), when.toInstant(ZoneOffset.UTC));
            jobsMap.put(deck.getId(), scheduled);
        } catch (Exception e) {
            LOG.info("Exception Occurred during scheduling the Tweets", e);
            isScheduled = false;
            throw new MediaDeckExceptions(31, e.getMessage());

        }
        return isScheduled;
    }

    @Override
    public boolean updateSchedule(Deck deck) throws MediaDeckExceptions {
        var scheduled=jobsMap.get(deck.getId());
        scheduled.cancel(true);
        return scheduled(deck);

    }

    @Override
    public boolean remove(String id) throws MediaDeckExceptions {
        var scheduled=jobsMap.get(id);
        if(scheduled!=null)
         scheduled.cancel(true);
        jobsMap.put(id, null);
        return true;
    }
}
