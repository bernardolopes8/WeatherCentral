package pt.ipb.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pt.ipb.ejb.entity.WeatherInfo;
import pt.ipb.ejb.entity.Location;

/**
 * Session Bean implementation class WeatherInfoBean
 */
@Stateless
public class WeatherInfoBean implements WeatherInfoBeanRemote {
	@PersistenceContext(unitName = "tp2-pu")
    EntityManager em;

    /**
     * Default constructor.
     */
    public WeatherInfoBean() {
	// TODO Auto-generated constructor stub
    }

	@Override
	public long getTemperature(long id) {
		WeatherInfo weatherInfo = em.find(WeatherInfo.class, id);
		return weatherInfo.getTemperature();
	}

	@Override
	public String getForecast(long id) {
		WeatherInfo weatherInfo = em.find(WeatherInfo.class, id);
		return weatherInfo.getForecast();
	}

	@Override
	public long getWeatherInfoCount() {
		Query query = em.createQuery("select count(w) from WeatherInfo w");
		return (long) query.getSingleResult();
	}
	
	@Override
	public List<WeatherInfo> getAllWeatherInfo() {
		TypedQuery<WeatherInfo> query = em.createQuery("select w from WeatherInfo w ORDER BY w.location.name", WeatherInfo.class);
		return query.getResultList();
	}
	
	@Override
	public List<WeatherInfo> getAllWeatherInfoAscending() {
		TypedQuery<WeatherInfo> query = em.createQuery("select w from WeatherInfo w ORDER BY w.datetime", WeatherInfo.class);
		return query.getResultList();
	}

	@Override
	public WeatherInfo getWeatherInfo(long id) {
		return em.find(WeatherInfo.class, id);
	}

	@Override
	public WeatherInfo create(WeatherInfo weatherInfo) {
		em.persist(weatherInfo);
		return weatherInfo;
	} 

	@Override
	public Date getDateTime(long id) {
		WeatherInfo weatherInfo = em.find(WeatherInfo.class, id);
		return weatherInfo.getDatetime();
	}

	@Override
	public String getTemperatureString(long id) {
		return getTemperature(id) + " ºC";
	}

	@Override
	public long create(long temperature, String forecast, Date datetime, Location location) {
		WeatherInfo weatherInfo = new WeatherInfo(temperature, forecast, datetime, location);
		em.persist(weatherInfo);
		return weatherInfo.getId();
	}

	@Override
	public void update(long id) {
		WeatherInfo weatherInfo = em.find(WeatherInfo.class, id);
		
		weatherInfo = em.merge(weatherInfo);
		weatherInfo.setTemperature(weatherInfo.getTemperature());
		weatherInfo.setForecast(weatherInfo.getForecast());
		weatherInfo.setDatetime(weatherInfo.getDatetime());
		weatherInfo.setLocation(weatherInfo.getLocation());
	}

	@Override
	public void update(long id, long newTemperature, String newForecast) {
		WeatherInfo weatherInfo = em.find(WeatherInfo.class, id);
		
		weatherInfo = em.merge(weatherInfo);
		weatherInfo.setTemperature(newTemperature);
		weatherInfo.setForecast(newForecast);
	}

	@Override
	public void delete(WeatherInfo weatherInfo) {
		weatherInfo = em.merge(weatherInfo);
		em.remove(weatherInfo);
	}

	@Override
	public void delete(long id) {
		delete(getWeatherInfo(id));
	}
}
