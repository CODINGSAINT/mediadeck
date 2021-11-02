package com.codingsaint.mediadeck.endpoints;

import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import com.codingsaint.mediadeck.models.Deck;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface DeckEndpoints {

    @GetMapping("decks")
    List<Deck> decks() throws MediaDeckExceptions;

    @GetMapping("deck/{id}")
    Deck deck (@PathVariable("id") String id) throws MediaDeckExceptions;

    @PostMapping("deck")
    Deck add(@RequestBody Deck deck) throws MediaDeckExceptions;

    @PutMapping("deck")
    Deck update(@RequestBody Deck update) throws MediaDeckExceptions;

    @DeleteMapping("deck/{id}")
    void deleteDeck(@PathVariable("id") String id) throws MediaDeckExceptions;

}


