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

import pt.ipb.ejb.entity.WeatherInfo;

public class WeatherInfoController {
	Client client = ClientBuilder.newClient();
	WebTarget target = 
			client.target("http://localhost:8080/WeatherCentral/webservices");
	
	public List<WeatherInfo> getAllWeatherInfo() {
		WebTarget resourceTarget = target.path("WeatherInfoService/record");
		
		Invocation.Builder invocationBuilder = 
				resourceTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<WeatherInfo> weatherInfoList = new ArrayList<WeatherInfo>();
		
		try {
			String json = response.readEntity(String.class);
			
			weatherInfoList = Arrays.asList(objectMapper.readValue(json, WeatherInfo[].class));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Weather info list OK!");
		
		return weatherInfoList;
	}
	
	public List<WeatherInfo> getAllWeatherInfoAscending() {
		WebTarget resourceTarget = target.path("WeatherInfoService/recordAscending");
		
		Invocation.Builder invocationBuilder = 
				resourceTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<WeatherInfo> weatherInfoList = new ArrayList<WeatherInfo>();
		
		try {
			String json = response.readEntity(String.class);
			
			weatherInfoList = Arrays.asList(objectMapper.readValue(json, WeatherInfo[].class));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Ascending weather info list OK!");
		
		return weatherInfoList;
	}
}
