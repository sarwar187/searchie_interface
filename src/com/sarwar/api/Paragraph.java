package com.sarwar.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Z
 */
@XmlRootElement
public class Paragraph {

    private int id;

    private String title;

    private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}
