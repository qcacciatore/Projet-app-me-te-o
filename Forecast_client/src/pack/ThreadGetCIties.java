package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ThreadGetCIties extends Thread {

	private static CityCallBack mCallback;
	private static String mUserLogin;
	private static String mIp;
	private static Socket mSocketServer;

	public ThreadGetCIties(String userLogin, String ip,Socket socketServer, CityCallBack callback) {
		mCallback = callback;
		mUserLogin = userLogin;
		mIp = ip;
		mSocketServer = socketServer;
	}

	public void run() {
		System.out.println("Running thread...");
		getUserCities(mUserLogin, mSocketServer);
	}

	
	private void getUserCities(String userLogin, Socket socketServer)
	{
		Socket socket;
		ObjectInputStream in_resultat;
		// PrintWriter enables to send a message.
		PrintWriter out;

		try {
			
			if (socketServer.isConnected() == false)
			{
				System.out.println("Not connected to server");
				return;
					
			}
			System.out.println("Creating socket on port "+socketServer.getPort()+", at adress"+socketServer.getInetAddress());
			
		
			
			out = new PrintWriter(socketServer.getOutputStream());
			out.println(userLogin);
			out.flush();

			in_resultat = new ObjectInputStream(socketServer.getInputStream());
			try {
                Object object = in_resultat.readObject();
                ArrayList<String> arrayList =  (ArrayList<String>) object;
                mCallback.onCitiesRetrieveComplete(arrayList);
                
            } catch (ClassNotFoundException e) {
                System.out.println("The title list has not come from the server");
                e.printStackTrace();
            }
			
			//socketServer.close();

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
		
	
}
