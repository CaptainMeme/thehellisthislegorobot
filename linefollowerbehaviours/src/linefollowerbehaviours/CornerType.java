package linefollowerbehaviours;

import lejos.nxt.LCD;

public class CornerType{
	public int tolerance;
	public int corner;
	public int threshhold = 440;
	public int suspicion = 0;
	public int turn = 0;
	public int timesincelineseen = 0;
	public CornerType(){
		this.corner = 0;
	}
	
	public void setTurn(int turn){
		this.turn = turn;
	}
	
	public void CT(int l1, int l2){
	    	if(l1 < threshhold){
	    		if(l2< threshhold) {
	    			if (corner == 0){
	    				corner = 5;
	    			}
	    			else if (corner == 1){
	    				if (tolerance < 20){
	    					corner = 5;
	    					tolerance = 0;
	    				}
	    				else {
	    					corner = 3;
	    					tolerance = 0;
	    				}
	    			}
	    			else if (corner == 2){
	    				if (tolerance < 20){
	    					corner = 5;
	    					tolerance = 0;
	    				}
	    				else {
	    					corner = 4;
	    					tolerance = 0;
	    				}
	    			}
	    			else if (corner == 3){
	    			}
	    			else if (corner == 4){
	    			}
	    			else if (corner == 5){
	    				suspicion++;
	    				if (suspicion >= 150){
	    					corner = 6;
	    					//I *KNEW* it!
	        				suspicion = 0;
	    				}
	    			}
	    			else if (corner == 6){
	    			}
	    		} else {
	    			if (corner == 0){
	    				corner = 2;
	    			}
	    			else if (corner == 1){
	    				//If you are here, something is very wrong
	    				corner = 2;
	    			}
	    			else if (corner == 2){
	    				tolerance++;
	    			}
	    			else if (corner == 3){
	    			}
	    			else if (corner == 4){
	    			}
	    			else if (corner == 5){
	    				suspicion = 0;
	    				corner = 2;
	    			}
	    			else if (corner == 6){
	    			}
	    		}
	    	} else if(l2< threshhold){
    			if (corner == 0){
    				corner = 1;
    			}
    			else if (corner == 1){
    				tolerance++;
    			}
    			else if (corner == 2){
    				//If you are here, something is very wrong
    				corner = 1;
    			}
    			else if (corner == 3){
    			}
    			else if (corner == 4){
    			}
    			else if (corner == 5){
    				suspicion = 0;
    				corner = 1;
    			}
    			else if (corner == 6){
    			}
	    	} else {
	    		//straight line or end of line
    			if (corner == 0){
    			}
    			else {
					tolerance = 0;
    				corner = 0;
    			}
	    	}
	}
}