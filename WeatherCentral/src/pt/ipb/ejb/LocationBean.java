package pt.ipb.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pt.ipb.ejb.entity.WeatherInfo;
import pt.ipb.ejb.entity.Location;

/**
 * Session Bean implementation class LocationBean
 */
@Stateless
public class LocationBean implements LocationBeanRemote {

	@PersistenceContext(unitName = "tp2-pu")
    EntityManager em;
	
    /**
     * Default constructor.
     */
    public LocationBean() {
	// TODO Auto-generated constructor stub
    }
	
	@Override
	public Location getLocation(long id) {
		Location location = em.find(Location.class, id);
		return location;
	}
	
	@Override
	public Location getLocation(String name) {
		TypedQuery<Location> query = em.createQuery("select l from Location l where l.name = :name", Location.class);
		Location location = query.setParameter("name", name).getSingleResult();
		return location;
	}

	@Override
	public long getLocationCount() {
		Query query = em.createQuery("select count(l) from Location l");
		return (long) query.getSingleResult();
	}

	@Override
	public String getLocationName(long id) {
		return em.find(Location.class, id).getName();
	}

	@Override
	public List<Location> getLocationList() {
		TypedQuery<Location> query = em.createQuery("select l from Location l ORDER BY l.name", Location.class);
		return query.getResultList();
	}

	@Override
	public void create(Location location) {
		em.persist(location);
	}
	
	@Override
	public void create(String name) {
		Location location = new Location(name);
		em.persist(location);
	}

	@Override
	public List<WeatherInfo> getAllWeatherInfo(Location location) {
		TypedQuery<WeatherInfo> query = em.createQuery("select l.weatherInfoList from Location l WHERE l.id = :id AND l.weatherInfoList IS NOT EMPTY", WeatherInfo.class);
		query.setParameter("id", location.getId());
		return query.getResultList();
	}

	@Override
	public void update(long id, String newName) {
		Location location = em.find(Location.class, id);
		location = em.merge(location);
		location.setName(newName);
	}

	@Override
	public void update(long id) {
		Location location = em.find(Location.class, id);
		location = em.merge(location);
		location.setName(location.getName());
	}
	
	@Override
	public void update(Location location) {
		location = em.merge(location);
		location.setName(location.getName());
	}

	@Override
	public void delete(Location location) {
		location = em.merge(location);
		em.remove(location);
	}

	@Override
	public void delete(long id) {
		delete(getLocation(id));
	}
}
