package com.cs428.pandemic.frontEnd.gamePlay;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.WindowManager;

import com.cs428.pandemic.R;

public class GamePlayActivity extends Activity 
		implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	
	private NavigationDrawerFragment mNavigationDrawerFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
        	View decorView = getWindow().getDecorView();
        	int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        	decorView.setSystemUiVisibility(uiOptions);
        }
		
		setContentView(R.layout.activity_gameplay);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
		
        if (getActionBar() != null)
        	getActionBar().setDisplayHomeAsUpEnabled(false);
		
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.gameplay_container, new BoardFragment()).commit();
		}
	}
	
	@Override
    public void onNavigationDrawerItemSelected(int position) {
        // TODO: handle menu item selection        
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    @SuppressWarnings("deprecation")
	public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
    }	
}
