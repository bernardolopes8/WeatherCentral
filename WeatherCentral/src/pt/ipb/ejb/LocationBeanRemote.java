package pt.ipb.ejb;

import java.util.List;

import javax.ejb.Remote;

import pt.ipb.ejb.entity.WeatherInfo;
import pt.ipb.ejb.entity.Location;

@Remote
public interface LocationBeanRemote {
	// Create
	void create(Location location);
    void create(String name);
	
    // Get
	Location getLocation(long id);
	Location getLocation(String name);
	String getLocationName(long id);
	List<WeatherInfo> getAllWeatherInfo(Location location);
	long getLocationCount();
	List<Location> getLocationList();
    
	// Update
	void update(long id, String newName);
	void update(long id);
	void update(Location location);
	
	// Delete
	void delete(Location location);
	void delete(long id);
}
