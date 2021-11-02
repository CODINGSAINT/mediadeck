package com.codingsaint.mediadeck.service;

import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import com.codingsaint.mediadeck.models.Deck;

public sealed interface ScheduleService permits ScheuledServiceImpl {

    boolean scheduled(Deck deck) throws MediaDeckExceptions;

    boolean updateSchedule(Deck deck) throws MediaDeckExceptions;

    boolean remove(String deck) throws MediaDeckExceptions;
}
