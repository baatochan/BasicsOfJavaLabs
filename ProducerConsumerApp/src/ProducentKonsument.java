/* 
 *  Problem producenta i konsumenta
 *
 *  Autor: Pawel Rogalinski, Bartosz Rodziewicz 226105
 *   Data: 1 pazdziernik 2009 r.
 */


import java.util.ArrayList;

public class ProducentKonsument {
	private int numberOfBuffers;
	private int numberOfProducers;
	private int numberOfConsumers;
	private ProducerConsumerApp BufferOutputHandler;
	
	private ArrayList<Producent> Producers = new ArrayList<Producent>();
	private ArrayList<Konsument> Consumers = new ArrayList<Konsument>();

	ProducentKonsument(int numberOfBuffers, int numberOfProducers, int numberOfConsumers, ProducerConsumerApp BufferOutputHandler) {
		this.numberOfBuffers = numberOfBuffers;
		this.numberOfConsumers = numberOfConsumers;
		this.numberOfProducers = numberOfProducers;
		this.BufferOutputHandler = BufferOutputHandler;
	}
	
	public void runSimulation(){
		
		Bufor b = new Bufor(numberOfBuffers, BufferOutputHandler);
		for (int i = 0; i<numberOfProducers; i++) {
			Producers.add(new Producent(b, i+1));
		}
		for (int i = 0; i<numberOfConsumers; i++) {
			Consumers.add(new Konsument(b, i+1));
		}
		
		for (Producent p : Producers) {
			p.resetItem();
			p.start();
		}
		for (Konsument k : Consumers) {
			k.start();
		}
		
		/*
		Bufor c = new Bufor();
		Producent p1 = new Producent(c, 1);
		Konsument c1 = new Konsument(c, 1);
		Producent p2 = new Producent(c, 2);
		Konsument c2 = new Konsument(c, 2);
		p1.start(); 
		c1.start();
		p2.start();
		c2.start();
		
		try { Thread.sleep( 8000 );
			} catch (InterruptedException e) { }
		System.exit(0);*/
	}
	
	public void stopSimulation(){
		for (Producent p : Producers) {
			p.stop();
		}
		for (Konsument k : Consumers) {
			k.stop();
		}
	}
	
	public void suspendSimulation() {
		for (Producent p : Producers) {
			p.suspend();
		}
		for (Konsument k : Consumers) {
			k.suspend();
		}
	}
	
	public void resumeSimulation() {
		for (Producent p : Producers) {
			p.resume();
		}
		for (Konsument k : Consumers) {
			k.resume();
		}
	}
}


