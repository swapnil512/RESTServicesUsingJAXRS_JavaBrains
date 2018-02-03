package com.itsme.restfullTutor.messenger.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.itsme.restfullTutor.messenger.model.Message;
import com.itsme.restfullTutor.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {

	MessageService msgService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages() {
		return msgService.getAllMessages();
	}

}
