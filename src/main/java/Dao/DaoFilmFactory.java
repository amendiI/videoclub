package Dao;

public class DaoFilmFactory {
	private static DaoFilm film = null;
	
	public static DaoFilm getInstance() {
		if(film == null) {
			film = new DaoFilmJdbcImpl();
		}
		return film;
	}
}
