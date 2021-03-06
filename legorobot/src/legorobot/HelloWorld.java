package legorobot;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class HelloWorld { 
    public static void main(String[] args) {
        DifferentialPilot pilot = new DifferentialPilot(56.0,170.0,Motor.B,Motor.C);
        LightSensor l1 = new LightSensor(SensorPort.S2);
        LightSensor l2 = new LightSensor(SensorPort.S3);
        l1.setFloodlight(false);
        l2.setFloodlight(false);
        int x = 0;
        while(true){
        	if(l1.getNormalizedLightValue()-l2.getNormalizedLightValue()>30){
        		if (x != 0) {
        			//pilot.stop();
            		pilot.steer(-50);
        			x=0;
        		}
        		LCD.drawString("Positive", 0,0);
        	} else if(l1.getNormalizedLightValue()-l2.getNormalizedLightValue()<-30){
        		if (x != 1) {
        			//pilot.stop();
            		pilot.steer(50);
        			x=1;
        		}
        		LCD.drawString("Negative", 0,0);
        	} else {
        		if (x != 2) {
        			//pilot.stop();
            		pilot.forward();
        			x=2;
        		}
        		LCD.drawString("Straight", 0,0);
        	}
        	LCD.drawInt(l1.getNormalizedLightValue(), 4, 0, 1);
        	LCD.drawInt(l2.getNormalizedLightValue(), 4, 0, 2);
        }
     //robot 16   
    }
}
