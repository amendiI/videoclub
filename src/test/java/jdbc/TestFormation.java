package jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import jdbc.dao.DaoFormation;
import jdbc.dao.DaoFormationFactory;
import jdbc.model.Formation;

public class TestFormation {

	public static void main(String[] args) {
		DaoFormation daoFormation = DaoFormationFactory.getInstance();

		System.out.println("findAll:" + daoFormation.findAll());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Formation f;
		try {
			f = new Formation("formation test", sdf.parse("12/03/2020"));
			daoFormation.insert(f);

			f = new Formation("ca marche pas");
			daoFormation.insert(f);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
