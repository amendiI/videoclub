package jdbc.model;

public class Stagiaire extends Personne {
	private Formation formation;

	public Stagiaire() {
		super();
	}

	public Stagiaire(Integer id, String prenom, String nom, Civilite civilite) {
		super(id, prenom, nom, civilite);
	}

	public Stagiaire(Integer id, String prenom, String nom) {
		super(id, prenom, nom);
	}

	public Stagiaire(String prenom, String nom, Civilite civilite) {
		super(prenom, nom, civilite);
	}

	public Stagiaire(String prenom, String nom) {
		super(prenom, nom);
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

}
