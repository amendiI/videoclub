package model;

public class Adherent {

	// attributs
	private Integer id;
	private String prenom;
	private String nom;
	
	// constructeurs
	
	public Adherent(Integer id, String prenom, String nom) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}
	
	// methodes

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	
}
