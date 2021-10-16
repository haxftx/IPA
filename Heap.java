public class Heap {
	private Nod root;
	private VectPasageri v;
	public Heap(int n) {
		this.root = null;
		this.v = new VectPasageri(n);
	}
	 private int inaltime(Nod root) 
	    {//calculeaza inaltimea arborelui
	        if (root == null) 
	           return 0; 
	        else
	        { 
	            int stinaltime = inaltime(root.st); 
	            int drinaltime = inaltime(root.dr); 
	              
	            if (stinaltime > drinaltime) 
	                return(stinaltime+1); 
	            else return(drinaltime+1);  
	        } 
	    }
	 private Nod findNod(Nod root,int level) 
	    {//cauta nodul unde trebuie adaugat urmatorul
		 if(root != null) {
	       
			 if(level == 1 && (root.st == null || root.dr == null)) {
				 return root;
			 }
			 else {
				 Nod found = findNod(root.st, level - 1);
				 if(found == null)found = findNod(root.dr, level - 1);
				 return found;
			 }
		 }
	     return null;   
	    }
	 private Nod findlastNod(Nod root,int level) {//cauta ulitmul nod adaugat 
		 if(root != null) {
	       
			 if(level == 1 && frunza(root)) {
				 return root;
			 }
			 else {
				 Nod found = findlastNod(root.dr, level - 1);
				 if(found == null)found = findlastNod(root.st, level - 1);
				 return found;
			 }
		 }
	     return null;   
	    }
	 private Nod finddelNod(Nod root, Pasageri p) {//cauta un anumit nod 
		 if(root != null) {
			 if((p.getId()).equals(root.info.getId())) {
				 return root;
			 }
			 else {
				 Nod found = finddelNod(root.dr, p);
				 if(found == null)found = finddelNod(root.st, p);
				 return found;
			 }
		 }
	     return null;   
	    }
	 public void read(String id, Persoana p, int n) {
		 this.v.add(id, p, n);//adaug persoana citita
	 }
	 public Pasageri getPasager(String nume) {
		 return this.v.getPasageri(nume);
	 }
	 private boolean fcmp(Nod a, Nod b) {//compar pentru echilibrare
		 return a.info.Prioritate() > b.info.Prioritate();
	 }
	 private void swap(Nod a, Nod b) {
		 Pasageri t = a.info;
		 a.info = b.info;
		 b.info = t;
	 }
	 private boolean frunza(Nod a) {
		 return a.st == null && a.dr == null;
	 }
	 private void echilibrare(Nod root) {//echilibrez de la Nod
		 Nod p, t = root;
		 while(t != null) {
				if(frunza(t)) break;
				if(t.dr != null && t.st != null && fcmp(t.dr, t.st))
					p = t.dr;
				else if(t.st != null) p = t.st;
				else return;
				if(fcmp(p, t)) {
					swap(t, p);
				}
				else break;
				t = p;
			}
	 }
	 private void echilibr(Nod act) {//echilibrez spre root
		 while(act.parent != null && fcmp(act, act.parent)) {
			 swap(act, act.parent);
			 act = act.parent;
		 }
	 }
	 private String RSD(Nod root) {
		 if(root == null) return "";
		 return root.info.getId()+ " " + RSD(root.st) + RSD(root.dr);
	 }
	 public void insert(Pasageri p, int prioritate) {
		 if(root == null) {//daca nu exista nici un nod
			 this.root = new Nod(p);
			 return;
		 }
		 int h = inaltime(root);
		 Nod act = findNod(this.root, h - 1);
		 if(act == null) act = findNod(this.root, h);//caut nodul de adaugat
		 if(frunza(act)) {//daca e frunza adaug in stanga
			 act.st = new Nod(p);
			 act.st.parent = act;
			 act = act.st;
		 }
		 else if(act != null && act.dr == null){//adaug in dreapta
			 act.dr = new Nod(p);
			 act.dr.parent = act;
			 act = act.dr;
		 }
		 echilibr(act);
	 }
	 public void delete(Pasageri p) {
		 Nod del = finddelNod(root, p);
		 if(frunza(del)) {//daca e frunza
			 if(!p.isempty()) {
				 del.info.delPersoana(p.getfirstName());
			 }
			 else if(del.parent == null) {
				 this.root = null;
			 }
			 else {
				 Nod t = del;
				 del = del.parent;
				 if(del.st != null && t.info.equals(del.st.info))
					 del.st = null;
				 if(del.dr != null && t.info.equals(del.dr.info))
					 del.dr = null;
			 }
		 }
		 else if(del.parent == null) {//daca e root
			 if(p.isempty()) {
				 embark();
			 }
			 else {
				 this.root.info.delPersoana(p.getfirstName());
				 echilibrare(this.root);
			 }
		 }
		 else {//oricare nod din interior
			 if(p.isempty()) {//daca sterg grupul
				 Nod last = findlastNod(this.root, inaltime(this.root));
				 swap(last,del);
				 Nod t = last;
				 last = last.parent;
				 if(last.st != null && t.info.equals(last.st.info))
					 last.st = null;
				 if(last.dr != null && t.info.equals(last.dr.info))
					 last.dr = null;
			 }
			 else//daca sterg o persoana din grup
				 del.info.delPersoana(p.getfirstName());
			 //echilibrez
			 if(del.parent != null && fcmp(del.parent, del))
				 echilibrare(del);
			 else
				 echilibr(del);
		 }
	 }
	 public void embark() {
		 if(this.root == null)return;
		 if(frunza(this.root)) {
			 this.root = null;
			 return;
		 }
		 int h = inaltime(root);
		 Nod p = findlastNod(this.root, h);//caut ultimul nod
		 this.root.info = p.info;
		 Nod t = null ;
		 if(p.parent != null) t = p.parent;
		 else return;
		 //sterg ultimul nod
		 if(t.st != null && p.info.equals(t.st.info))
			 t.st = null;
		 if(t.dr != null && p.info.equals(t.dr.info))
			 t.dr = null;
		echilibrare(this.root);
		 
	 }
	 public void list() {
		  String s = RSD(this.root);//afisez infomatia din arbore
		  System.out.print(s.trim());
	 }
}