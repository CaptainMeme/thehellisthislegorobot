package linefollowerbehaviours;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.*;

public class LineFollowerArb {
	public static void main(String args[]) {
		Behavior[] beh;
        LightSensor l1 = new LightSensor(SensorPort.S2);
        LightSensor l2 = new LightSensor(SensorPort.S3);
        UltrasonicSensor u1 = new UltrasonicSensor(SensorPort.S1);
        DifferentialPilot pilot = new DifferentialPilot(56.0,170.0,Motor.B,Motor.C);
        CornerType corner = new CornerType();
		beh = new Behavior[6];
		beh[0] = new MoveOn(l1, l2, pilot, corner);
		beh[1] = new GoLeft(l1, l2, pilot, corner); //Life is peaceful there
		beh[2] = new GoRight(l1, l2, pilot, corner);
		beh[3] = new FindLine(l1, l2, pilot, corner);
		beh[4] = new DetectJunction(l1,l2,pilot,corner);
		beh[5] = new DetectObstacle(pilot,u1);
		Button.waitForAnyPress();
		Arbitrator arb = new Arbitrator(beh,true);
		arb.start();
	}
}

