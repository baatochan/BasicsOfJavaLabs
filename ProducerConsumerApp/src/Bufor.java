/*
 *  Problem producenta i konsumenta
 *
 *  Autor: Pawel Rogalinski, Bartosz Rodziewicz 226105
 *   Data: 1 pazdziernik 2009 r.
 */


import java.util.ArrayList;

class Bufor
{
	//private char contents;
	private ArrayList<Character> contents = new ArrayList<Character>();
	private int BufforSize;
	private int SpaceAvalaible;
	private ProducerConsumerApp outputHandler;
	
	Bufor(int numberOfBuffors, ProducerConsumerApp outputHandler) {
		BufforSize = numberOfBuffors;
		SpaceAvalaible = numberOfBuffors;
		this.outputHandler = outputHandler;
	}
	

	public synchronized void get(int kons){
		//System.out.println("Konsument #" + kons + " chce zabrac");
		outputHandler.accept("Konsument #" + kons + " chce zabrac");
		while (SpaceAvalaible == BufforSize){
			//try { System.out.println("Konsument #" + kons + "   bufor pusty - czekam");
			try { outputHandler.accept("Konsument #" + kons + "   bufor pusty - czekam");
				  wait();
				} catch (InterruptedException e) { }
		}
		SpaceAvalaible++;
		int i = 0;
		while (contents.get(i) == '0') {
			i++;
		}
		char element = contents.get(i);
		contents.remove(i);
		//System.out.println("Konsument #" + kons + "      zabral: " + element);
		outputHandler.accept("Konsument #" + kons + "      zabral: " + element);
		notifyAll();
	}

	public synchronized void put(int prod, char value){
		//System.out.println("Producent #" + prod + "  chce oddac: " + value);
		outputHandler.accept("Producent #" + prod + "  chce oddac: " + value);
		while (SpaceAvalaible == 0){
			//try { System.out.println("Producent #" + prod + "   bufor zajety - czekam");
			try { outputHandler.accept("Producent #" + prod + "   bufor zajety - czekam");
				  wait();
				} catch (InterruptedException e) { }
		}
		contents.add(value);
		SpaceAvalaible--;
		//System.out.println("Producent #" + prod + "       oddal: " + value);
		outputHandler.accept("Producent #" + prod + "       oddal: " + value);
		notifyAll();
	}
}
