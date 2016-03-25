package com.cs428.pandemic.frontEnd.gamePlay;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.IModelInterface;
import com.cs428.pandemic.frontEnd.IUI_Updater;
import com.cs428.pandemic.frontEnd.gamePlay.boardFragment.BoardFragment;
import com.cs428.pandemic.frontEnd.startSequence.MainMenuActivity;
import com.cs428.pandemic.frontEnd.test.FakeModelFacade;

public class GamePlayActivity extends AppCompatActivity 
		implements NavigationDrawerFragment.NavigationDrawerCallbacks, IUI_Updater {
	
	private NavigationDrawerFragment mNavigationDrawerFragment;
    private FloatingActionButton mMenuFab;

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
		
		Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
	    setSupportActionBar(myToolbar);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mMenuFab = (FloatingActionButton) findViewById(R.id.fab_menu);
        mMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigationDrawerFragment.openDrawer();
            }
        });
        
        if (getSupportActionBar() != null) {
//            myToolbar.setNavigationIcon(R.drawable.ic_launcher);
        }

        // Retrieve paramaters passed from MainMenuActivity
        Intent intent = getIntent();
        Bundle args = new Bundle();
        args.putStringArrayList(BoardFragment.PLAYERS_ARGS, intent.getStringArrayListExtra(MainMenuActivity.EXTRA_PLAYERS));
        args.putString(BoardFragment.DIFFICULTY_ARGS, intent.getStringExtra(MainMenuActivity.EXTRA_DIFFICULTY));
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
	
	public void toggleToolbar() {
		if (mMenuFab.isShown())
            mMenuFab.hide();
		else
            mMenuFab.show();
	}

    public IModelInterface getModelFacade() {
        return modelFacade;
    }

    @Override
    public void updateUI() {

        // Notify the BoardFragment of changes made to the model
        BoardFragment boardFragment = (BoardFragment) getFragmentManager().findFragmentById(R.id.game_board);
        boardFragment.updateBoard();
    }

    @Override
    public void ResolveEpidemic() {

    }
}
