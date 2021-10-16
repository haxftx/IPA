import java.io.*;
import java.util.Scanner;

public class In_Out {
	public static void main(String[] arg) throws IOException  {
		File file = new File("queue.in"); 
		Scanner sc = new Scanner(file);
		sc.useDelimiter("");
		if(!sc.hasNext()) {//verific daca nu e gol fisierul
			sc.close();
			return;
		}
		int n = Integer.parseInt(sc.nextLine());
		String line;
		Heap heap = new Heap(n);
		boolean firstlist = false;
		String[] input = new String[10];
		Pasageri pasager;
		for(int i = 0; i < n; i++) {//citesc persoanele din fisier
			if(!(sc.hasNextLine())) break;
			line = sc.nextLine();
			input = line.split(" ");
			Persoana p = new Persoana(input[1], Integer.parseInt(input[2]), input[3].charAt(0), Boolean.parseBoolean(input[4]), Boolean.parseBoolean(input[5]));
			heap.read(input[0], p, n);
		  }
		  while(sc.hasNextLine()) {//citesc comenzele din fisier
			  input = (sc.nextLine()).split(" ");
			  if(input[0].equals("list")) {
				  if(firstlist) {
					  System.out.println();
				  }
				  heap.list();
				  firstlist = true;
			  }
			  if(input[0].equals("embark")) {
				  heap.embark();
			  }
			  if(input[0].equals("insert")) {
				  pasager = heap.getPasager(input[1]);
				  heap.insert(pasager, pasager.Prioritate());
			  }
			  if(input[0].equals("delete")) {
				  pasager = new Pasageri(1);
				  if(input.length > 2){
					  pasager.add(new Persoana(input[2], 0, ' ', false, false));
				  }
				  pasager.setId(input[1]);
				  heap.delete(pasager);		  
			  }
		  }
		  sc.close();
		  }
}




