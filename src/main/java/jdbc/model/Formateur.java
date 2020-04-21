package jdbc.model;

public class Formateur extends Personne {
	private Integer experience;

	public Formateur() {
		super();
	}

	public Formateur(Integer id, String prenom, String nom, Civilite civilite) {
		super(id, prenom, nom, civilite);
	}

	public Formateur(Integer id, String prenom, String nom) {
		super(id, prenom, nom);
	}

	public Formateur(String prenom, String nom, Civilite civilite) {
		super(prenom, nom, civilite);
	}

	public Formateur(String prenom, String nom) {
		super(prenom, nom);
	}

	public Formateur(String prenom, String nom, Civilite civilite, Integer experience) {
		super(prenom, nom, civilite);
		this.experience = experience;
	}

	public Formateur(String prenom, String nom, Integer experience) {
		super(prenom, nom);
		this.experience = experience;
	}

	public Formateur(Integer id, String prenom, String nom, Civilite civilite, Integer experience) {
		super(id, prenom, nom, civilite);
		this.experience = experience;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

}
