package model;

public class Blueray extends Article {
	
	private boolean troisD;

	public boolean isTroisD() {
		return troisD;
	}

	public void setTroisD(boolean troisD) {
		this.troisD = troisD;
	}

	public Blueray(Integer id, Integer nbDisque, Adherent emprinteur, Film film, boolean troisD) {
		super(id, nbDisque, emprinteur, film);
		this.troisD = troisD;
	}
	
	
	
}
