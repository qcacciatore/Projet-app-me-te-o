package pack;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Afficher extends JFrame {

	static private LoginFrame loginForm;
	static private CityWindow cityWindow;

	static private Socket mSocketServer;

	private static final Ville ville = null;
	static int swValue;
	static String svSelect;
	static String svCrea;
	static double svLat;
	static double svLong;
	static String svChoix;
	static boolean continueMenu = true;
	static ArrayList<Ville> tabVille = new ArrayList<Ville>();
	private static final String ipServer = "92.139.70.7";
	private static final String ipServer2 = "192.168.1.40";
	private static Scanner sc = new Scanner(System.in);
	private static ActionListener l;

	public static void main(String[] args) throws Exception {

		// Create socket.
		mSocketServer = new Socket();
		mSocketServer.connect(new InetSocketAddress(ipServer2, 3000), 20000);

		dislayLoginForm();
		// getUserCities("quentin", ipServer2);
	}

	private static void connectToServerAtIP(String ip, final String login,
			String password) {
		ThreadConnection threadConnection = new ThreadConnection(login,
				password, ip,mSocketServer, new CallBack() {
					@Override
					public void onLoginComplete(boolean isLoginOk) {
						System.out.println("Login complete");

						if (isLoginOk == true) {
							System.out.println("" + login + " is connected");
							// Close frame.
							//loginForm.dispatchEvent(new WindowEvent(loginForm, WindowEvent.WINDOW_CLOSING));
							cityWindow = new CityWindow();
							getUserCities(login, ipServer2);

						} else {
							System.out.println("Connection failed");
						}
					}
				});
		threadConnection.start();
	}

	private static void getUserCities(final String userLogin, String ip) {
		ThreadGetCIties threadGetCities = new ThreadGetCIties(userLogin, ip, mSocketServer,
				new CityCallBack() {

					@Override
					public void onCitiesRetrieveComplete(ArrayList<String> citiesList) { // retrieve list over
						System.out.println("Retrieve cities list");
						System.out.println(citiesList);
						// Update list with array of cities.
						cityWindow.updateListWithData(citiesList);
						// Update GUI.
						//getWeatherInfosJSON(userLogin, "Marseille");
						cityWindow.getListCities().getSelectionModel().addListSelectionListener(new ListSelectionListener(){

							@Override
							public void valueChanged(ListSelectionEvent e) {
								if (!e.getValueIsAdjusting()) {//This line prevents double events

								// TODO Auto-generated method stub
									cityWindow.launchProgressBar();
								String selectedValue = (String) cityWindow.getListCities().getSelectedValue();
								cityWindow.setLblCity(selectedValue);
								
								// get weather infos.
								getWeatherInfosJSON(userLogin, selectedValue);
							}
							}
						});

					}

				});
		threadGetCities.start();
	}
	
	private static void getWeatherInfosJSON(String userLogin, String city)
	{
		ThreadGetWeatherJSON threadWeatherJSON = new ThreadGetWeatherJSON(userLogin, city, mSocketServer, new WeatherJSONCallBack()
		{

			@Override
			public void onWeatherJSONRetrieveComplete(String json) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("Retrieve json weather infos");
				cityWindow.stopProgressBar();
				Forecast forecast = RecupData.getForecastVille(json);
				
				// Update GUI (temperature,image, ...).
				cityWindow.updateCityWeather(forecast);
				
			}
			
		});
		threadWeatherJSON.start();
	}

	private static void dislayLoginForm() {
		loginForm = new LoginFrame(400, 150);
		loginForm.getLoginButton().addActionListener(new ActionListener() // Add
																			// a
																			// listener
																			// on
																			// login
																			// button
				{

					@Override
					public void actionPerformed(ActionEvent arg0) { // When
																	// clicked
																	// on login
																	// button
						String strLogin = loginForm.getLoginText(); // Get login
						if (strLogin.isEmpty()) { // Check if is empty
							System.out.println("Empty login... retry.......");
							return;
						}
						// Login is not empty.
						System.out.println("Login entered :" + strLogin);

						String strPassword = loginForm.getPasswordText(); // get
																			// password
						if (strPassword.isEmpty()) { // Check if is empty
							System.out.println("Empty password... retry....");
							return;
						}
						// passwor dis not empty.
						System.out.println("Password entered :" + strPassword);
						connectToServerAtIP(ipServer2, strLogin, strPassword); // Connect
																				// user
																				// to
																				// app
																				// by
																				// sending
																				// login
																				// +
																				// password
					}

				});

		loginForm.getCancelButton().addActionListener(new ActionListener() { // Add
																				// a
																				// listener
																				// on
																				// password
																				// button

					@Override
					public void actionPerformed(ActionEvent arg0) { // if click
						loginForm.dispatchEvent(new WindowEvent(loginForm,
								WindowEvent.WINDOW_CLOSING)); // Exit app, by
																// closing
																// frame.

					}

				});

	}

	public static void menu() throws Exception {
		// Display menu graphics
		System.out.println("============================");
		System.out.println("|      FORECAST MENU       |");
		System.out.println("============================");
		System.out.println("| Options:                 |");
		System.out.println("|     1. Lister villes     |");
		System.out.println("|     2. Cr�er ville       |");
		System.out.println("|     3. Afficher ville    |");
		System.out.println("|     4. Exit              |");
		System.out.println("============================");
		swValue = Keyin.inInt(" Select option: ");

		// Switch construct
		switch (swValue) {

		case 1:
			System.out.println(" Liste villes:");

			for (int i = 0; i < tabVille.size(); i++) {
				Ville currentVille = tabVille.get(i);
				System.out.println((i + 1) + ") " + currentVille.getNomVille());
			}
			break;

		case 2:
			svCrea = Keyin.inString(" Nom de la ville:");
			svLat = Keyin.inDouble(" Latitude de la ville:");
			svLong = Keyin.inDouble(" Longitude de la ville:");
			Ville newVille = new Ville(svCrea, svLat, svLong);
			tabVille.add(newVille);
			break;

		case 3:
			svChoix = Keyin.inString(" Nom de la ville choisie:");

			break;

		case 4:
			System.out.println("Exit selected");
			continueMenu = false;
			break;

		default:
			System.out.println("Invalid selection");
			break; // This break is not really necessary
		}
	}

	static Ville getVilleFromName(String villeName) {
		for (Ville ville : tabVille) {
			if (ville.getNomVille().equals(villeName)) {
				System.out.println("Found Ville object, name :" + villeName);
				return ville;
			}
		}
		System.out.println("Did not found Ville that matches with name");
		return null;
	}

}