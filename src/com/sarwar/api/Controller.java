package com.sarwar.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Z
 */
@Path("/api")
public class Controller {
	
	Service service = new Service();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Paragraph showParagraph() {
		System.out.println("showParagraph");
		return service.getParagraphContent();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Boolean query(Query query) {
		return service.queryData(query);
	}

}
