package linefollowerbehaviours;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.LCD;

public class MoveOn implements Behavior {
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	private LightSensor l1;
	private LightSensor l2;
	private CornerType corner;
	public MoveOn(LightSensor l1, LightSensor l2, DifferentialPilot pilot, CornerType corner){
		this.pilot = pilot;
		this.l1 = l1;
		this.l2 = l2;
		this.corner = corner;
	}
	public boolean takeControl(){
		return true;
	}
	public void suppress() {
		suppressed = true;
	}
	public void action() {
        pilot.setTravelSpeed(50);
        suppressed = false;
    	pilot.steer(0);
        while (!suppressed) {
        	this.corner.timesincelineseen++;
        	LCD.drawInt(corner.timesincelineseen, 0, 1);
        	Thread.yield();
        	
        }
	}
}
