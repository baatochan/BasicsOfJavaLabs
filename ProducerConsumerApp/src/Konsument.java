/*
 *  Problem producenta i konsumenta
 *
 *  Autor: Pawel Rogalinski, Bartosz Rodziewicz 226105
 *   Data: 1 pazdziernik 2009 r.
 */


class Konsument extends Thread
{
	private Bufor buf;
    private int number;

	public Konsument(Bufor c, int number){
		buf = c;
		this.number = number;
	}
	
	public void run(){
		while(true){
			buf.get(number);
			try {
				sleep((int)(Math.random() * 1000));
				} catch (InterruptedException e) { }
		}
	}
}
