package com.cs428.pandemic.frontEnd.gamePlay;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.WindowManager;

import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.IModelInterface;
import com.cs428.pandemic.frontEnd.test.FakeModelFacade;

public class GamePlayActivity extends Activity 
		implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	
	private NavigationDrawerFragment mNavigationDrawerFragment;

    // Have a pointer to the Facade so all fragments started from this activity can have access to
    // the Facade.
    private IModelInterface modelFacade;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // This facade needs to be changed when we integrate with the model
        modelFacade = new FakeModelFacade();
		
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

        // Retrieve paramaters passed from MainMenuActivity
        Intent intent = getIntent();
        Bundle args = new Bundle();
        args.putStringArrayList("players", intent.getStringArrayListExtra("players"));
        args.putString("difficulty", intent.getStringExtra("difficulty"));
        Fragment boardFragment = new BoardFragment();
        boardFragment.setArguments(args);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.gameplay_container, boardFragment).commit();
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

    public IModelInterface getModelFacade() {
        return modelFacade;
    }
}
