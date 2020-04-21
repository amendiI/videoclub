package model;

public class Dvd extends Article {
	
	private Boolean bonus;
	
	
	
	public Dvd(Integer id, Integer nbDisque, Adherent emprinteur, Film film, Boolean bonus) {
		super(id, nbDisque, emprinteur, film);
		this.bonus = bonus;
	}

	public Boolean getBonus() {
		return bonus;
	}

	public void setBonus(Boolean bonus) {
		this.bonus = bonus;
	}
	
	
	
}
