package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ThreadGetWeatherJSON extends Thread {

	private static String mLogin;
	private static String mCity;
	private static WeatherJSONCallBack mCallback;
	private static Socket mSocketServer;

	public ThreadGetWeatherJSON(String login, String city, Socket socketServer,
			WeatherJSONCallBack callback) {
		mLogin = login;
		mCity = city;
		mCallback = callback;
		mSocketServer = socketServer;
	}

	public void run() {
		System.out.println("Running weather json thread...");
		try {
			getWeatherJSON(mLogin, mCity, mSocketServer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getWeatherJSON(String login, String city, Socket socketServer) throws Exception {
		BufferedReader in_resultat;
		ObjectOutputStream out;

		try {

			if (socketServer.isConnected() == false) {
				System.out.println("Not connected");
				return;

			}
			
			if (socketServer.isClosed() == true) {
				System.out.println("socket closed");
				return;

			}
			
			System.out.println("Preparing datas to send");
			ArrayList<String> weatherParams = new ArrayList<String>();
			weatherParams.add(login);
			weatherParams.add(city);

			System.out.println("Sending params to socket...");
			out = new ObjectOutputStream(socketServer.getOutputStream());
			// Send array with login and password.
			out.writeObject(weatherParams);
			out.flush();
			
			System.out.println("Waiting for json...");
			in_resultat = new BufferedReader(new InputStreamReader(
					socketServer.getInputStream()));
			String WeatherJSONString = in_resultat.readLine();
			System.out.println("weather jsont :" + WeatherJSONString);

			if (WeatherJSONString.equals(null)) {
				mCallback.onWeatherJSONRetrieveComplete("indisponible");
			} else {
				mCallback.onWeatherJSONRetrieveComplete(WeatherJSONString);
			}

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
