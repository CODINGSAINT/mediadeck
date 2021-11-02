package com.codingsaint.mediadeck.service;

import com.codingsaint.mediadeck.config.StorageConfigProperties;
import com.codingsaint.mediadeck.config.StorageInitializer;
import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import com.codingsaint.mediadeck.models.Deck;
import com.codingsaint.mediadeck.models.Element;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class MediaDeckServiceImpl implements MediaDeckService {
    private final Logger LOG = LoggerFactory.getLogger(MediaDeckServiceImpl.class);
    private final String DECK = "deck";
    private final String ELEMENT = "element";


  ;
    private final StorageConfigProperties storageProperties;

    public MediaDeckServiceImpl(StorageConfigProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public List<Deck> decks() throws ExecutionException, InterruptedException {
        LOG.info("Finding All Decks ");
        List<Deck> decks = new ArrayList<>();

        var dbStore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbStore.collection(DECK).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (!CollectionUtils.isEmpty(documents)) {
            for (var doc : documents) {
                var deck = doc.toObject(Deck.class);
                decks.add(deck);
            }
        }
        return decks;
    }

    @Override
    public Deck deck(String id) throws ExecutionException, InterruptedException {
        LOG.info("Finding Deck with id {} ", id);
        Deck deck = null;
        var dbStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbStore.collection(DECK).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            deck = document.toObject(Deck.class);
        }
        return deck;
    }

    @Override
    public Deck add(Deck deck) throws ExecutionException, InterruptedException, MediaDeckExceptions {
        var dbFirestore = FirestoreClient.getFirestore();
        deck.setId(UUID.randomUUID().toString());
        var collectionsApiFuture = dbFirestore.collection(DECK).document(deck.getId()).set(deck);
        var time = collectionsApiFuture.get().getUpdateTime();

        return deck;
    }

    @Override
    public Deck update(Deck deck) throws ExecutionException, InterruptedException {
        var dbFirestore = FirestoreClient.getFirestore();
        var collectionsApiFuture = dbFirestore.collection(DECK).document(deck.getId()).set(deck);
        var time = collectionsApiFuture.get().getUpdateTime();
        return deck;
    }

    @Override
    public void deleteDeck(String id) throws ExecutionException, InterruptedException {
        var elements = elementsByDeckId(id);
        elements.stream().forEach(element ->
                {
                    try {
                        deleteElement(element.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        var dbFirestore = FirestoreClient.getFirestore();
        var writeResult = dbFirestore.collection(DECK).document(id).delete();
        LOG.info(String.valueOf(writeResult.get()));
    }

    @Override
    public Element save(Element element) throws ExecutionException, InterruptedException {
        var dbFirestore = FirestoreClient.getFirestore();
        element.setId(UUID.randomUUID().toString());
        var elements = elementsByDeckId(element.getDeckId());
        // Always Add +1 to sequence than last added element , This will help if elements are deleted
        if (elements.size() > 0)
            element.setSequence(elements.get(elements.size() - 1).getSequence() + 1);
        else
            element.setSequence(1);
        var collectionsApiFuture = dbFirestore.collection(ELEMENT).document(element.getId()).set(element);
        return element;
    }

    @Override
    public Element update(Element element) throws ExecutionException, InterruptedException {
        var dbFirestore = FirestoreClient.getFirestore();
        var collectionsApiFuture = dbFirestore.collection(ELEMENT).document(element.getId()).set(element);
        var time = collectionsApiFuture.get().getUpdateTime();
        return element;
    }

    @Override
    public List<Element> details() throws ExecutionException, InterruptedException {
        List<Element> elements = new ArrayList<>();
        var dbStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbStore.collection(ELEMENT).document();
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            elements = document.toObject(ArrayList.class);
        }
        return elements;
    }

    @Override
    public Element getDetail(String id) throws ExecutionException, InterruptedException {
        LOG.info("Finding Element {id} ", id);
        Element element = null;
        var dbStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbStore.collection(ELEMENT).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            element = document.toObject(Element.class);
        }
        return element;
    }

    private void deleteElementMedia(String id) throws ExecutionException, InterruptedException {
        var element = getDetail(id);
        for (var photoId : element.getMediaURLs()) {
            StorageInitializer.storage().delete(storageProperties.getBucket(), photoId);
        }
    }

    @Override
    public void deleteElement(String id) throws ExecutionException, InterruptedException {
        deleteElementMedia(id);
        var dbFirestore = FirestoreClient.getFirestore();
        var writeResult = dbFirestore.collection(ELEMENT).document(id).delete();
        LOG.info(String.valueOf(writeResult.get()));

    }

    @Override
    public List<Element> elementsByDeckId(String deckId) throws ExecutionException, InterruptedException {
        LOG.info("Finding All Elements for Deck with Id {} ", deckId);
        List<Element> elements = new ArrayList<>();
        var dbStore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbStore.collection(ELEMENT).get();
        CollectionReference cities = dbStore.collection(ELEMENT);
        // Create a query against the collection.
        Query query = cities.whereEqualTo("deckId", deckId);
        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (var doc : querySnapshot.get().getDocuments()) {
            Element element = doc.toObject(Element.class);
            elements.add(element);
        }
        elements.sort(Comparator.comparingInt(Element::getSequence));

        return elements;
    }

    @Override
    public String upload(MultipartFile media) throws IOException {
        String fileName = RandomStringUtils.randomAlphanumeric(5).toLowerCase() + "_" + media.getName();
        BlobId blobId = BlobId.of(storageProperties.getBucket(), fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        StorageInitializer.storage().create(blobInfo, media.getBytes());
        return fileName;
    }

    public byte[] download(String fileName) throws IOException {
        var blob =  StorageInitializer.storage().get(BlobId.of(storageProperties.getBucket(), fileName));
        return blob.getContent();
    }
}
