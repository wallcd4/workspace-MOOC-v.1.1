package uk.ac.reading.sis05kol.mooc;


//Other parts of the android libraries that we us
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Name				Revision	Date		Description
 * 					Change No.
 * 					1.0						Initial coding
 * David Wallace	1.1			06/11/2013	Add Ballon and it's children.
 * 											Code is moved from here to object
 * 											and so protects it.
 *
 */

public class TheGame extends GameThread{

	//Will store the values of a ball, Paddle
	private RedBall mBall = new RedBall();
	private Paddle mPaddle = new Paddle();
	private YellowBall mSmileyFace = new YellowBall();
	private YellowBall[] mSadBall = new YellowBall[3];
	private Bitmap mSadBallImage;

	//This is run before anything else, so we can prepare things here
	public TheGame(GameView gameView) {
		//House keeping
		super(gameView);
		
	//	mSadBall = new YellowBall[3];
		
		for(int i = 0; i < mSadBall.length; i++)
			mSadBall[i] = new YellowBall();
		
		//Prepare the images so we can draw them on the screen (using a canvas)	
		mBall.setImage(BitmapFactory.decodeResource
				(gameView.getContext().getResources(), 
				R.drawable.small_red_ball));			
		
		mPaddle.setImage(BitmapFactory.decodeResource
				(gameView.getContext().getResources(), 
				R.drawable.yellow_ball));	
		
		mSmileyFace.setImage(BitmapFactory.decodeResource
				(gameView.getContext().getResources(), 
				R.drawable.smiley_ball));
		
		for(int i = 0; i < mSadBall.length; i++)
			mSadBall[i].setImage(BitmapFactory.decodeResource
					(gameView.getContext().getResources(), 
					R.drawable.sad_ball));
		
		mSadBallImage = (BitmapFactory.decodeResource
				(gameView.getContext().getResources(), 
				R.drawable.sad_ball));
	}
	
	//This is run before a new game (also after an old game)
	@Override
	public void setupBeginning() {
		//Initialise speeds
		mBall.setVelX(50);								
		mBall.setVelY(35);								
		
		//Place the ball in the middle of the screen.
		//mBall.Width() and mBall.getHeigh() gives us the height and width of the image of the ball
		mBall.setPosX(mCanvasWidth / 2);				
		mBall.setPosY(mCanvasHeight / 2);			
		
		//Place the Paddle bottom, middle
		mPaddle.setPosX(mCanvasWidth / 2);
		mPaddle.setPosY(mCanvasHeight);
		
		// Place Smiley Face at the top
		mSmileyFace.setPosX(mCanvasWidth / 2);
		mSmileyFace.setPosY(mSmileyFace.getImage().getHeight() / 2);
		
		//Sad faces
		mSadBall[0].setPosX(mCanvasWidth / 3);
		mSadBall[0].setPosY(mCanvasHeight / 3);
		
		mSadBall[1].setPosX(mCanvasWidth  - mCanvasWidth / 3);
		mSadBall[1].setPosY((mCanvasHeight / 3));
		
		mSadBall[2].setPosX(mCanvasWidth / 2);
		mSadBall[2].setPosY((mCanvasHeight / 5));
		
		for(int i = 0; i < mSadBall.length; i++)
			mSadBall[i].setImage(mSadBallImage);
	}

	@Override
	protected void doDraw(Canvas canvas) {
		//If there isn't a canvas to draw on do nothing
		//It is ok not understanding what is happening here
		if(canvas == null) return;
		
		super.doDraw(canvas);
		
		//draw the image of the ball using the X and Y of the ball
		//drawBitmap uses top left corner as reference, we use middle of picture
		//null means that we will use the image without any extra features (called Paint)
		canvas.drawBitmap(mBall.getImage(), mBall.getPosX() - mBall.getImage().getWidth() / 2,
				mBall.getPosY() - mBall.getImage().getHeight() / 2, null);
		
		canvas.drawBitmap(mPaddle.getImage(), mPaddle.getPosX() - mPaddle.getImage().getWidth() / 2,
				mPaddle.getPosY() - mPaddle.getImage().getHeight() / 2, null);
		
		canvas.drawBitmap(mSmileyFace.getImage(), mSmileyFace.getPosX() - mSmileyFace.getImage().getWidth() / 2,
				mSmileyFace.getPosY() - mSmileyFace.getImage().getHeight() / 2, null);
		
		for(int i = 0; i < mSadBall.length; i++)
			if(mSadBall[i].image != null)
				canvas.drawBitmap(mSadBall[i].getImage(), mSadBall[i].getPosX() - mSadBall[i].getImage().getWidth() / 2,
					mSadBall[i].getPosY() - mSadBall[i].getImage().getHeight() / 2, null);
			
	}
	
	//This is run whenever the phone is touched by the user
	
	@Override
	protected void actionOnTouch(float x, float y) {
		// Move the Paddle
		mPaddle.move(x, mCanvasWidth);
	}
	
	
	/*
	//This is run whenever the phone moves around its axises 
	@Override
	protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {
		//Increase/decrease the speed of the ball
		mBallSpeedX = mBallSpeedX - 1.5f * xDirection;
		mBallSpeedY = mBallSpeedY - 1.5f * yDirection;
	}
	*/
	
	//This is run just before the game "scenario" is printed on the screen
	@Override
	protected void updateGame(float secondsElapsed) {
		//Move the ball's X and Y using the speed (pixel/sec)
		mBall.move(secondsElapsed, mCanvasWidth, mCanvasHeight);
		
		if(mBall.collisionCheck(mPaddle))
			mBall.changeVelocity(mPaddle);
		
		if(mBall.collisionCheck(mSmileyFace)) {
			mBall.changeVelocity(mSmileyFace);
			updateScore(1);
			while(mBall.collisionCheck(mSmileyFace))
				mBall.move(secondsElapsed, mCanvasWidth, mCanvasHeight);
		}
		
		for(int i = 0; i < mSadBall.length; i++)
			if(mBall.collisionCheck(mSadBall[i])) {
				mBall.changeVelocity(mSadBall[i]);
				mSadBall[i].image = null;
			}

		
		if(mBall.posY > mCanvasHeight - mBall.getImage().getHeight() / 2)
			setState(GameThread.STATE_LOSE);
	}
}

// This file is part of the course "Begin Programming: Build your first mobile game" from futurelearn.com
// Copyright: University of Reading and Karsten Lundqvist
// It is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// It is is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// 
// You should have received a copy of the GNU General Public License
// along with it.  If not, see <http://www.gnu.org/licenses/>.
