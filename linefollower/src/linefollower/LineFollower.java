package linefollower;
import lejos.nxt.*;
//import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.*;

public class LineFollower {
	public static void main(String args[]) {
		Behavior[] beh;
		beh = new Behavior[1];
		beh[0] = new FollowLine();
		
		Arbitrator arb = new Arbitrator(beh,true);
		arb.start();
	}
}

