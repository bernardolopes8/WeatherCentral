package pt.ipb.ejb.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: WeatherInfo
 *
 */
@Entity
public class WeatherInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long temperature; // Temperature in Celsius
	private String forecast; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Location location;
	
	public WeatherInfo() {
		super();
	}
	
	public WeatherInfo(long temperature, String forecast, Date datetime, Location location) {
		this.temperature = temperature;
		this.forecast = forecast;
		this.datetime = datetime;
		this.location = location;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getTemperature() {
		return temperature;
	}
	
	public String getForecast() {
		return forecast;
	}
	
	public Date getDatetime() {
		return datetime;
	}
	
	public String getDatestring() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.format(datetime);
	}
	
	public void setTemperature(long temperature) {
		this.temperature = temperature;
	}
	
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
}