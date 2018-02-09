package com.usermindarchive.h.projectsms;

/**
 * Created by HERO on 12/2/2017.
 */

public class MessageFormat {
String PERSON,ADDRESS,BODY,STATUS;

int Mode,THREAD_ID;
long DATE;
boolean READ;

//mode is used to identify the message is sent or received
//1 for inbox and 0 for outbox
public MessageFormat(int THREAD_ID,boolean READ,String PERSON,String ADDRESS,String BODY,long DATE,String STATUS,int Mode){
    this.ADDRESS=ADDRESS;
    this.BODY=BODY;
    this.DATE=DATE;
    this.READ=READ;
    this.THREAD_ID=THREAD_ID;
    this.PERSON=PERSON;
    this.STATUS=STATUS;
    this.Mode=Mode;
}
}
