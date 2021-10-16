public class VectPasageri {
	private Pasageri[] v;
	private int index;
	public VectPasageri(int n) {
		this.v = new Pasageri[n];
		this.index = 0;
	}
	public void add(String id, Persoana p, int n) {
		boolean ad = false;
		for(int i = 0; i < this.index; i++) {
			if(id.equals(this.v[i].getId())) {
				this.v[i].add(p);
				ad = true;
				break;
			}
		}
		if(!ad) {
			if(id.charAt(0) == 'f')
				this.v[index] =new  Familie(n, id);
			if(id.charAt(0) == 'g')
				this.v[index] =new  Grup(n, id);
			if(id.charAt(0) == 's')
				this.v[index] =new  Singur(id);
				
			this.v[index++].add(p);
		}
		
	}
	public Pasageri getPasageri(String id) {
		for(int i = 0; i < this.index; i++)
		{
			if(id.equals(this.v[i].getId()))
				return this.v[i];
		}
		return null;
	}
}