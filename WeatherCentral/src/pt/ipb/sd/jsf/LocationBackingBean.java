package pt.ipb.sd.jsf;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import pt.ipb.ejb.LocationBeanRemote;
import pt.ipb.ejb.entity.Location;
import pt.ipb.ejb.entity.WeatherInfo;

@ManagedBean
@RequestScoped
public class LocationBackingBean {
	@EJB
    LocationBeanRemote locations;

	private String createName;
	private String updateName;
	private long updateLocationId;
	
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public long getUpdateLocationId() {
		return updateLocationId;
	}

	public void setUpdateLocationId(long updateLocationId) {
		this.updateLocationId = updateLocationId;
	}

	public Location getLocation(long id) {
		return locations.getLocation(id);
	}
	
    public List<Location> getLocationList() {
    	return locations.getLocationList();
    }
    
    public List<WeatherInfo> getAllWeatherInfo(Location location) {
    	return location.getWeatherInfoList();
    }
    
    public long getLocationCount() {
    	return locations.getLocationCount();	
    }
    
    public void createLocation() {
    	locations.create(createName);
    }
    
    public void updateLocation() {
    	locations.update(updateLocationId, updateName);
    }
    
    public void deleteLocation(Location location) {
    	locations.delete(location);
    }
}
