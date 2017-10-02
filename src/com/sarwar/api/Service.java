package com.sarwar.api;

/**
 * @author Z
 */
public class Service {
	
	public Paragraph getParagraphContent() {
		Paragraph para = new Paragraph();
		para.setId(1);
		para.setTitle("Time Travel");
		para.setDescription("Its a dummy description.");
		
		return para;
	}
	
	public boolean queryData(Query query) {
		if (query == null) {
			System.err.println("Empty query");
			return false;
		}
		
		System.out.println("Incoming query: " + query.getParam());
		String[] array = query.getParam().split("<$@4M@4>");
		/* do your query with this array of query params */
		
		return true;
	}
}
