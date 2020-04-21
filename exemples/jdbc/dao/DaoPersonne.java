package jdbc.dao;

import java.util.List;

import jdbc.model.Formateur;
import jdbc.model.Personne;

public interface DaoPersonne extends DaoGeneric<Personne, Integer> {
	List<Personne> findByPrenom(String prenom);

	List<Formateur> findAllFormateur();
}
