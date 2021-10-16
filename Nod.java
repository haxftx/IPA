public class Nod
{ 
    Pasageri info;
    Nod st, dr, parent; 
    public Nod(Pasageri p) 
    { 
        this.info = p; 
        st = dr = parent = null;
    } 
}