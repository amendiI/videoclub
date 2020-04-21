package jdbc.model;

public abstract class Personne {
	private Integer id;
	private String prenom;
	private String nom;
	private Civilite civilite;

	public Personne(String prenom, String nom, Civilite civilite) {
		this.prenom = prenom;
		this.nom = nom;
		this.civilite = civilite;
	}

	public Personne(Integer id, String prenom, String nom, Civilite civilite) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.civilite = civilite;
	}

	public Personne(Integer id, String prenom, String nom) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Personne(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
	}

	public Personne() {

	}

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

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

}
