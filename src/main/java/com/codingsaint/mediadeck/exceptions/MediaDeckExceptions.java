package com.codingsaint.mediadeck.exceptions;


public class MediaDeckExceptions extends Exception{
    public MediaDeckExceptions(Integer code){
        super(String.format("Error Occurred - code : %d ",code));
    }
    public MediaDeckExceptions(Integer code, String message){
        super(String.format("Error Occurred - %s : code : %d ",message,code));
    }
}
