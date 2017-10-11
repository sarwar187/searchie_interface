package com.sarwar.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Z
 */
@XmlRootElement
public class Paragraph {

    private Integer id;

    private String title;

    private String description;
    
    private String instructions; 
    
    private String context_words;
    
    private String passcode;
    
    private int input_count;
    
    public int getInput_count() {
		return input_count;
	}

	public void setInput_count(int input_count) {
		this.input_count = input_count;
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return this.passcode + "\t" + this.getId() + "\t" + this.getDescription() + "\t" + this.getTitle() + "\t" + this.getInstructions();
	}

	public void setPasscode(String passcode) {
		// TODO Auto-generated method stub
		this.passcode = passcode;
		
	}
	
	public String getPasscode() {
		return this.passcode;
	}
}
