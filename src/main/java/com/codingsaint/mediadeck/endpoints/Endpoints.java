package com.codingsaint.mediadeck.endpoints;

import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import com.codingsaint.mediadeck.models.Deck;
import com.codingsaint.mediadeck.models.Element;
import com.codingsaint.mediadeck.service.MediaDeckService;
import com.codingsaint.mediadeck.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class Endpoints implements DeckEndpoints, ElementEndpoints,FileEndpoints {
    private final Logger LOG = LoggerFactory.getLogger(Endpoints.class);

    private final MediaDeckService service;
    private final ScheduleService scheduleService;

    public Endpoints(MediaDeckService service, ScheduleService scheduleService) {
        this.service = service;
        this.scheduleService = scheduleService;
    }

    @Override
    public List<Deck> decks() throws MediaDeckExceptions {
        try {
            return service.decks();
        } catch (ExecutionException | InterruptedException e) {
            LOG.error("Error during finding all Decks ", e);
            throw new MediaDeckExceptions(1, e.getMessage());
        }
    }

    @Override
    public Deck deck(String id) throws MediaDeckExceptions {
        try {
            return service.deck(id);
        } catch (Exception e) {
            LOG.error(String.format("Error during finding  Deck %s", id), e);
            throw new MediaDeckExceptions(2, e.getMessage());
        }
    }

    @Override
    public Deck add(Deck deck) throws MediaDeckExceptions {
        Deck response=null;
        try {
            response= service.add(deck);
            scheduleService.scheduled(response);
            return response;

        } catch (Exception e) {
            LOG.error(String.format("Error during saving  Deck %s", deck), e);
            if(response!=null)
                delete(response.getId());
            throw new MediaDeckExceptions(3, e.getMessage());
        }
    }


    @Override
    public Deck update(Deck deck) throws MediaDeckExceptions {
        try {
            var response= service.update(deck);
            scheduleService.remove(response.getId());
            scheduleService.scheduled(deck);
            return response;
        } catch (Exception e) {
            LOG.error(String.format("Error during update  Deck %s", deck), e);
            throw new MediaDeckExceptions(4, e.getMessage());
        }

    }
    @Override
    public void deleteDeck(String id) throws MediaDeckExceptions {
        try {
            service.deleteDeck(id);
        } catch (Exception e) {
            LOG.error(String.format("Error during deleting  Deck %s", id), e);
            throw new MediaDeckExceptions(5, e.getMessage());
        }
    }
    @Override
    public Element save(Element element) throws MediaDeckExceptions {
        try{
            return service.save(element);
        }catch (Exception e){
            LOG.error(String.format("Error during Saving Element %s", element), e);
            throw new MediaDeckExceptions(6, e.getMessage());
        }

    }

    @Override
    public Element update(Element element) throws MediaDeckExceptions {
        try{
            return service.update(element);
        }catch (Exception e){
            LOG.error(String.format("Error during Updating Element %s", element), e);
            throw new MediaDeckExceptions(7, e.getMessage());
        }
    }

    @Override
    public List<Element> details() throws MediaDeckExceptions {
        try{
            return service.details();
    }catch (Exception e){
        LOG.error(String.format("Error during fetching elements"), e);
        throw new MediaDeckExceptions(8, e.getMessage());
    }

    }

    @Override
    public List<Element> elements(String id) throws MediaDeckExceptions {
           try{
            return service.elementsByDeckId( id);
        }catch (Exception e){
            LOG.error(String.format("Error during fetching elements"), e);
            throw new MediaDeckExceptions(8, e.getMessage());
        }
    }

    @Override
    public Element getDetail(String id) throws MediaDeckExceptions {
        try{
            return service.getDetail(id);
        }catch (Exception e){
            LOG.error(String.format("Error during fetching element %s", id), e);
            throw new MediaDeckExceptions(9, e.getMessage());
        }

    }

    @Override
    public void delete(String id) throws MediaDeckExceptions {
        try{
             service.deleteElement(id);
        }catch (Exception e){
            LOG.error(String.format("Error during fetching element %s", id), e);
            throw new MediaDeckExceptions(10, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> upload(MultipartFile media) throws MediaDeckExceptions {
        try{
            String url=service.upload(media);
            return new ResponseEntity(url, HttpStatus.CREATED);
        }catch (Exception e){
            LOG.error(String.format("Error during adding file %s", media.getOriginalFilename()), e);
            throw new MediaDeckExceptions(11, e.getMessage());
        }

    }

    @Override
    public byte[] download(String fileName) throws IOException {
        return service.download(fileName);
    }
}
