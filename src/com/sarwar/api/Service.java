package com.sarwar.api;

/**
 * @author Z
 */
public class Service {
	
	public Paragraph getParagraphContent() {
		Paragraph para = new Paragraph();
		para.setId(1);
		para.setTitle("");
		String description = "Suppose you are reading a sentence like one below\n";
		description+="Carolyn and her twin sisters , Lauren and Lisa , were raised by their mother , Ann Freeman , a teacher and administrator in the New York public schools , and their stepfather , orthopedic surgeon Richard Freeman .\r\n";
		description+= "Now assume that you are interested in an entity 'Riachard Freeman' mentioned in the sentence. Richard Freeman is a familiy member(father) of Carolyn, and you want to find other family members. Which keywords from the sentence best describes your information need? Assume that " + 
		"these words will be sent to a keyword-based search engine";		
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
