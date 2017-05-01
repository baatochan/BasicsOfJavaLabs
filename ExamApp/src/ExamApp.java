import javax.swing.*;
import java.awt.*;

/**
 * Created by barto on 10.01.17.
 */
public class ExamApp extends JFrame{
	
	private ExamApp() {
		super("Bartosz Rodziewicz, 226105");
		setSize(new Dimension(300, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WindowContent mainWrapper = new WindowContent();
		
		setContentPane(mainWrapper);
		setVisible(true);
	}
	
	
	public static void main(String[] args){
		new ExamApp();
	}
}
