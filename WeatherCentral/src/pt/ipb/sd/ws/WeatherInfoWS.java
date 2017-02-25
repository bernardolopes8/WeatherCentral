package pt.ipb.sd.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import pt.ipb.ejb.WeatherInfoBeanRemote;
import pt.ipb.ejb.entity.WeatherInfo;

@Path("/WeatherInfoService")
public class WeatherInfoWS {
	@EJB
	WeatherInfoBeanRemote weatherInfo;

	@GET
	@Path("/record")
	@Produces("application/json")
	public List<WeatherInfo> getAllWeatherInfo() {
		return weatherInfo.getAllWeatherInfo();
	}
	
	@GET
	@Path("/recordAscending")
	@Produces("application/json")
	public List<WeatherInfo> getAllWeatherInfoAscending() {
		return weatherInfo.getAllWeatherInfoAscending();
	}
}
