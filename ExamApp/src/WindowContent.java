import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by barto on 11.01.17.
 */
public class WindowContent extends JPanel implements ActionListener, MouseListener, MouseMotionListener, Runnable {
	
	private int secondsRun = 0;
	
	private JLabel secondsRunLabel = new JLabel("Sekundy: ");
	private JTextField secondsRunTextField = new JTextField(2);
	private JButton secondsRunReset = new JButton("Reset");
	
	private Thread counter = new Thread(this);
	
	private JButton addBoxButton = new JButton("Dodaj");
	private JButton removeBoxButton = new JButton("Usun");
	private JButton removeAllButton = new JButton("Usun wszystkie");
	
	private JButton ABOUT = new JButton("O programie");
	
	private Boolean clicked = false;
	private Boolean paint = true;
	
	private int ovalPositionX = -20;
	private int ovalPositionY = -20;
	private int ovalRadius = 10;
	
	public WindowContent() {
		this.add(secondsRunLabel);
		this.add(secondsRunTextField);
		secondsRunTextField.setEditable(false);
		this.add(secondsRunReset);
		this.add(ABOUT);
		
		secondsRunReset.addActionListener(this);
		ABOUT.addActionListener(this);
		
		this.add(addBoxButton);
		this.add(removeBoxButton);
		this.add(removeAllButton);
		
		addBoxButton.addActionListener(this);
		removeBoxButton.addActionListener(this);
		removeAllButton.addActionListener(this);
		JTextArea test = new JTextArea(15,15);
		
		addMouseMotionListener(this);
		addMouseListener(this);
		counter.start();
		
		this.add(test);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(paint){
			g.drawOval(ovalPositionX,ovalPositionY,ovalRadius,ovalRadius);
		}
		if(clicked){
			g.setColor(Color.cyan);
			g.fillOval(ovalPositionX,ovalPositionY,ovalRadius,ovalRadius);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ABOUT) {
			JOptionPane.showMessageDialog(this,"Autor: Bartosz Rodziewicz, 226105","O programie",1);
		}
		else if (e.getSource() == secondsRunReset) {
			secondsRun = 0;
			secondsRunTextField.setText(Integer.toString(secondsRun));
		}
	}
		
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		clicked = true;
		repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		clicked = false;
		repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		paint = true;
		repaint();
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		paint = false;
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		ovalPositionX = e.getX();
		ovalPositionY = e.getY();
		repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		ovalPositionX = e.getX();
		ovalPositionY = e.getY();
		repaint();
	}
	
	@Override
	public void run() {
		while(true){
			secondsRunTextField.setText(Integer.toString(secondsRun));
			secondsRun++;
			try{
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
