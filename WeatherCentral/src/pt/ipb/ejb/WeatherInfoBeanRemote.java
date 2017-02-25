package pt.ipb.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import pt.ipb.ejb.entity.WeatherInfo;
import pt.ipb.ejb.entity.Location;

@Remote
public interface WeatherInfoBeanRemote {
	// Create
	WeatherInfo create(WeatherInfo weatherInfo);
	long create(long temperature, String forecast, Date datetime, Location location);
	
	// Get
	long getTemperature(long id);
	String getTemperatureString(long id);
	String getForecast(long id);
	Date getDateTime(long id);
	WeatherInfo getWeatherInfo(long id);
	List<WeatherInfo> getAllWeatherInfo();
	List<WeatherInfo> getAllWeatherInfoAscending();
	long getWeatherInfoCount();
	
	// Update
	void update(long id);
	void update(long id, long newTemperature, String newForecast);
	
	// Delete
	void delete(WeatherInfo weatherInfo);
	void delete(long id);
}
