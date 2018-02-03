package com.itsme.restfullTutor.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoParam {

	@GET
	@Path("annotations")
	public String getParamUsingAnnotation(@MatrixParam("param") String matrixParam,
			@HeaderParam("customHeaderParam") String header, @CookieParam("new") String cookie) {
		return "Matrix Param :" + matrixParam + " Header Param : " + header + " Cookie Param : " + cookie;
	}

	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders header) {
		String path = uriInfo.getAbsolutePath().toString();
		String headersparam = header.getRequestHeaders().toString();
		return "Path : " + path + "header Param : " + headersparam;
	}
}
