package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Film {
	private Integer id;
	private String titre;
	private Date dateDeSortie;
	ArrayList<Realisation> realisations;
	HashSet<Article> articles;
	
	
	
	
	
	



	public Film() {
	}



	public Film(Integer id, String titre, Date dateDeSortie) {
		this.id = id;
		this.titre = titre;
		this.dateDeSortie = dateDeSortie;
		this.articles = new HashSet<Article>();
		this.realisations = new ArrayList<Realisation>();
	}
	
	public void addArticles(Article e) {
		articles.add(e);
	}
	public void addRealisations(Realisation f) {
		realisations.add(f);
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Date getDateDeSortie() {
		return dateDeSortie;
	}


	public void setDateDeSortie(Date dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}
	
	
	public ArrayList<Realisation> getRealisations(){
		return null;
	}
	
	public void setRealisations(ArrayList<Realisation> realisations) {
		this.realisations = realisations;
	}

	
	
	public Article getArticles(){
		return null;
		
	}
	
	public void setArticles(){
		
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articles == null) ? 0 : articles.hashCode());
		result = prime * result + ((dateDeSortie == null) ? 0 : dateDeSortie.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((realisations == null) ? 0 : realisations.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		Film other = (Film) obj;
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		if (dateDeSortie == null) {
			if (other.dateDeSortie != null)
				return false;
		} else if (!dateDeSortie.equals(other.dateDeSortie))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (realisations == null) {
			if (other.realisations != null)
				return false;
		} else if (!realisations.equals(other.realisations))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	
	

	
}
