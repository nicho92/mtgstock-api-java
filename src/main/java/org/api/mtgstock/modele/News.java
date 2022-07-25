package org.api.mtgstock.modele;

import java.util.Date;

import org.api.mtgstock.tools.MTGStockConstants;

public class News {

	private String title;
	private Date date;
	private String description;
	private int id;
	private String slug;
	private Integer categoryId;
	private String categoryLabel;
	
	
	
	public String toUri()
	{
		return MTGStockConstants.MTGSTOCK_WEBSITE_URI+"/news/"+getSlug();
	}
	
	@Override
	public String toString() {
		return getTitle();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryLabel() {
		return categoryLabel;
	}
	public void setCategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}
	
	
	
	
	
}
