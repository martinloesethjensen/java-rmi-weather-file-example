package example.weather;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Weather extends Remote {
	String getWeatherData(String city) throws RemoteException;
}
