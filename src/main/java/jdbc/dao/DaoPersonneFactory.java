package jdbc.dao;

public class DaoPersonneFactory {

	private static DaoPersonne daoPersonne = null;

	public static DaoPersonne getInstance() {
		if (daoPersonne == null) {
			daoPersonne = new DaoPersonneJdbcImpl();
		}
		return daoPersonne;
	}
}
