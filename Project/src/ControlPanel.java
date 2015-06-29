
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

/**
 * A Jpanel containing the buttons, text fields, and radio buttons for the gui
 * Also includes the action listeners.
 * @author Aaron
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel{
	private infoPanel infopanel;
	private JTextField startField;
	private JTextField endField;
	private JTextField searchbox;
	public AmusementPark start;
	public AmusementPark end;
	private boolean startChanging;
	public mapPanel map;
	public SearchMap smap;
	private JTextField limit;

	public ControlPanel(infoPanel info, SearchMap sea){
		this.startChanging = true;
		this.setLayout(new MigLayout());
		this.smap = sea;
		infopanel = info;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JTextField searchBox = new JTextField(10);
		this.add(searchBox, "cell 0 0, gapy 30::30");
		searchbox = searchBox;
		
		JButton searchButton = new JButton("search");
		this.add(searchButton, "cell 1 0, span");
		ActionListener search = new ActionListener(){
			public void actionPerformed(ActionEvent f){
				infopanel.removeAll();
				ArrayList<AmusementPark> parklist = smap.getList(Integer.parseInt(searchbox.getText()));
				for(int i=0; i < parklist.size(); i++){
					JLabel label = new JLabel();
					label.setText(parklist.get(i).getName() + " " + parklist.get(i).levelOfFun);
					infopanel.add(label, "cell 0 " + i);
				
				}
				infopanel.displayingParkInfo = false;
				infopanel.revalidate();
				infopanel.repaint();
			}
		};
		searchButton.addActionListener(search);
		
		
		JLabel startlabel = new JLabel("start: ");
		this.add(startlabel, "cell 0 1, span");
		
		final JTextField startBox = new JTextField(18);
		this.add(startBox, "cell 0 1");
		this.startField = startBox;
		
		JLabel endLabel = new JLabel("end: ");
		this.add(endLabel, "cell 0 2, span");
		
		final JTextField endBox = new JTextField(18);
		this.add(endBox, "cell 0 2");
		this.endField = endBox;
		
		final JRadioButton byDistance = new JRadioButton("By distance");
		byDistance.setSelected(true);
		final JRadioButton byTime = new JRadioButton("By time");
		ButtonGroup group =  new ButtonGroup();
		group.add(byDistance);
		group.add(byTime);
		
		this.add(byDistance, "cell 1 3 1 2");
		this.add(byTime, "cell 0 3 1 2");
	
		JButton goButton = new JButton("GO!");
		this.add(goButton, "cell 0 5");
		ActionListener go = new ActionListener(){
			public void actionPerformed(ActionEvent f){
				if (start == null || end == null){
					String error = "Please select ";
					if (start == null && end == null) error = error + "a start and end location";
					else if (start == null) error = error + "a start location";
					else if (end == null) error = error + "an end location";
					JLabel error1 = new JLabel(error);
					infopanel.removeAll();
					infopanel.displayingParkInfo = false;
					infopanel.add(error1, "cell 0 0");
					infopanel.revalidate();
					infopanel.repaint();
					return;
				}
				ParkGraph graph = new ParkGraph(start, end);
				if (byDistance.isSelected()) {
					while (!graph.pathsByDistance.peek().routeByDistance.contains(end)){
						graph.travelByDistance();
					}
					Paths bestPath = graph.pathsByDistance.poll();
					if (map != null){
						map.drawLines = true;
						map.lines.clear();
					}
					
					for (int i =1; i < bestPath.routeByDistance.size(); i++){
						if (map != null){
							map.addLine(bestPath.routeByDistance.get(i-1), bestPath.routeByDistance.get(i));
						}
					}
					infopanel.displayRoute(bestPath.routeByDistance);
				}
				if (byTime.isSelected()) {
					while (!graph.pathsByTime.peek().routeByTime.contains(end)){
						graph.travelByTime();
					}
					Paths bestPath = graph.pathsByTime.poll();
					if (map != null){
						map.drawLines = true;
						map.lines.clear();
					}
					
					for (int i =1; i < bestPath.routeByTime.size(); i++){
						if (map != null){
							map.addLine(bestPath.routeByTime.get(i-1), bestPath.routeByTime.get(i));
						}
					}
				}
				if (map != null){
					map.revalidate();
					map.repaint();
				}
				infopanel.displayingParkInfo = false;
				infopanel.revalidate();
				infopanel.repaint();
				
			}
		};
		goButton.addActionListener(go);
		
		JButton planButton = new JButton("trip Planner");
		this.add(planButton, "cell 1 5");
		ActionListener plan = new ActionListener(){
			public void actionPerformed(ActionEvent f){
				if (start == null || end == null){
					String error = "Please select ";
					if (start == null && end == null) error = error + "a start and end location";
					else if (start == null) error = error + "a start location";
					else if (end == null) error = error + "an end location";
					JLabel error1 = new JLabel(error);
					infopanel.removeAll();
					infopanel.displayingParkInfo = false;
					infopanel.add(error1, "cell 0 0");
					infopanel.revalidate();
					infopanel.repaint();
					return;
				}
				if (limit.getText().isEmpty()){
					JLabel error2 = new JLabel("please select a maximum distance/time");
					infopanel.removeAll();
					infopanel.displayingParkInfo = false;
					infopanel.add(error2, "cell 0 0");
					infopanel.revalidate();
					infopanel.repaint();
					return;
				}
				infopanel.removeAll();
				ParkGraph graph = new ParkGraph(start, end);
				double limitation = Double.parseDouble(limit.getText());
				if (byDistance.isSelected()) {
					int counter = 1;
					while (graph.pathsByDistance.size() != 0 && graph.pathsByDistance.peek() != null) {
						
						while (graph.pathsByDistance.peek() != null && !graph.pathsByDistance.peek().routeByDistance.contains(end)){
							graph.travelByDistance();
						}
						if (graph.pathsByDistance.peek() != null) {
							Paths bestPath = graph.pathsByDistance.poll();
							if (bestPath.distanceTraveled <= limitation) {
									JLabel plan = new JLabel();
									String message = "<HTML><U>plan " + counter + "</U><br>";
									counter++;
								for (int i = 1; i < bestPath.routeByDistance.size(); i++) {
									message = message + bestPath.routeByDistance.get(i - 1).name + " to " + bestPath.routeByDistance.get(i).name + "<br>";
									
								}
								message = message + "</HTML>";
								plan.setText(message);
								infopanel.add(plan, "cell 0 " + counter);
							}
						}
					}
				}
				
				if (byTime.isSelected()) {
					int counter = 1;
					while (graph.pathsByTime.size() != 0 && graph.pathsByTime.peek() != null) {
						
						while (graph.pathsByTime.peek() != null && !graph.pathsByTime.peek().routeByTime.contains(end)){
							graph.travelByTime();
						}
						if (graph.pathsByTime.peek() != null) {
							Paths bestPath = graph.pathsByTime.poll();
							if (bestPath.timeSpentTraveling <= limitation) {
								JLabel plan = new JLabel();
								String message = "<HTML><U>plan " + counter + "</U><br>";
								counter++;
								for (int i = 1; i < bestPath.routeByTime.size(); i++) {
									message = message + bestPath.routeByTime.get(i - 1).name + " to " + bestPath.routeByTime.get(i).name + "<br>";
								}
								message = message + "</HTML>";
								plan.setText(message);
								infopanel.add(plan, "cell 0 " + counter);
								
							}
						}
					}
				}
				
				infopanel.displayingParkInfo = false;
				infopanel.revalidate();
				infopanel.repaint();
				
			}
		};
		planButton.addActionListener(plan);
		
		JLabel distanceLabel = new JLabel("Limit Distance/Time: ");
		this.add(distanceLabel, "cell 0 6, span");
		JTextField distanceLimit = new JTextField(7);
		this.add(distanceLimit, "cell 0 6, span");
		this.limit = distanceLimit;
		
	}
	
	/**
	 * gives this control panel the map panel for reference in the action listeners
	 * @param inmap
	 */
	public void setMap(mapPanel inmap){
		this.map = inmap;
	}
	
	/**
	 * sets the start and end boxes according to which one is being changed
	 * @param park park to change
	 */
	public void setBox(AmusementPark park){
		if(this.startChanging){
			this.start = park;
			this.startField.setText(park.name);
			this.startChanging = !this.startChanging;
		}
		else{
			this.end = park;
			this.endField.setText(park.name);
			this.startChanging = !this.startChanging;
		}
	}

}