package pt.ipb.sd.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pt.ipb.ejb.LocationBeanRemote;
import pt.ipb.ejb.entity.Location;
import pt.ipb.ejb.entity.WeatherInfo;

@Path("/LocationService")
public class LocationsWS {
	@EJB
	LocationBeanRemote locations;
	
	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Location> getLocationList() {
		return locations.getLocationList();
	}
	
	@GET
	@Path("/location/{name}")
	@Produces("application/json")
	public Location getLocationByName(@PathParam("name") String name) {
		return locations.getLocation(name);
	}
	
	@GET
	@Path("/allWeatherInfo/{name}")
	@Produces("application/json")
	public List<WeatherInfo> getAllWeatherInfoByName(@PathParam("name") String name) {
		return locations.getAllWeatherInfo(locations.getLocation(name));
	}
}