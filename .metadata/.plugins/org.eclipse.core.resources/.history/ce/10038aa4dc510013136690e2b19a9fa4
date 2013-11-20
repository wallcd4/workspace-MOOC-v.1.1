/**
 *  Generic Ball object
 */
package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;

/**
 * Name				Revision	Date		Description
 * 					Change No.
 * David Wallace	1.1			06/11/2013	Initial coding
 * 
 * 
 *
 */

/*
 * This is the prototype ball from which other ball classes will be created
 */
public class Ballon {

	/**
	 * Properties
	 */

	protected Bitmap image = null;
	protected float posX = 0;
	protected float posY = 0;
	protected float velX = 0;
	protected float velY = 0;
	protected float radius = 0;

	
	/** Auto generated.  See Menu item Source
	 * @param image
	 * @param posX
	 * @param posY
	 * @param velX
	 * @param velY
	 */
	public Ballon(Bitmap image, float posX, float posY, float velX, float velY) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		radius = calculateRadius();
	}
	
	



	//
	// null constructor
	//
	public Ballon() {

	}


	/**
	 * @return the image
	 */
	public Bitmap getImage() {
		return image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(Bitmap image) {
		this.image = image;
		radius = calculateRadius();
	}


	/**
	 * @return the posX
	 */
	public float getPosX() {
		return posX;
	}


	/**
	 * @param posX the posX to set
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}


	/**
	 * @return the posY
	 */
	public float getPosY() {
		return posY;
	}


	/**
	 * @param posY the posY to set
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}


	/**
	 * @return the velX
	 */
	public float getVelX() {
		return velX;
	}


	/**
	 * @param velX the velX to set
	 */
	public void setVelX(float velX) {
		this.velX = velX;
	}


	/**
	 * @return the velY
	 */
	public float getVelY() {
		return velY;
	}


	/**
	 * @param velY the velY to set
	 */
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	//
	// Now add user code
	//
	 public void changeVel(float x, float y) {
		 
		 velX = x - posX;
		 velY = y - posY;
		 
	 }
	 
	private float calculateRadius() {

		return this.image.getHeight() / 2;
	}
	 
/*
 * Move the ball making sure is stays on the screen
 */
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
				posY = maxPosY * 2 - posY;
				velY = velY * -1;
				onTheScreen = false;
			}
			
		}
			
	 }
	 
	 /*
	  * Check for a collision with another ball
	  */
	 protected boolean collisionCheck(Ballon otherBallon) {
		 float distance;
		 
		 distance = (float) Math.sqrt(Math.pow(this.posX - otherBallon.posX, 2) + 
					Math.pow(this.posY - otherBallon.posY, 2));
		 
		 if((distance <= (this.radius + otherBallon.radius)))
			 
			 return true;

		 else
			 
			 return false;
		 
	 }
	 
	 /*
	  * If a collision is identified, calculate the new velocity
	  */
	 protected void changeVelocity(Ballon otherBall) {
		 float velocityOfBallon;
		 float newVelocityOfBallon;
		 
		 velocityOfBallon = (float) Math.sqrt(Math.pow(this.velX, 2) + 
				 Math.pow(this.velY, 2));
		 
		 velX = posX - otherBall.posX;
		 velY = posY - otherBall.posY;
		 
		 newVelocityOfBallon = (float) Math.sqrt(Math.pow(this.velX, 2) + 
				 Math.pow(this.velY, 2));
		 
		 velX = velX * velocityOfBallon / newVelocityOfBallon;
		 velY = velY * velocityOfBallon / newVelocityOfBallon;
		 
	 }
	

}