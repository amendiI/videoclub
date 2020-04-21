package jdbc.dao;

public class DaoFormationFactory {
	private static DaoFormation singleton = null;

	public static DaoFormation getInstance() {
		if (singleton == null) {
			singleton = new DaoFormationJdbcImpl();
		}
		return singleton;
	}
}
