import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.cardboard.dtanjp.Computer;
/**
 * AppState.java
 * 
 * @author David Tan
 **/
public class AppState extends JPanel {

	/** Generated serial version UID **/
	private static final long serialVersionUID = 2533200929277679307L;
	
	/** Constructor **/
	private AppState() {
		setSize(300, 300);
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(150, 200, 500, 500);
		setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		exitButton = new JButton("Exit");
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.CYAN);
		exitButton.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		exitButton.setBounds(10, 30, 100, 100);
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Computer.getInstance().GetOS().Request(MainApp.instance, "OS", "exit", 0);
			}
			
		});
	}
	
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D)graphics;
		
		//Convert 24 hours to 12 hours
		int hour = 12;
		if(LocalDateTime.now().getHour()%12 != 0)
			hour = (12*(LocalDateTime.now().getHour()/12) - LocalDateTime.now().getHour())*-1;
		String AM = (LocalDateTime.now().getHour()/12 == 0) ? "AM" : "PM";
		//Get minutes
		int minutes = LocalDateTime.now().getMinute();
				
		g.setColor(Color.WHITE);
		g.drawString("[Clock App]: "+dtf.format(LocalDateTime.now()), 5, 15);
		g.setColor(Color.CYAN);
		String min = minutes < 10 ? "0"+minutes : minutes+"";
		g.drawString(hour+":"+min+":"+LocalDateTime.now().getSecond()+" "+AM, 225, 365);
		
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(3));
		g.drawOval(100, 40, 300, 300);
		g.setColor(Color.CYAN);
		g.setStroke(new BasicStroke(1));
		g.drawOval(240, 190, 10, 10);
		
		//Draw clock markings
		for(int i=12; i>=1; --i) {
			g.setColor(Color.CYAN);
			float radians = (float) ((Math.PI/180)*(90 + ((12-i)*30)));
			g.drawString(""+i, (int)((Math.cos(radians)*140)+245), (int)(-1*(Math.sin(radians)*140)+195));
		}

		//Draw hour hand
		g.setColor(Color.WHITE);
		float radians = (float) ((Math.PI/180)*(90 + (((12-hour) * (30))) - (minutes/2)));
		g.setStroke(new BasicStroke(3));
		g.drawLine(245, 195, (int)((Math.cos(radians)*50)+245), (int)(-1*(Math.sin(radians)*50)+195));
		
		//Draw minute hand
		radians = (float) ((Math.PI/180)* (90 - (minutes*6)));
		g.setStroke(new BasicStroke(1));
		g.drawLine(245, 195, (int)((Math.cos(radians)*100)+245), (int)(-1*(Math.sin(radians)*100)+195));
	}
	
	
	
	/** Singleton **/
	public static AppState getInstance() {
		if(instance == null)
			instance = new AppState();
		return instance;
	}
	
	/** Variables **/
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private static AppState instance = null;
	private JButton exitButton = null;
}
