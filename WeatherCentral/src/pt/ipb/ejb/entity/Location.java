package pt.ipb.ejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity
public class Location implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique=true)
	private String name;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="location", cascade = CascadeType.ALL)
	private List<WeatherInfo> weatherInfoList;
	
	public Location() {
		super();
	}
	
	public Location(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<WeatherInfo> getWeatherInfoList() {
		return weatherInfoList;
	}
	
	public void setWeatherInfoList(List<WeatherInfo> weatherInfoList) {
		this.weatherInfoList = weatherInfoList;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}