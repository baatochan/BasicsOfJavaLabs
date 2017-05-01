/*
 *  Problem producenta i konsumenta
 *
 *  Autor: Pawel Rogalinski, Bartosz Rodziewicz 226105
 *   Data: 1 pazdziernik 2009 r.
 */


class Producent extends Thread
{
	private static char item = 'A';
	
	private Bufor buf;
	private int number;
	
	public Producent(Bufor c, int number){
		buf = c;
		this.number = number;
	}
	
	public void run(){
		char c;
		while(true){
			c = item++;
			if (item == '[') item = 'A';
			buf.put(number, c);
			try {
				sleep((int)(Math.random() * 1000));
				} catch (InterruptedException e) { }
		}
	}
	
	public void resetItem() {
		item = 'A';
	}
}
