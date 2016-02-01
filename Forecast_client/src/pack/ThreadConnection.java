package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class ThreadConnection extends Thread {
	
	static private ArrayList <String> mCredentialsList;
	static private String mLogin;
	static private String mPassword;
	static private String mIp;
	static private CallBack mCallBack;
	static private Socket mSocketServer;

	public ThreadConnection(String login, String password, String ip,Socket socketServer, CallBack callback)
	{
		System.out.println("Creating thread...");
		mLogin = login;
		mPassword = password;
		mIp = ip;
		mCallBack = callback;
		mSocketServer = socketServer;
	}
	
	public void run()
	{
		System.out.println("Running thread...");
		connectToServerAtIP(mIp,mLogin, mPassword,mCallBack, mSocketServer);
	}
	
	private static void connectToServerAtIP(String ip,String login, String password, CallBack callback, Socket socketServer) {
		Socket socket;
		BufferedReader in_resultat;
		// PrintWriter enables to send a message.
		ObjectOutputStream out;

		try {
			
			if (socketServer.isConnected() == false)
			{
				System.out.println("Not connected");
				return;
					
			}
			System.out.println("Creating socket on port "+socketServer.getPort()+", at adress"+socketServer.getInetAddress());
			
		ArrayList<String> credentialList = new ArrayList <String>();
		credentialList.add(login);
		credentialList.add(password);
			
			out = new ObjectOutputStream(socketServer.getOutputStream());
			// Send array with login and password.
			out.writeObject(credentialList);
			out.flush();

			in_resultat = new BufferedReader ( new InputStreamReader ( socketServer.getInputStream()));
			String isLoginOk = in_resultat.readLine();
			System.out.println("login result :"+isLoginOk);
			if (isLoginOk.equals("true"))
			{
				// Callback here.
				System.out.println("good credentials matching.");
				mCallBack.onLoginComplete(true);
			}
			else if(isLoginOk.equals("false"))
			{
				// Callback here
				mCallBack.onLoginComplete(false);
				System.out.println("Wrong matching credentials.");
			}
			
			else
			{
				System.out.println("Server sends a wrong result. Try again.");
				mCallBack.onLoginComplete(false);
			}

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
}
