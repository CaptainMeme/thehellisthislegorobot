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
	private CornerType corner;
	public GoLeft(LightSensor l1, LightSensor l2, DifferentialPilot pilot, CornerType corner){
		this.pilot = pilot;
		this.l1 = l1;
		this.l2 = l2;
		this.corner = corner;
	}
	public boolean takeControl(){
		return (l2.getNormalizedLightValue()< threshhold);
	}
	public void suppress() {
		suppressed = true;
	}
	public void action() {
        pilot.setTravelSpeed(75);
        suppressed = false;
    	pilot.steer(150);
        while (!suppressed && l2.getNormalizedLightValue()< threshhold) {
        	this.corner.timesincelineseen = 0;
        }
	}
}
