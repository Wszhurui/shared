package com.example.demo.entity;

public class Idea {
	private String title;
	private String idea;
	private String path;
	private String date;
	
	public void setDate(String date)
	{
		this.date=date;
	}
	public void setIdea(String idea)
	{
		this.idea=idea;
	}
	public void setPath(String path)
	{
		this.path=path;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	public String getTitle()
	{
		return this.title;
	}
	public String getPath()
	{
		return this.path;
	}
	public String getDate()
	{
		return this.date;
	}
	public String getIdea()
	{
		return this.idea;
	}

}
