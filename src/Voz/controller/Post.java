+package Voz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class Post implements Comparable <Post>{
	private Random random = new Random();
	private int ID = random.nextInt(99999999);
	private String name;
	SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	String TIME;
	private Date dNow = new Date();
	private Object fields, location;

	public Post() {
		name = "n/a";
		TIME = ft.format(dNow);
		this.location = -1;
	}

	public Post(String name, Object fields, Object location) {
		super();
		this.name = name;
		TIME = ft.format(dNow);
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return TIME;
	}

	public void setTIME() {
		dNow = new Date();
		this.TIME = ft.format(dNow);
	}

	@Override
	public String toString() {
		return "Post [random=" + random + ", ID=" + ID + ", name=" + name + ", ft=" + ft + ", TIME=" + TIME + ", dNow="
				+ dNow + ", fields=" + fields + ", location=" + location + "]";
	}

	public Object getFields() {
		return fields;
	}

	public void setFields(Object fields) {
		this.fields = fields;
	}

	public Object getLocation() {
		return location;
	}

	public void setLocation(Object location) {
		this.location = location;
	}

	public int compareTo(Post post){
		return this.getName().compareTo(post.getName());
	}
}
