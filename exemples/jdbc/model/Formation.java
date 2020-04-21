package jdbc.model;

import java.util.Date;

public class Formation {
	private Integer id;
	private String nom;
	private Date dateFormation;

	public Formation(Integer id, String nom, Date dateFormation) {
		this.id = id;
		this.nom = nom;
		this.dateFormation = dateFormation;
	}

	public Formation(String nom, Date dateFormation) {
		this.nom = nom;
		this.dateFormation = dateFormation;
	}

	public Formation(Integer id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Formation(String nom) {
		this.nom = nom;
	}

	public Formation() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateFormation() {
		return dateFormation;
	}

	public void setDateFormation(Date dateFormation) {
		this.dateFormation = dateFormation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formation other = (Formation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
