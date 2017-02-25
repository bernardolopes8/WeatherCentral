package pt.ipb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.*;

import pt.ipb.ejb.entity.Location;

public class LocationController {
	Client client = ClientBuilder.newClient();
	WebTarget target = 
			client.target("http://localhost:8080/WeatherCentral/webservices");
	
	public List<Location> getLocations() {
		WebTarget resourceTarget = target.path("LocationService/list");
		
		Invocation.Builder invocationBuilder = 
				resourceTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Location> locationList = new ArrayList<Location>();
		
		try {
			String json = response.readEntity(String.class);
			
			locationList = Arrays.asList(objectMapper.readValue(json, Location[].class));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Location list OK!");
		
		return locationList;
	}
}
