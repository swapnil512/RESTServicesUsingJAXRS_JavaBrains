package com.itsme.restfullTutor.messenger.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.itsme.restfullTutor.messenger.model.Message;

public class MessageService {

	public List<Message> getAllMessages(){
		Message m1 = new Message(1, "Hello World", "author 1" );
		Message m2 = new Message(1, "Hello Jersey", "author 2");
		List<Message>  listOfMsg =  new ArrayList<Message>();
		listOfMsg.add(m1);
		listOfMsg.add(m2);
		return listOfMsg;
	}
}

