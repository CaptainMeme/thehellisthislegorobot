package linefollowerbehaviours;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.LCD;

public class FindLine implements Behavior {
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	private LightSensor l1;
	private LightSensor l2;
	private CornerType corner;
	private int threshhold = 440;
	public FindLine(LightSensor l1, LightSensor l2, DifferentialPilot pilot, CornerType corner){
		this.pilot = pilot;
		this.l1 = l1;
		this.l2 = l2;
		this.corner = corner;
	}
	public boolean takeControl(){
		return this.corner.timesincelineseen > 2000;
	}
	public void suppress() {
		suppressed = true;
	}
	public void action() {
		this.corner.turn = 0;
        pilot.setTravelSpeed(75);
        suppressed = false;
    	pilot.steer(200);
        while (!suppressed && this.corner.timesincelineseen > 2000) {
        	if (l1.getNormalizedLightValue() < threshhold || l2.getNormalizedLightValue() < threshhold){
        		this.corner.timesincelineseen = 0;
        	}
        	this.corner.timesincelineseen++;
        	this.corner.CT(l1.getNormalizedLightValue(), l2.getNormalizedLightValue());
	    	LCD.drawInt(l1.getNormalizedLightValue(), 4, 0, 1);
	    	LCD.drawInt(l2.getNormalizedLightValue(), 4, 0, 2);
	    	LCD.drawString("Lost Line", 0, 3);
        }
        this.corner.timesincelineseen = 0;
	}
}