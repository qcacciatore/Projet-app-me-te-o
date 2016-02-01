package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Afficher {

	 	private static final Ville ville = null;
	 	static int swValue;
	    static String svSelect;
		static String svCrea;
		static double svLat;
		static double svLong;
		static String svChoix;
		static boolean continue_json = true;
		static ArrayList<Ville> tabVille = new ArrayList<Ville>();
		static String userLogin, userPassword, affichageLogin, affichageVille;
		static Socket client ;
		
	public static void main(String[] args) throws Exception {
		//afficherVille("quentin" , "Tours");
		ServerSocket socketserveur  ;
		ObjectInputStream in;
		ObjectInputStream in_affichage;
		BufferedReader in_login;
		PrintWriter out, out_json;
		boolean confirm = false;
		System.out.println(InetAddress.getLocalHost());
		
		try {
			
			socketserveur = new ServerSocket(3000);
			
			System.out.println("Le serveur est à l'écoute du port "+socketserveur.getLocalPort());
			client = socketserveur.accept();
			System.out.println("Connecté à un client : " +client.getInetAddress());
			
			boolean isCredentialsOk = false;
			while(isCredentialsOk == false)
			{
			in = new ObjectInputStream (client.getInputStream());
  	        ArrayList <String> login_mdp =  new ArrayList <String>();
  	        login_mdp =  (ArrayList<String>) in.readObject();
  	        userLogin = login_mdp.get(0);
  	        userPassword = login_mdp.get(1);
  	        
  	        System.out.println("Login posté : " +userLogin);
  	        System.out.println("Passmord posté : " +userPassword);
  	        
  	        isCredentialsOk = checkCredentials(userLogin);
  	        if (!isCredentialsOk)
  	        {
  	        	System.out.println("Login failed !");
  	        	confirm = false;
  	        }
  	        else {
  	        	System.out.println("Login succeeded !");
  	        	confirm = true;
  	        }
			}
  	        
  	        out = new PrintWriter(client.getOutputStream());
		    out.println(confirm);
		    System.out.println("Confirmation of connection sended");
		    out.flush();
  	      
  	        //in.close(); //si on close, le socket se ferme => programme stop(Why???)
  	        
  	        in_login = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        String login_recu = in_login.readLine();
	        //System.out.println("Le login recu pour afficher les villes est : "+login_recu);
	        
	        listerVille(login_recu);//envoyer les villes associées au login
	        
	        System.out.println("On va créer l'objet pour recevoir");
	        if(client.isClosed()==true){
	        	System.out.println("socket client is closed");
	        }
	        //System.out.println("Et la c'est le drame");
	        
	        while(continue_json == true){
	        
	        in_affichage = new ObjectInputStream (client.getInputStream());
	        System.out.println("création de l'objet pour recevoir");
  	        ArrayList <String> login_ville =  new ArrayList <String>();
  	        login_ville =  (ArrayList<String>) in_affichage.readObject();
  	        System.out.println("Tableau crée");
  	        affichageLogin = login_ville.get(0);
  	        affichageVille = login_ville.get(1);
	        
	        String json_final = afficherVille(affichageLogin, affichageVille);//envoyer au login le JSON d'une ville
	        out_json = new PrintWriter(client.getOutputStream());
		    out_json.println(json_final);
	        System.out.println("Json sended");
	        out_json.flush();
	        }
	        //client.close();
  	        //socketserveur.close();
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private static boolean checkCredentials(String userLogin) {
		boolean isUserCredentialsCorrect = false;
		Connexion connexion = new Connexion("weather_database.db");
		connexion.connect();
		ResultSet resultSetLogin = connexion.query("SELECT * FROM USERS");
		try {
			while (resultSetLogin.next()) {
				System.out.println("Searching login : " +resultSetLogin.getString("login"));
				if (resultSetLogin.getString("login").equals(userLogin))
				{
					// User exists.
		    		System.out.println("Login is in the database.");
					// Check his password.
		        ResultSet resultSetPassword = connexion.query("SELECT * FROM USERS WHERE LOGIN = '"+userLogin+"'");
		        
		        while (resultSetPassword.next()) {
		        	if (resultSetPassword.getString("password").equals(userPassword))
		        	{
		        		System.out.println("password is correct");
		        		isUserCredentialsCorrect = true;
		        	}
		        	else
		        	{
		        		System.out.println("password is wrong");
		        		isUserCredentialsCorrect = false;
		        	}
		        }
		       
		        break;
		        
				}
				else
				{
					isUserCredentialsCorrect = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
   
        //connexion.close();
		return isUserCredentialsCorrect;
	}
		
	   
	public void insertIntoVille(String nom, double lat, double longi, String login){
		int id_user=0;
		
		Connexion connexion = new Connexion("weather_database.db");
		connexion.connect();
		ResultSet resultID = connexion.query("SELECT ID FROM USERS WHERE LOGIN = '"+login+"'");
		id_user =  ((Number) resultID).intValue();
		connexion.query("INSERT INTO villes (nom, lat, long, id_user) VALUES ('"+nom+"', '"+lat+"', '"+longi+"', '"+id_user+"')");
	}
	
	public static void listerVille(String login) throws SQLException{
		ArrayList<String> listeVille = new ArrayList<String>();
		
		Connexion connexion = new Connexion("weather_database.db");
		connexion.connect();
		System.out.println("Requete IDsearchBylogin avec : "+login);
		ResultSet resultIDSet = connexion.query("SELECT ID FROM USERS WHERE LOGIN = '"+login+"'");
		int user_id = -1;
		
		while (resultIDSet.next()) {
			
        		user_id = resultIDSet.getInt("id");
        		System.out.println("Numéro ID trouvé : "+user_id);
      
        }
		
		if (user_id == -1)
		{
			System.out.println("Pas de ID associé à "+login);
			return;
		}
		
		ResultSet resultVille = connexion.query("SELECT NOM FROM VILLES WHERE ID_USER = '"+user_id+"'");
		try {
			while (resultVille.next()) {
				
	        		System.out.println("Ville trouvée : "+resultVille.getString("nom"));
	        		listeVille.add(resultVille.getString("nom"));
	        	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(ID_ville);
		//return listeVille;
		try {
			ObjectOutputStream objectOutput = new ObjectOutputStream(client.getOutputStream());
			objectOutput.writeObject(listeVille);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String afficherVille(String login, String nomVilleSelect) throws Exception{
		int user_id = -1;
		double get_lat = 0;
		double get_long = 0;
		
		Connexion connexion = new Connexion("weather_database.db");
		connexion.connect();
		
		System.out.println("Requete IDsearchBylogin avec : "+login);
		ResultSet resultIDSet = connexion.query("SELECT ID FROM USERS WHERE LOGIN = '"+login+"'");
		
		try {
			while (resultIDSet.next()) {
				user_id = resultIDSet.getInt("id");
				System.out.println("Numéro ID trouvé : "+user_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet resultVilleSet = connexion.query("SELECT LAT, LONG FROM VILLES WHERE NOM = '"+nomVilleSelect+"' AND ID_USER = '"+user_id+"'");
		
		try {
			while (resultVilleSet.next()) {
				get_lat = resultVilleSet.getDouble("lat");
				get_long = resultVilleSet.getDouble("long");
				System.out.println(nomVilleSelect+" : "+get_lat+" ,"+get_long);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Ville ville = new Ville(nomVilleSelect, get_lat, get_long);
		
		String json = RecupData.getForecastVille(ville);
		System.out.print("JSON : ");
		System.out.println(json);
		return json;
	}
	
	
}