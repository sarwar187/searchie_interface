package com.sarwar.api;


import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Z
 */
public class Service {
	
	public Paragraph getParagraphContent() throws ClassNotFoundException {
		Paragraph para = new Paragraph();
		para.setId("1");
		para.setTitle("");
		String description="Carolyn and her twin sisters , Lauren and Lisa , were raised by their mother , Ann Freeman , a teacher and administrator in the New York public schools , and their stepfather , orthopedic surgeon Richard Freeman .\r\n";
		para.setDescription(description);
		String instructions= "Now assume that you are interested in an entity 'Riachard Freeman' mentioned in the sentence. "
				+ "Richard Freeman is a familiy member(father) of Carolyn, and you want to find other family members. "
				+ "Which keywords from the sentence best describes your information need? Assume that " + 
				"these words will be sent to a keyword-based search engine that will be used to retrieve the entities you are looking for. So, help it with keywords!!";
		para.setInstructions(instructions);	
		TestSQLiteDB testdb = new TestSQLiteDB();
		para = testdb.selectAll();		
		return para;
	}
	
	public boolean queryData(Query query) throws IOException, ClassNotFoundException, SQLException {
		if (query == null) {
			System.err.println("Empty query");
			return false;
		}
		
		System.out.println("Incoming query: " + query.getParam());
		
		//Writing this to a file
		String[] array = query.getParam().split("\t");
		
		/*
		for(int i=0; i<array.length; i++) {
			System.out.println(array[i]);
		}
					
		Random r = new Random();
		String fileName = String.valueOf(r.nextInt(100000));
		String context_words = "";
		BufferedWriter br = new BufferedWriter(new FileWriter(new File("/home/smsarwar/sarwar/searchie_query/" + fileName + ".txt")));
		for(int i=0; i<array.length; i++) {
			br.write(array[i] + "\t");
			
		}
		br.newLine();
		br.close();		
		*/
		
		//Writing this to sqlite database. First two params are paragraph id and paragraph title
		String context_words = "";
		for(int i=2; i<array.length; i++) {
			context_words+=array[i] + "\t";
		}
		context_words = context_words.trim();
		Paragraph paragraph = new Paragraph();
		paragraph.setId(array[0]);
		paragraph.setTitle(array[1]); //this is the passcode
		paragraph.setContext_words(context_words);
		TestSQLiteDB testdb = new TestSQLiteDB();
	    boolean flag  = testdb.update(paragraph);
		/* do your query with this array of query params */		
		return flag;
	}
}
