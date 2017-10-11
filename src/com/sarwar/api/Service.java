package com.sarwar.api;

import java.sql.SQLException;

/**
 * @author Z
 */
public class Service {
	
	public Paragraph getParagraphContent(String passcode) throws ClassNotFoundException {
		// Send paragraph according to passcode
//		Paragraph para = new Paragraph();
//		para.setId(1);
//		para.setTitle("Time Travel");
//		String contents = "Carolyn and her twin sisters , Lauren and Lisa , were raised by their mother , Ann Freeman , a teacher and administrator in the New York public schools , and their stepfather , orthopedic surgeon Richard Freeman .\r\n";
//		String instruction= "Now assume that you are interested in an entity 'Riachard Freeman' mentioned in the sentence. "
//				+ "Richard Freeman is a familiy member(father) of Carolyn, and you want to find other family members. "
//				+ "Which keywords from the sentence best describes your information need? Assume that " + 
//				"these words will be sent to a keyword-based search engine that will be used to retrieve the entities you are looking for. So, help it with keywords!!";
//		para.setDescription(contents);
//		para.setInstructions(instruction);
//		para.setPasscode(passcode);
		TestSQLiteDB testdb = new TestSQLiteDB();
		Paragraph para = testdb.selectAll(passcode);	
		return para;
	}
	
	public Paragraph queryData(Query query) throws ClassNotFoundException, SQLException {
		if (query == null) {
			System.err.println("Empty query");
			return new Paragraph();
		}
		if (query.getPasscode() == null || query.getPasscode().length() == 0) {
			System.err.println("Passcode missing");
			return new Paragraph();
		}
		
		System.out.println("Incoming query: " + query.getParam());
		String[] array = query.getParam().split("\t");
		/* do your query with this array of query params */
		String context_words = "";
		for(int i=1; i<array.length; i++) {
			context_words+=array[i] + "\t";
		}
		context_words = context_words.trim();
		Paragraph paragraph = new Paragraph();
		paragraph.setId(Integer.parseInt(array[0]));
		paragraph.setPasscode(query.getPasscode());
		//paragraph.setTitle(array[1]); //this is the passcode
		paragraph.setContext_words(context_words);
		TestSQLiteDB testdb = new TestSQLiteDB();
	    testdb.update(paragraph);
		/* do your query with this array of query params */		
		// get another paragraph for this user
		paragraph = getParagraphContent(query.getPasscode());
		//System.out.println(paragraph);
		return paragraph;
	}

	public Boolean access(String passcode) throws ClassNotFoundException, SQLException {
		// access grant or deny
		TestSQLiteDB testdb = new TestSQLiteDB();
		return testdb.checkAccess(passcode);		
	}
}
