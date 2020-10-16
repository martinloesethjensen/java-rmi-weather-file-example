package example.weather;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server implements Weather {
	@Override
	public String getWeatherData(String city) {
		String data = null;
		try {
			File file = new File("weather_data.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);
				if (line.toLowerCase().startsWith(city.toLowerCase())) {
					data = line;
					break;
				}
			}

			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data == null ? "Found no weather data from city: " + city : data;
	}

	public static void main(String args[]) {
		try {
			Server obj = new Server();
			Weather stub = (Weather) UnicastRemoteObject.exportObject(obj, 0);

			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("Weather", stub);

			System.out.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
