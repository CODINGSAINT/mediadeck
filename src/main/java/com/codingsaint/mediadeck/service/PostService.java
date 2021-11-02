package com.codingsaint.mediadeck.service;

import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import com.codingsaint.mediadeck.models.Deck;

import java.util.concurrent.ExecutionException;

public interface PostService {
    String post(Deck element) throws Exception;
}
