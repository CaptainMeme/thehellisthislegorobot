package linefollower;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class FollowLine implements Behavior {
	private boolean suppressed = false;
	private int threshhold = 440;
	private String[] cornerStrings;
	
	public FollowLine(){
		this.cornerStrings = new String[7];
		this.cornerStrings[0] = "Straight";
		this.cornerStrings[1] = "Left Corner";
		this.cornerStrings[2] = "Right Corner";
		this.cornerStrings[3] = "Left T";
		this.cornerStrings[4] = "Right T";
		this.cornerStrings[5] = "Left-Right T";
		this.cornerStrings[6] = "Crossroads";
	}
	
	public boolean takeControl() {
		return true;
	}
	public void suppress() {
		suppressed = true;
	}
	public void action() {
        DifferentialPilot pilot = new DifferentialPilot(56.0,170.0,Motor.B,Motor.C);
        LightSensor l1 = new LightSensor(SensorPort.S2);
        LightSensor l2 = new LightSensor(SensorPort.S3);
        l1.setFloodlight(true);
        l2.setFloodlight(true);
        int suspicion = 0;
        int tolerance = 0;
        int y = 0;
        int z = 0;
		int cornertype = 0;
        pilot.setTravelSpeed(75);
        
        suppressed = false;
        while (z < 1000 && !suppressed) {
	    	if(l1.getNormalizedLightValue() < threshhold){
	    		y = 0;
	    		z = 0;
	    		if(l2.getNormalizedLightValue()< threshhold) {
	    			if (cornertype == 0){
	    				cornertype = 5;
	    				pilot.steer(-150);
	    			}
	    			else if (cornertype == 1){
	    				if (tolerance < 20){
	    					cornertype = 5;
	    					tolerance = 0;
	    				}
	    				else {
	    					cornertype = 3;
	    					tolerance = 0;
	    				}
	    			}
	    			else if (cornertype == 2){
	    				if (tolerance < 20){
	    					cornertype = 5;
	    					tolerance = 0;
	    				}
	    				else {
	    					cornertype = 4;
	    					tolerance = 0;
	    				}
	    			}
	    			else if (cornertype == 3){
	    			}
	    			else if (cornertype == 4){
	    			}
	    			else if (cornertype == 5){
	    				suspicion++;
	    				if (suspicion >= 150){
	    					cornertype = 6;
	    					//I *KNEW* it!
	        				suspicion = 0;
	    				}
	    			}
	    			else if (cornertype == 6){
	    			}
	    		} else {
	    			if (cornertype == 0){
	    				cornertype = 2;
	    				pilot.steer(-150);
	    			}
	    			else if (cornertype == 1){
	    				//If you are here, something is very wrong
	    				cornertype = 2;
	    	    		pilot.steer(-150);
	    			}
	    			else if (cornertype == 2){
	    				tolerance++;
	    			}
	    			else if (cornertype == 3){
	    			}
	    			else if (cornertype == 4){
	    			}
	    			else if (cornertype == 5){
	    				suspicion = 0;
	    				cornertype = 2;
	    	    		pilot.steer(-150);
	    			}
	    			else if (cornertype == 6){
	    			}
	    		}
	    	} else if(l2.getNormalizedLightValue()< threshhold){
	    		//corner
	    		y = 0;
	    		z = 0;
    			if (cornertype == 0){
    				cornertype = 1;
    				pilot.steer(150);
    			}
    			else if (cornertype == 1){
    				tolerance++;
    			}
    			else if (cornertype == 2){
    				//If you are here, something is very wrong
    				cornertype = 1;
    	    		pilot.steer(150);
    			}
    			else if (cornertype == 3){
    			}
    			else if (cornertype == 4){
    			}
    			else if (cornertype == 5){
    				suspicion = 0;
    				cornertype = 1;
    	    		pilot.steer(150);
    			}
    			else if (cornertype == 6){
    			}
	    	} else {
	    		//straight line or end of line
    			if (cornertype == 0){
    			}
    			else {
					tolerance = 0;
    				cornertype = 0;
    				pilot.forward();
    			}
	    		y++;
	    		if (y > 2000) {
	    			pilot.steer(-150);
	    			z++;
	    		}
	    	}
	    	//LCD.drawInt(l1.getNormalizedLightValue(), 4, 0, 1);
	    	//LCD.drawInt(l2.getNormalizedLightValue(), 4, 0, 2);
	    	LCD.drawString(this.cornerStrings[cornertype], 0, 1);
        }
        pilot.stop();
	}
}
