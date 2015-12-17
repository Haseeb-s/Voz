package Voz.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONObject;

public class Post implements Comparable <Post>, Comparator<Post>{
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

	public String getCoordinates(){
		
		String str = location.toString();
		String lat = str.substring(str.indexOf(' '), str.indexOf(','));
		String lng = str.substring(str.indexOf("lng: "));
		return (lat+","+lng);
	}
	
	public int compareTo(Post post){
		return this.getName().compareTo(post.getName());
	}

//    public static final Comparator<Post> DistanceComparator = new Comparator<Post>(){
//
//        @Override
//        public int compare(Post o1, Post o2) {
//            return o1.getCoordinates().compareTo(o2.getCoordinates());
//        }
//      
//    };


	
	public static int getDistance(String from, String to){
		String jsonResponse = "";
        try {
        	jsonResponse = getHTML("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+from.replaceAll("\\s+","")+"&destinations="+to.replaceAll("\\s+","")+"&units=imperial&language=en-EN");
        } catch (Exception e){
        	System.out.println(e);
        }
        String distanceString = new JSONObject(jsonResponse).getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");
        Scanner getInt = new Scanner(distanceString);
        int distance = getInt.nextInt();

        String origin = new JSONObject(jsonResponse).getJSONArray("origin_addresses").getString(0);
        String destination = new JSONObject(jsonResponse).getJSONArray("destination_addresses").getString(0);

        System.out.println("A trip from "+ origin +" to "+ destination +" is " +distanceString+ ".");

		return distance;
	}
	public static String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	   }

	@Override
	public int compare(Post o1, Post o2) {
        return o1.getCoordinates().compareTo(o2.getCoordinates());
	}
}
