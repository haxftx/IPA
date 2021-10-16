public class Persoana {
	private String nume;
	private int varsta;
	private char tip_bilet;
	private boolean i_p, n_s;
	public Persoana(String nume, int varsta, char tip_bilet, boolean i_p, boolean n_s) {
		this.nume = nume;
		this.varsta = varsta;
		this.tip_bilet = tip_bilet;
		this.i_p = i_p;
		this.n_s = n_s;
	}
	public String getNume() {
		return this.nume;
	}
	public int getPrioritate() {
		int p = 0;
		if(this.varsta < 2)
			p += 20;
		if(this.varsta >= 2 && this.varsta < 5)
			p += 10;
		if(this.varsta >= 5 && this.varsta < 10)
			p += 5;
		if(this.varsta >= 60)
			p += 15;
		
		if(tip_bilet == 'b')
			p += 35;
		if(tip_bilet == 'p')
			p += 20;
		
		if(this.i_p)
			p += 30;
		if(this.n_s)
			p += 100;
		return p;
	}
}