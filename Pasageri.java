public class Pasageri {
	private Persoana[] v;
	private int index;
	private String id;
	public Pasageri(int n) {
		this.index = 0;
		this.v = new Persoana[n];
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void add(Persoana p) {
		this.v[(this.index)++] = p;
	}
	public int getIndex() {
		return this.index;
	}
	public boolean isempty() {
		return this.index == 0;
	}
	public String getfirstName() {
		return this.v[0].getNume();
	}
	public void delPersoana(String nume) {
		for(int i = 0 ; i < this.index; i++) {
			if((this.v[i].getNume()).equals(nume)) {
				for( ; i < this.index - 1; i++) {
					this.v[i] = this.v[i+1];
				}
				this.index --;
			}
		}
	}
	public int Prioritate() {
		int p = 0;
		if(this.id.charAt(0) == 'f')
			p += 10;
		if(this.id.charAt(0) == 'g')
			p +=5;
		for(int i = 0 ;i < index; i++)
			p += this.v[i].getPrioritate();
		return p;
	}
}