package model;

public abstract class Article {
	
	protected Integer id;
	protected Integer nbDisque;
	protected Adherent emprunteur;
	protected Film film;
	
	
	public Article(Integer id, Integer nbDisque, Adherent emprinteur, Film film) {
		super();
		this.id = id;
		this.nbDisque = nbDisque;
		this.emprunteur = emprinteur;
		this.film = film;
	}


	public Article() {
		super();
	}


	public Integer getNbDisque() {
		return nbDisque;
	}


	public void setNbDisque(Integer nbDisque) {
		this.nbDisque = nbDisque;
	}


	public Adherent getEmprunteur() {
		return emprunteur;
	}


	public void setEmprinteur(Adherent emprinteur) {
		this.emprunteur = emprinteur;
	}


	public Film getFilm() {
		return film;
	}


	public void setFilm(Film film) {
		this.film = film;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emprunteur == null) ? 0 : emprunteur.hashCode());
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nbDisque == null) ? 0 : nbDisque.hashCode());
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
		Article other = (Article) obj;
		if (emprunteur == null) {
			if (other.emprunteur != null)
				return false;
		} else if (!emprunteur.equals(other.emprunteur))
			return false;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nbDisque == null) {
			if (other.nbDisque != null)
				return false;
		} else if (!nbDisque.equals(other.nbDisque))
			return false;
		return true;
	}
	
	
	
	
}
