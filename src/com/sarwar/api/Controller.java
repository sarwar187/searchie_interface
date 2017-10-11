package com.sarwar.api;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
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
	
	@POST
	@Path("/paragraph")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Paragraph showParagraph(String passcode) throws ClassNotFoundException {
		System.out.println("showParagraph. Passcode: [" + passcode + "]");
		Paragraph paragraph = service.getParagraphContent(passcode);
		//System.out.println(paragraph);
		return paragraph;
	}
	
	@POST
	@Path("/query")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Paragraph query(Query query) throws ClassNotFoundException, SQLException {
		System.out.println("Incoming query: " + query);
		Paragraph paragraph= service.queryData(query);
		System.out.println(paragraph);
		return paragraph;
	}
	
	@POST
	@Path("/access")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Boolean access(String passcode) throws ClassNotFoundException, SQLException {
		System.out.println("Incoming passcode: " + passcode);
		Boolean access_granted = service.access(passcode);
		System.out.println("from controller " + access_granted);
		return access_granted;
	}
}
