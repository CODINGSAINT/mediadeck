package com.codingsaint.mediadeck.models;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Deck {
    private String  title;
    private String id;
    private String scheduledDate;
    private String createdOn;
   }
