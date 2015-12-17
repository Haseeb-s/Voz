package Voz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Voz.technique.A3.WeightedGraph;
import Voz.technique.A4.BST;
import Voz.technique.A4.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.*;

@Controller
// @RequestMapping("/Voz/Post")
public class RestController {

	private BST<Post> bst = new BST<Post>();
	private WeightedGraph wg = new WeightedGraph();

	public RestController() {
		System.out.println("init RestController");
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST, consumes = "application/json")
	public void addPost(@RequestBody String json) throws JsonParseException, IOException {
		System.out.println(json.toString());
		ObjectMapper mapper = new ObjectMapper();
		Post post = mapper.readValue(json, Post.class);
		bst.add(post);
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public void getPost(@RequestParam("_lat") String lat, @RequestParam(value = "lng") String lng) {
		System.out.println(lat + " ayyy " + lng);
		Location temp = new Location(Double.parseDouble(lat),Double.parseDouble(lng));
		String start = new String("start");
		wg.addVertex(start);
		addEdges(temp);
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.POST, consumes = "application/json")
	public void addPost1(@RequestBody String json) throws JsonParseException, IOException {
		System.out.println(json.toString());
		ObjectMapper mapper = new ObjectMapper();
		Post post = mapper.readValue(json, Post.class);
		System.out.println(post);
		bst.add(post);
	}

	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public void getPost1(@RequestParam("_lat") String lat, @RequestParam(value = "lng") String lng) {
		System.out.println(lat + " ayyy " + lng);
		Location temp = new Location(Double.parseDouble(lat),Double.parseDouble(lng));
		String start = new String("start");
		wg.addVertex(start);
		//addEdges(temp);
	}
	@SuppressWarnings("unchecked")
	public void addEdges(Location start){
		Collection<Post> nodes = bst.traversal(Order.INORDER);
		for(Post elem : nodes){
			Location temp = (Location) elem.getLocation();
			Double lat = temp.getLat();
			Double lng = temp.getLon();
			double dis = start.getDistance(lat, lng);
			wg.addEdge((Object)start, (Object)elem, dis);
		}
		System.out.println(wg.toString());
	}
	
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
}
