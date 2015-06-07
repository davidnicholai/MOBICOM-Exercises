package com.example.labgestures;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	TextView tvOne, tvTwo;
	ViewFlipper flipper;
	GestureDetector gestureDetector;
	int counter = 0;
	boolean isTVOneCurrent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		tvOne = (TextView) findViewById(R.id.tvOne);
		tvTwo = (TextView) findViewById(R.id.tvTwo);
		
		gestureDetector = new GestureDetector(getBaseContext(), listener);
	}
	
	GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
		public boolean onDown(MotionEvent e) {
			return true;
		};
		
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			return true;
		};
		
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if (velocityX < -10f) { // go to left
				counter++;
				moveCounterUp();
			} else if (velocityX > 10f) { // go to right
				counter--;
				moveCounterDown();
			}
			
			if (isTVOneCurrent) {
				tvOne.setText(counter + "");
				isTVOneCurrent = false;
			} else {
				tvTwo.setText(counter + "");
				isTVOneCurrent = true;				
			}
			
			flipper.showNext();
			
			return true;
		};
	};
	
	public void moveCounterUp() {
		TranslateAnimation inFromRight
			= new TranslateAnimation
				(Animation.RELATIVE_TO_PARENT, 1,
				Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0);
		
		inFromRight.setDuration(500);
		
		TranslateAnimation outToLeft
		= new TranslateAnimation
			(Animation.RELATIVE_TO_PARENT, 0,
			Animation.RELATIVE_TO_PARENT, -1,
			Animation.RELATIVE_TO_PARENT, 0,
			Animation.RELATIVE_TO_PARENT, 0);
		
		outToLeft.setDuration(500);
		
		flipper.setInAnimation(inFromRight);
		flipper.setOutAnimation(outToLeft);
	}

	public void moveCounterDown() {
		TranslateAnimation inFromLeft
			= new TranslateAnimation
				(Animation.RELATIVE_TO_PARENT, -1,
				Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0);
		
		inFromLeft.setDuration(500);
		
		TranslateAnimation outToRight
		= new TranslateAnimation
			(Animation.RELATIVE_TO_PARENT, 0,
			Animation.RELATIVE_TO_PARENT, 1,
			Animation.RELATIVE_TO_PARENT, 0,
			Animation.RELATIVE_TO_PARENT, 0);
		
		outToRight.setDuration(500);
		
		flipper.setInAnimation(inFromLeft);
		flipper.setOutAnimation(outToRight);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}
}
