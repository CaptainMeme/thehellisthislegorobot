package linefollowerbehaviours;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.LCD;

public class GoRight implements Behavior {
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	private LightSensor l1;
	private LightSensor l2;
	private int threshhold = 440;
	private CornerType corner;
	public GoRight(LightSensor l1, LightSensor l2, DifferentialPilot pilot, CornerType corner){
		this.pilot = pilot;
		this.l1 = l1;
		this.l2 = l2;
		this.corner = corner;
	}
	public boolean takeControl(){
		return (l1.getNormalizedLightValue()< threshhold && this.corner.turn != 1);
	}
	public void suppress() {
		suppressed = true;
	}
	public void action() {
		this.corner.turn = -1;
        pilot.setTravelSpeed(75);
        suppressed = false;
    	pilot.steer(-150);
        while (!suppressed && l1.getNormalizedLightValue()< threshhold) {
        	this.corner.timesincelineseen = 0;
        	this.corner.CT(l1.getNormalizedLightValue(), l2.getNormalizedLightValue());
	    	LCD.drawInt(l1.getNormalizedLightValue(), 4, 0, 1);
	    	LCD.drawInt(l2.getNormalizedLightValue(), 4, 0, 2);
	    	LCD.drawString("Right    ", 0, 3);
        }
	}
}
