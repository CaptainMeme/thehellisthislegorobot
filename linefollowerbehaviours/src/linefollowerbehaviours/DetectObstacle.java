package linefollowerbehaviours;

import lejos.nxt.UltrasonicSensor;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class DetectObstacle implements Behavior {
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	private UltrasonicSensor u1;
	public DetectObstacle(DifferentialPilot pilot, UltrasonicSensor u1){
		this.pilot = pilot;
		this.u1 = u1;
	}
	public boolean takeControl(){
		return u1.getDistance()<7.0; //needs testing...
	}
	public void suppress() {
		suppressed = true;
	}
	public void action() {
		pilot.travel(-20);
		pilot.steer(200,180);
	}
}
