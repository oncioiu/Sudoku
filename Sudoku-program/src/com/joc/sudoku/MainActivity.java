package com.joc.sudoku;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("ValidFragment")
public class MainActivity extends Activity {
	Button continueButon, jocnouButon, despreButon, exitButon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		continueButon = (Button) findViewById(R.id.continue_button);
		continueButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		jocnouButon = (Button) findViewById(R.id.new_button);
		jocnouButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openNewGameDialog();
			}
		});
		despreButon = (Button) findViewById(R.id.about_button);
		despreButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, About.class);
				startActivity(i);
			}
		});
		exitButon = (Button) findViewById(R.id.exit_button);
		exitButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		switch (item.getItemId()) {
		case R.id.setari:
			startActivity(new Intent(MainActivity.this, Prefs.class));
			break;

		}
		return false;
	}

	private static final String TAG = "Sudoku";

	/** Aici intrebi useru dificultate */
	private void openNewGameDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.new_game_title)
				.setItems(R.array.dificultate,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								startGame(i);
							}
						}).show();
	}


	/** Start a new game with the given difficulty level */
	private void startGame(int i) {
		Log.d(TAG, "clicked on " + i);
		Intent intent = new Intent(MainActivity.this, Game.class);
		intent.putExtra(Game.KEY_DIFICULTATE, i);
		startActivity(intent);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	@SuppressLint("NewApi")
	public class PlaceholderFragment extends Fragment {

		@SuppressLint("ValidFragment")
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
