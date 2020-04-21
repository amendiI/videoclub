package Dao;

public class DaoRealisateurFactory {
	public static DaoRealisateur realisateur = null;
	
	public static DaoRealisateur getInstance() {
		if(realisateur == null) {
			realisateur = new DaoRealisateurJdbcImpl();
		}
		return realisateur;
	}
}
