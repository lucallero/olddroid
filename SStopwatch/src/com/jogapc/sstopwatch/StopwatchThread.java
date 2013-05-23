package com.jogapc.sstopwatch;

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
    private TextView textChronometer;
    private TextView textBestLap;
    private TextView textWorstLap;
    private TextView textAverage;
    private LinearLayout splitTimeContainer;
    private int layoutId;
    private Activity mainActivity;
    private long lapAverage = 0L;
    private long lastSplitTime = 0L;
    private long lapTime = 0L;
    private long bestLap = Long.MAX_VALUE;
    private long worstLap = 0L;
    private int lapCount = 0;

    public StopwatchThread(TextView aTextChronometer, TextView aTextBestLap,
	    TextView aTextWorstLap, TextView aTextAverage, LinearLayout aSplitTimeContainer,
	    int aLayoutId, Activity aMainActivity) {
	this.logger = Logger.getLogger(getClass().getName());
	this.run = true;
	this.stopWatch = new StopWatch();
	this.textChronometer = aTextChronometer;
	this.textBestLap = aTextBestLap;
	this.textWorstLap = aTextWorstLap;
	this.textAverage = aTextAverage;
	this.splitTimeContainer = aSplitTimeContainer;
	this.layoutId = aLayoutId;
	this.mainActivity = aMainActivity;
    }

    public void stopWork(final TextView aTextView) {
	this.stopWatch.split();
	this.stopWatch.stop();
	this.lapTime = this.stopWatch.getSplitTime() - this.lastSplitTime;
	this.lastSplitTime = this.stopWatch.getSplitTime();
	if (this.lapTime < this.bestLap) {
	    this.bestLap = this.lapTime;
	}
	if (this.lapTime > this.worstLap) {
	    this.worstLap = this.lapTime;
	}

	this.lapAverage = stopWatch.getSplitTime() / ++this.lapCount;

	aTextView.setText(this.lapCount + " - "
		+ DurationFormatUtils.formatDurationHMS(this.lapTime));
	this.mainActivity.runOnUiThread(new Runnable() {
	    @Override
	    public void run() {
		splitTimeContainer.addView(aTextView);
		textBestLap.setText("Best lap: " + DurationFormatUtils.formatDurationHMS(bestLap));
		textAverage.setText("Average: " + DurationFormatUtils.formatDurationHMS(lapAverage));
		textWorstLap.setText("Worst lap :"
			+ DurationFormatUtils.formatDurationHMS(worstLap));
		textChronometer.setText(stopWatch.toSplitString());
	    }
	});
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

	aTextView.setText(this.lapCount + " - "
		+ DurationFormatUtils.formatDurationHMS(this.lapTime));
	this.mainActivity.runOnUiThread(new Runnable() {
	    @Override
	    public void run() {
		splitTimeContainer.addView(aTextView);
		textBestLap.setText("Best lap: " + DurationFormatUtils.formatDurationHMS(bestLap));
		textAverage.setText("Average: " + DurationFormatUtils.formatDurationHMS(lapAverage));
		textWorstLap.setText("Worst lap :"
			+ DurationFormatUtils.formatDurationHMS(worstLap));
	    }
	});
    }

    @Override
    public void run() {
	this.stopWatch.reset();
	this.stopWatch.start();
	while (run) {
	    try {
		Thread.sleep(70);
		this.mainActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			textChronometer.setText(stopWatch.toString());
		    }
		});
	    } catch (InterruptedException e) {
		this.logger.log(Level.SEVERE, e.getMessage());
		e.printStackTrace();
	    }
	}
	this.logger.log(Level.INFO, layoutId + "Encerrou");
    }
}
