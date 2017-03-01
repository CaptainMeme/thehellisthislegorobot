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
		return (l1.getNormalizedLightValue()< threshhold);
	}
	public void suppress() {
		suppressed = true;
	}
	public void action() {
        pilot.setTravelSpeed(50);
        suppressed = false;
    	pilot.steer(-150);
        while (!suppressed && l1.getNormalizedLightValue()< threshhold) {
    		this.corner.timesincelineseen = 0;
        }
	}
}
