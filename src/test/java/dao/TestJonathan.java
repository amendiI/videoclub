package dao;

import model.Adherent;
import model.Adresse;
import model.Civilite;

public class TestJonathan {
	public static void main(String[] args) {

		
		Adresse adJo= new Adresse(3, "rue", "78888","lalaland");

		Adherent jo = new Adherent(1,"jo","ts",Civilite.M,adJo);
		System.out.println(jo.getNom());
		System.out.println(jo.getId());
		System.out.println(jo.getPrenom());
		System.out.println(jo.getAdresse().toString());
		
	}
}
