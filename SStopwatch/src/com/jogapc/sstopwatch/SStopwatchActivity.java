package com.jogapc.sstopwatch;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SStopwatchActivity extends Activity implements OnClickListener {
    private Logger logger = Logger.getLogger(getClass().getName());
    private ArrayList<LaneLayout> laneLayoutList = new ArrayList<LaneLayout>(10);
    private SparseArray<StopwatchThread> stopwatchThreadList = new SparseArray<StopwatchThread>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.sstopwatch_activity);
	LinearLayout layoutMain = (LinearLayout) findViewById(R.id.mainLayout);
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
	LaneLayout tmpLayout = null;
	for (int i = 0; i < 10; i++) {
	    if (i == 0 || i % 2 == 0) {
		tmpLayout = new LaneLayout(this, i, Color.GRAY);
	    } else {
		tmpLayout = new LaneLayout(this, i, Color.WHITE);
	    }
	    tmpLayout.getToggleButton().setOnClickListener(this);
	    tmpLayout.getButtonLap().setOnClickListener(this);
	    tmpLayout.getButtonLap().setId(100 + i);
	    tmpLayout.getToggleButton().setId(200 + i);
	    tmpLayout.getTextViewChronometer().setId(300 + i);
	    tmpLayout.getTextViewLane().setId(400 + i);
	    laneLayoutList.add(tmpLayout);
	    // layoutMain.addView(tmpLayout, params);
	}
	layoutMain.addView(laneLayoutList.get(0), params);
	layoutMain.addView(laneLayoutList.get(1), params);
	layoutMain.addView(laneLayoutList.get(2), params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.sstopwatch_activity, menu);
	return true;
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	case 100:
	    System.out.println("1: button Lap clicked");
	    break;
	case 101:
	    System.out.println("2: button Lap clicked");
	    break;
	case 102:
	    System.out.println("3: button Lap clicked");
	    break;
	case 200:
	    logger.log(Level.INFO, "labelUpdater1:Toggle button clicked");
	    logger.log(Level.INFO, "Toggle button state:"
		    + laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked());
	    if (laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked()) {
		stopwatchThreadList.append(v.getId() - 200,
			new StopwatchThread(laneLayoutList.get(v.getId() - 200)
				.getTextViewChronometer(), laneLayoutList.get(v.getId() - 200)
				.getId(), this));
		stopwatchThreadList.get(v.getId() - 200).start();
	    } else {
		stopwatchThreadList.get(v.getId() - 200).stopWork();
	    }
	    break;
	case 201:
	    logger.log(Level.INFO, "labelUpdater2:Toggle button clicked");
	    logger.log(Level.INFO, "Toggle button state:"
		    + laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked());
	    if (laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked()) {
		stopwatchThreadList.append(v.getId() - 200,
			new StopwatchThread(laneLayoutList.get(v.getId() - 200)
				.getTextViewChronometer(), laneLayoutList.get(v.getId() - 200)
				.getId(), this));
		stopwatchThreadList.get(v.getId() - 200).start();
	    } else {
		stopwatchThreadList.get(v.getId() - 200).stopWork();
	    }
	    break;
	case 202:
	    logger.log(Level.INFO, "labelUpdater3:Toggle button clicked");
	    logger.log(Level.INFO, "Toggle button state:"
		    + laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked());
	    if (laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked()) {
		stopwatchThreadList.append(v.getId() - 200,
			new StopwatchThread(laneLayoutList.get(v.getId() - 200)
				.getTextViewChronometer(), laneLayoutList.get(v.getId() - 200)
				.getId(), this));
		stopwatchThreadList.get(v.getId() - 200).start();
	    } else {
		stopwatchThreadList.get(v.getId() - 200).stopWork();
	    }
	    break;
	default:
	    break;
	}
    }
}
