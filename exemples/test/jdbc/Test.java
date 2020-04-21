package jdbc;

import java.util.Optional;

import jdbc.dao.DaoPersonne;
import jdbc.dao.DaoPersonneFactory;
import jdbc.model.Civilite;
import jdbc.model.Formateur;
import jdbc.model.Personne;
import jdbc.model.Stagiaire;

public class Test {

	public static void main(String[] args) {
		DaoPersonne daoPersonne = DaoPersonneFactory.getInstance();

		Optional<Personne> opt = daoPersonne.findByKey(6);
		if (opt.isPresent()) {
			System.out.println(((Stagiaire) opt.get()));
		} else {
			System.out.println("personne");
		}

	}

}

//3 solutions

//1 table:
//toutes les donnees personne+formateur+stagiaire+colonne discriminante

//2 tables:
//formateur + stagiaire

//3 tables à eviter
// personne id
// formateur id
// stagiaire id
