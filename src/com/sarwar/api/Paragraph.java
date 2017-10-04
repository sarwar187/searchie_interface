package com.sarwar.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Z
 */
@XmlRootElement
public class Paragraph {

    private String id;

    private String title;

    private String description;
    
    private String instructions; 
    
    private String context_words;
    
    public String getContext_words() {
		return context_words;
	}

	public void setContext_words(String context_words) {
		this.context_words = context_words;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		return this.getId() + "\t" + this.getDescription() + "\t" + this.getTitle() + "\t" + this.getInstructions();
	}
}
