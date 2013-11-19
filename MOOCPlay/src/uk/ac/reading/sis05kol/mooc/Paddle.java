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

/*
 * Class for a paddle.  Not move overides move in Ballon
 */
public class Paddle extends Ballon {

	public Paddle() {
		radius = 0;
		this.image = null;
		this.posX = 0;
		this.posY = 0;
		this.velX = 0;
		this.velY = 0;
	}

	void move(float x, float canvasWidth) {
		
		if(x > canvasWidth)
			x = canvasWidth;
		
		if(x < 0)
			x = 0;
		
		this.posX = x;
		
	}
}
