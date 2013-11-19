package uk.ac.reading.sis05kol.mooc;

import uk.ac.reading.sis05kol.mooc.Ballon;


/**
 * Name				Revision	Date		Description
 * 					Change No.
 * David Wallace	1.1			06/11/2013	Initial coding
 * 
 * 
 *
 */
public class RedBall extends Ballon {

	/**
	 *   RedBall is an extension of Ballon. 
	 */
	
	
	//
	// Move overides the method in Ballon
	//
	 @Override
	 protected void move(float secondsElapsed, float maxPosX, float maxPosY) {
		 
		boolean onTheScreen = false;
		 
		posX = posX + secondsElapsed * velX;
		posY = posY + secondsElapsed * velY;
		
		while(onTheScreen == false) {
			
			onTheScreen = true;
			
			if(posX < 0) {
				posX = posX * -1;
				velX = velX * -1;
				onTheScreen = false;
			}
			
			if(posX > maxPosX) {
				posX = maxPosX * 2 - posX;
				velX = velX * -1;
				onTheScreen = false;
			}
			
			if(posY < 0) {
				posY = posY * -1;
				velY = velY * -1;
				onTheScreen = false;
			}
			
			if(posY > maxPosY) {
				// Do nothing

			}
			
		}
			
	 }

}
