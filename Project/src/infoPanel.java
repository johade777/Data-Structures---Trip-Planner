
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.InputStream;

import net.miginfocom.swing.MigLayout;

/**
 * A dynamic Jpanel that displays any info needed to be shown
 * if searching, displays search results
 * if planning a trip, displays possible paths
 * if choosing best path, displays directions along best path
 * @author Aaron
 */
@SuppressWarnings("serial")
public class infoPanel extends JPanel{
	public boolean displayingParkInfo;
	private AmusementPark currentPark;

	public infoPanel(){
		this.displayingParkInfo = false;
		this.setLayout(new MigLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel label = new JLabel(" This area will contain useful info");
		this.add(label, "cell 0 0, gapleft push");
		
		
	}
	
	/**
	 * displays the Routes from the list given
	 * @param list
	 */
	public void displayRoute(LinkedList<AmusementPark> list){
		this.removeAll();
		for (int i=0; i < list.size() -1; i++){
			for (int j=0; j< list.get(i).getLinks().size(); j++){
				if (list.get(i).getLinks().get(j).travelToLocation == list.get(i +1)){
					String labelText = String.format("<html><U>go from " + list.get(i).name + " to " + list.get(i).getLinks().get(j).travelToLocation.name + 
							"</U><div WIDTH=%d>%s</div><html>", 200, list.get(i).getLinks().get(j).directions);
					JLabel direction = new JLabel();
					direction.setText(labelText);
					this.add(direction, "cell 0 " + i + ", gapbottom 20");
				}
			}
		}
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * displays the info of the park into this panel 
	 * 
	 * @param park park to display info of
	 */
	public void displayInfo(AmusementPark park){
		this.currentPark = park;
        this.removeAll();
        JLabel parkLabel = new JLabel("<HTML><U><B>" + park.name + "</B></U></HTML>");
        parkLabel.setFont(parkLabel.getFont().deriveFont(22f));
        this.add(parkLabel, "pushx, align center");
		this.displayingParkInfo = true;
		InputStream inputFile = this.getClass().getResourceAsStream("" + "ParkInfo/" + park.name);
		Scanner inputScanner = null;
		inputScanner = new Scanner(inputFile);
		int row = 2; 
		this.add(new JLabel(""), "cell 0 1, height 150");
		while(inputScanner.hasNextLine()){
			
			String line = inputScanner.nextLine();
			this.add(new JLabel(line), "cell 0 " + row + "");
			row++;
			
		}
		inputScanner.close();
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * paints the park info if it needs to
	 */
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if (this.displayingParkInfo){
			this.currentPark.drawOn(g, this);
		}
	}
}