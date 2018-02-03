package com.itsme.restfullTutor.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.itsme.restfullTutor.messenger.model.Message;
import com.itsme.restfullTutor.messenger.model.Profile;
import com.itsme.restfullTutor.messenger.resources.beans.MessageFilterBean;
import com.itsme.restfullTutor.messenger.service.MessageService;
//The url will be "http://localhost:8080/messenger/webapi/messages"
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public List<Message>
	 * getAllMessages() { return messageService.getAllMessages(); }
	 * 
	 * @GET
	 * 
	 * @Path("/test")
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String test() { return "test"; }
	 * 
	 * @GET
	 * 
	 * @Path("/{messageId}")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public Message
	 * getMessage(@PathParam("messageId") long messageId) { return
	 * messageService.getMessage(messageId); }
	 * 
	 * @POST
	 * 
	 * @Path("/{message}")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public Message
	 * addMessage(@PathParam("message") Message message) { return
	 * messageService.addMessage(message); }
	 * 
	 * @PUT
	 * 
	 * @Path("/{message}")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public Message
	 * updateMessage(@PathParam("message") Message message) { return
	 * messageService.updateMessage(message); }
	 * 
	 * @DELETE
	 * 
	 * @Path("/messageId")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public Message
	 * removeMessage(@PathParam("messageId") long messageId) { return
	 * messageService.removeMessage(messageId); }
	 * 
	 * @POST
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public String addMessage(){ return
	 * "Post Works!"; }
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonAllMessages(@BeanParam MessageFilterBean messageFilter) {
		System.out.println("Json is created");
		if (messageFilter.getYear() > 0) {
			return messageService.getAllMessageForYear(messageFilter.getYear());
		}
		if (messageFilter.getStart() > 0 && messageFilter.getSize() >= 0) {
			return messageService.getAllMessagesPaginated(messageFilter.getStart(), messageFilter.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXmlAllMessages(@BeanParam MessageFilterBean messageFilter) {
		System.out.println("XML is created");
		if (messageFilter.getYear() > 0) {
			return messageService.getAllMessageForYear(messageFilter.getYear());
		}
		if (messageFilter.getStart() > 0 && messageFilter.getSize() >= 0) {
			return messageService.getAllMessagesPaginated(messageFilter.getStart(), messageFilter.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.addLinks(getUriForSelf(uriInfo, message), "self");
		message.addLinks(getUriForProfile(uriInfo, message), "profile");
		message.addLinks(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri  = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build();
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri  = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId()))
				.build().toString();
		return uri;
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		// return Response.status(Status.CREATED).entity(newMessage).build();
		// return Response.created(new URI("/messenger/webapi/messages/"+
		// newMessage.getId())).entity(newMessage).build();
		return Response.created(uri).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
