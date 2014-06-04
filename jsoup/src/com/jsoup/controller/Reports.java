package com.jsoup.controller;

import java.util.List;

public class Reports {

	private String name;
	private String link;
	private boolean folder;
	private boolean report;
	private List<Reports> nextLink;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<Reports> getNextLink() {
		return nextLink;
	}
	public void setNextLink(List<Reports> nextLink) {
		this.nextLink = nextLink;
	}
	public boolean isFolder() {
		return folder;
	}
	public void setFolder(boolean folder) {
		this.folder = folder;
	}
	public boolean isReport() {
		return report;
	}
	public void setReport(boolean report) {
		this.report = report;
	}
	
}
