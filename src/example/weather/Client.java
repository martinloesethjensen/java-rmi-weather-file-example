//Client.java
package example.weather;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);

            Weather stub = (Weather) registry.lookup("Weather");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Input city or input ':q' to quit");

            while (true) {
                System.out.print("City: ");
                var input = scanner.nextLine();

                if (input.equals(":q")) break;

                String response = stub.getWeatherData(input);

                System.out.println("response: " + response);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
