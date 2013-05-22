package com.jogapc.sstopwatch;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.time.StopWatch;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StopwatchThread extends Thread {
	private Logger logger;
	private boolean run;
	private StopWatch stopWatch;
	private TextView chronometerTextView;
	LinearLayout splitTimeContainer;
	private int layoutId;
	private Activity mainActivity;

	public StopwatchThread(TextView textViewMainTime,
			LinearLayout aSplitTimeContainer, int aLayoutId,
			Activity aMainActivity) {
		this.logger = Logger.getLogger(getClass().getName());
		this.run = true;
		this.stopWatch = new StopWatch();
		this.chronometerTextView = textViewMainTime;
		this.splitTimeContainer = aSplitTimeContainer;
		this.layoutId = aLayoutId;
		this.mainActivity = aMainActivity;
	}

	public void stopWork() {
		this.run = false;
	}

	public boolean isRunning() {
		return this.run;
	}

	public void splitTime(final TextView aTextView) {
		stopWatch.split();
		aTextView.setText(stopWatch.toSplitString());
		mainActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				splitTimeContainer.addView(aTextView);
			}
		});
	}

	@Override
	public void run() {
		stopWatch.start();
		while (run) {
			try {
				Thread.sleep(50);
				mainActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						chronometerTextView.setText(stopWatch.toString());
					}
				});
//				System.out.println(layoutId + ":" + stopWatch.toString());
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
			}
		}
		stopWatch.reset();
		logger.log(Level.INFO, layoutId + "Encerrou");
	}
}
