package pt.ipb.sd.jsf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import pt.ipb.ejb.LocationBeanRemote;
import pt.ipb.ejb.WeatherInfoBeanRemote;
import pt.ipb.ejb.entity.Location;
import pt.ipb.ejb.entity.WeatherInfo;

@ManagedBean
@RequestScoped
public class WeatherInfoBackingBean {
	@EJB
    WeatherInfoBeanRemote weatherInfo;
	
	@EJB
	LocationBeanRemote locations;
	
	private long createLocationId;
	private long viewLocationId;
	
	private String forecast;
	private long temperature;
	private String date;

	public long getCreateLocationId() {
		return createLocationId;
	}

	public void setCreateLocationId(long createLocationId) {
		this.createLocationId = createLocationId;
	}

	public long getViewLocationId() {
		return viewLocationId;
	}

	public void setViewLocationId(long viewLocationId) {
		this.viewLocationId = viewLocationId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public long getTemperature() {
		return temperature;
	}

	public void setTemperature(long temperature) {
		this.temperature = temperature;
	}
	
	public Date convertDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.parse(date);
	}
	
	public List<WeatherInfo> getAllWeatherInfo() {
		if (viewLocationId == 0) return weatherInfo.getAllWeatherInfo();
		else {
			Location location = locations.getLocation(viewLocationId);
			return locations.getAllWeatherInfo(location);
		}
	}
	
	public String createWeatherInfo() throws ParseException {
		Location location = locations.getLocation(createLocationId);
		WeatherInfo weather = new WeatherInfo(temperature, forecast, convertDate(), location);
		weather.setLocation(location);
		location.getWeatherInfoList().add(weather);
		weather = weatherInfo.create(weather);
		return "weather.jsf";
	}
	
	public void deleteWeatherInfo(WeatherInfo info) {
    	weatherInfo.delete(info);
    }
}
