import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A JPanel object that displays the map with points and paths
 * Moving the cursor over a park will highlight it blue
 * @author Aaron
 */
@SuppressWarnings("serial")
public class mapPanel extends JPanel{
	private BufferedImage usa;
	private ArrayList<AmusementPark> parkList;
	private	infoPanel infopanel;
	private ControlPanel controlpanel;
	@SuppressWarnings("unused")
	private AmusementPark clickedPark;
	public ArrayList<Line2D.Double> lines;
	public boolean drawLines;
	
	public mapPanel(ArrayList<AmusementPark> parks, infoPanel info) {
		this.lines = new ArrayList<Line2D.Double>();
		this.drawLines = false;
		this.controlpanel = null;
		this.clickedPark = null;
		this.infopanel = info;
		this.parkList = parks;
		this.setPreferredSize(new Dimension(900, 600));
		try {
			usa = ImageIO.read(new File("Images/usa.png"));
			
		} catch (IOException ex){
			
		}
		MouseAdapter adapter = new MouseAdapter(){
			  @Override
	            public void mousePressed(MouseEvent me) {
	                super.mousePressed(me);
	                for (AmusementPark park : parkList) {
	                	Point2D point = me.getPoint();
	                    if (isContained(point, park.location)) {
	                        clickedPark = park;
	                        revalidate();
	                        repaint();
	                        infopanel.displayInfo(park);
	        				
	        				if (controlpanel != null){
	        					controlpanel.setBox(park);
	        				}
	                    }
	                }
	            }
			  
			  public void mouseMoved(MouseEvent me){
	            	super.mouseMoved(me);
	                for (AmusementPark park : parkList) {
	                	if (isContained(me.getPoint(), park.location)) {
	                        park.color = Color.BLUE;
	                        revalidate();
	                        repaint();
	                	}
	                	else{
	                		park.color = Color.RED;
	                		revalidate();
	                		repaint();
	                	}
	                }
	            }
		};
		this.addMouseListener(adapter);
		this.addMouseMotionListener(adapter);
	}
	
	/**
	 * sets the control panel as a global variable for access in the mouselisteners
	 * @param panel
	 */
	public void setControl(ControlPanel panel){
		this.controlpanel = panel;
	}
	
	/**
	 * checks if the point contains a park and returns true if it is
	 * @param click
	 * @param park
	 * @return
	 */
	public boolean isContained(Point2D click, Point2D park){
		if (click.getX() > park.getX() && click.getX() < park.getX() + 15){
			if (click.getY() > park.getY() && click.getY() < park.getY() + 15){
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * creates a line2D object based on the start and end location
	 * used in paintComponent to simplify code
	 * @param startPark
	 * @param endPark
	 */
	public void addLine(AmusementPark startPark, AmusementPark endPark){
		Line2D.Double line = new Line2D.Double(startPark.location.getX() + 7.5, startPark.location.getY() + 7.5, endPark.location.getX() + 7.5, endPark.location.getY() + 7.5);
		this.lines.add(line);
	}
	
	/**
	 * draws the map on this component as well as park circles
	 * if a best path is found, it will display that path with the appropriate lines
	 */
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D graphics2 = (Graphics2D) g;
		
		double scale = 1.3;
		g.drawImage(usa,50,50,(int)(usa.getWidth() * scale), (int)(usa.getHeight() * scale), this);
		
	
		for(int i=0; i < parkList.size(); i++){
			Point2D point = parkList.get(i).location;
			Ellipse2D.Double circ = new Ellipse2D.Double(point.getX(), point.getY(), 15, 15);
			graphics2.setColor(parkList.get(i).color);
			g.drawString(parkList.get(i).name, (int)point.getX(), (int)point.getY());
			graphics2.fill(circ);
		}
		graphics2.setStroke(new BasicStroke(5));
		graphics2.setColor(Color.BLACK);
		if (this.drawLines){
			for(int i=0; i<this.lines.size(); i++){
				
				graphics2.draw(this.lines.get(i));
			}
		}
	}
}