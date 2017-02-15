package linefollowerbehaviours;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.LCD;


public class GoLeft implements Behavior {
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	private LightSensor l1;
	private LightSensor l2;
	private int threshhold = 440;
	private String[] cornerStrings;
	private CornerType corner;
	public GoLeft(LightSensor l1, LightSensor l2, DifferentialPilot pilot, CornerType corner){
		this.pilot = pilot;
		this.l1 = l1;
		this.l2 = l2;
		this.cornerStrings = new String[7];
		this.cornerStrings[0] = "Straight    ";
		this.cornerStrings[1] = "Left Corner ";
		this.cornerStrings[2] = "Right Corner";
		this.cornerStrings[3] = "Left T      ";
		this.cornerStrings[4] = "Right T     ";
		this.cornerStrings[5] = "Left-Right T";
		this.cornerStrings[6] = "Crossroads  ";
		this.corner = corner;
	}
	public boolean takeControl(){
		return (l2.getNormalizedLightValue()< threshhold && this.corner.turn != -1);
	}
	public void suppress() {
		suppressed = true;
	}
	public void action() {
		this.corner.turn = 1;
        pilot.setTravelSpeed(75);
        suppressed = false;
    	pilot.steer(150);
        while (!suppressed && l2.getNormalizedLightValue()< threshhold) {
        	this.corner.timesincelineseen = 0;
        	this.corner.CT(l1.getNormalizedLightValue(), l2.getNormalizedLightValue());
	    	LCD.drawInt(l1.getNormalizedLightValue(), 4, 0, 1);
	    	LCD.drawInt(l2.getNormalizedLightValue(), 4, 0, 2);
	    	LCD.drawString("Left       ", 0, 3);
        }
	}
}