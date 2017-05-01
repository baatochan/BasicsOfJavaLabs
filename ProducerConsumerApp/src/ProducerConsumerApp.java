/*
 *  Problem producenta i konsumenta
 *
 *  Autor: Bartosz Rodziewicz 226105
 *  Data: 3 stycznie 2017 r.
 */

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by barto on 03.01.17.
 */

public class ProducerConsumerApp extends JFrame implements ActionListener{
	private int numberOfBuffers;
	private int numberOfProducers;
	private int numberOfConsumers;
	private boolean simulationStarted = false;
	
	private ProducentKonsument simulation;
	
	private final static String ABOUT_MESSAGE = "Symulacja problemu konsumentów i producentów\n" +
			"\n" +
			"Autor: Paweł Rogaliński, Bartosz Rodziewicz 226105\n" +
			"Data: 3 stycznia 2017 r.";
	
	private JLabel numberOfProducersLabel = new JLabel("Ilość producentów: ");
	private JLabel numberOfConsumersLabel = new JLabel(" Ilość konsumentów: ");
	private JLabel bufferSizeLabel = new JLabel(" Rozmiar bufora: ");
	
	private JSpinner numberOfProducersSpinner = new JSpinner(new SpinnerNumberModel(2,1,5,1));
	private JSpinner numberOfConsumersSpinner = new JSpinner(new SpinnerNumberModel(2,1,5,1));
	private JSpinner bufferSizeSpinner = new JSpinner(new SpinnerNumberModel(1,1,5,1));
	
	private JButton startSimulationButton = new JButton("Start");
	private JButton pauseSimulationButton = new JButton("Pauza");
	private JButton stopSimulationButton = new JButton("Stop");
	private JButton aboutMessageButton = new JButton("O programie");
	
	private JTextArea simulationOutputTextArea = new JTextArea(24, 80);
	
	
	
	private ProducerConsumerApp() {
		super("Symulacja problemu konsumentów i producentów");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		
		JPanel mainWrapper = new JPanel();
		mainWrapper.setLayout(new BoxLayout(mainWrapper, BoxLayout.Y_AXIS));
		
		JPanel menuPanel = new JPanel();
		menuPanel.add(startSimulationButton);
		menuPanel.add(pauseSimulationButton);
		menuPanel.add(stopSimulationButton);
		menuPanel.add(aboutMessageButton);
		
		JPanel setiingsPanel = new JPanel();
		setiingsPanel.add(numberOfProducersLabel);
		setiingsPanel.add(numberOfProducersSpinner);
		setiingsPanel.add(numberOfConsumersLabel);
		setiingsPanel.add(numberOfConsumersSpinner);
		setiingsPanel.add(bufferSizeLabel);
		setiingsPanel.add(bufferSizeSpinner);
		
		simulationOutputTextArea.setLineWrap(true);
		JScrollPane simulationOutputScroller = new JScrollPane(simulationOutputTextArea);
		simulationOutputScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		((DefaultCaret)simulationOutputTextArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		mainWrapper.add(menuPanel);
		mainWrapper.add(setiingsPanel);
		mainWrapper.add(simulationOutputScroller);
		
		this.getContentPane().add(mainWrapper);
		
		startSimulationButton.addActionListener(this);
		pauseSimulationButton.addActionListener(this);
		stopSimulationButton.addActionListener(this);
		aboutMessageButton.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ProducerConsumerApp();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startSimulationButton) startSimulation();
		else if (e.getSource() == pauseSimulationButton) pauseSimulation();
		else if (e.getSource() == stopSimulationButton) stopSimulation();
		else if (e.getSource() == aboutMessageButton) JOptionPane.showMessageDialog(this, ABOUT_MESSAGE);
	}
	
	private void startSimulation() {
		if (!simulationStarted) {
			simulationOutputTextArea.setText(null);
			
			numberOfBuffers = (int)bufferSizeSpinner.getValue();
			numberOfProducers = (int)numberOfProducersSpinner.getValue();
			numberOfConsumers = (int)numberOfConsumersSpinner.getValue();
			
			simulation = new ProducentKonsument(numberOfBuffers, numberOfProducers, numberOfConsumers, this);
			
			bufferSizeSpinner.setEnabled(false);
			numberOfProducersSpinner.setEnabled(false);
			numberOfConsumersSpinner.setEnabled(false);
			
			simulation.runSimulation();
			simulationStarted = true;
		}
		else simulation.resumeSimulation();
	}
	
	private void pauseSimulation() {
		if(simulationStarted) {
			simulation.suspendSimulation();
		}
	}
	
	private void stopSimulation() {
		if(simulationStarted) {
			simulation.stopSimulation();
			simulationStarted = false;
			
			simulation = null;
			
			bufferSizeSpinner.setEnabled(true);
			numberOfProducersSpinner.setEnabled(true);
			numberOfConsumersSpinner.setEnabled(true);
		}
	}
	
	public void accept(String s) {
		simulationOutputTextArea.append(s);
		simulationOutputTextArea.append("\n");
	}
}