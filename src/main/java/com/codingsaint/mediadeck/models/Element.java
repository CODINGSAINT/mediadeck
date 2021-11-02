package com.codingsaint.mediadeck.models;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Element {
    private String id;
    private String desc;
    private String deckId;
    private Integer sequence;
    private List<String> mediaURLs;
}
