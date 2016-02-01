package pack;

public class Ville {
	
	String nomVille;
	String pNom;
	String nomPays, pPays;
	double temp, pTemp;
	double latVille, longVille;
	String prevision, pPrevision;
	String timezone;
	
	//Constructeur 
	public Ville(String pNom, double pLat, double pLong){
		
		latVille = pLat;
		longVille = pLong;
		nomVille = pNom;
		nomPays = pPays;
		temp = pTemp;
		prevision = pPrevision;
	}
	
	//Constructeur par défaut
	public Ville(){
		
		latVille = 0;
		longVille = 0;
		nomVille = null; 
		nomPays = null;
		temp = 0;
		prevision = null;
	}
	
	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}
	
	public double getLatVille() {
		return latVille;
	}
	
	public double getLongVille() {
		return longVille;
	}
	
}
