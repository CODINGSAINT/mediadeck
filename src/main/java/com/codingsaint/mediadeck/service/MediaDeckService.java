package com.codingsaint.mediadeck.service;

import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import com.codingsaint.mediadeck.models.Deck;
import com.codingsaint.mediadeck.models.Element;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MediaDeckService {

    List<Deck> decks() throws ExecutionException, InterruptedException;

    Deck deck (String id) throws ExecutionException, InterruptedException;

    Deck add(Deck deck) throws ExecutionException, InterruptedException, MediaDeckExceptions;

    Deck update(Deck update) throws ExecutionException, InterruptedException;

    void deleteDeck(String id) throws ExecutionException, InterruptedException;

    Element save(Element element) throws ExecutionException, InterruptedException;

    Element update(Element element) throws ExecutionException, InterruptedException;

    List<Element> details() throws ExecutionException, InterruptedException;

    Element getDetail(String id) throws ExecutionException, InterruptedException;

     void deleteElement(String id) throws ExecutionException, InterruptedException;

    List<Element> elementsByDeckId(String deckId) throws ExecutionException, InterruptedException;

    String upload(MultipartFile media) throws IOException;

    byte[] download(String fileName) throws IOException;
}
