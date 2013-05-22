package com.jogapc.sstopwatch;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StopwatchThread extends Thread {
    private Logger logger;
    private boolean run;
    private StopWatch stopWatch;
    private TextView chronometerTextView;
    private LinearLayout splitTimeContainer;
    private int layoutId;
    private Activity mainActivity;
    private long lapAverage = 0L;
    private long lastSplitTime = 0L;
    private long lapTime = 0L;
    private long bestLap = 0L;
    private long worstLap = 0L;
    private int lapCount = 0;
    private ArrayList<Long> splitTimeList = new ArrayList<Long>();

    public StopwatchThread(TextView textViewMainTime, LinearLayout aSplitTimeContainer,
	    int aLayoutId, Activity aMainActivity) {
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

    /**
     * Calcula e atualiza as variveis de tempo, tempo da volta, melhor volta,
     * pior volta, media das voltas
     * 
     * @param aTextView
     */
    public void splitTime(final TextView aTextView) {
	this.stopWatch.split();
	this.lapTime = this.stopWatch.getSplitTime() - this.lastSplitTime;
	this.lastSplitTime = this.stopWatch.getSplitTime();
	if (this.lapTime < this.bestLap) {
	    this.bestLap = this.lapTime;
	}
	if (this.lapTime > this.worstLap) {
	    this.worstLap = this.lapTime;
	}

	this.lapAverage = stopWatch.getSplitTime() / ++this.lapCount;

	aTextView
		.setText(this.lapCount + " - " + DurationFormatUtils.formatDurationHMS(this.lapTime));
	this.mainActivity.runOnUiThread(new Runnable() {
	    @Override
	    public void run() {
		splitTimeContainer.addView(aTextView);
	    }
	});
    }

    @Override
    public void run() {
	this.stopWatch.start();
	while (run) {
	    try {
		Thread.sleep(50);
		this.mainActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			chronometerTextView.setText(stopWatch.toString());
		    }
		});
	    } catch (InterruptedException e) {
		this.logger.log(Level.SEVERE, e.getMessage());
		e.printStackTrace();
	    }
	}
	this.stopWatch.reset();
	this.logger.log(Level.INFO, layoutId + "Encerrou");
    }
}
