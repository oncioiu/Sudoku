package com.joc.sudoku;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class Game extends Activity {
	private static final String TAG = "Sudoku";
	public static final String KEY_DIFICULTATE = "com.joc.sudoku.dificultate";
	public static final int DIFICULTATE_EASY = 0;
	public static final int DIFICULTATE_MEDIUM = 1;
	public static final int DIFICULTATE_HARD = 2;
	private int puzzle[] = new int[9 * 9];
	private PuzzleView puzzleView;
	private final int used[][][] = new int[9][9][];

	private final String easyPuzzle = "360000000004230800000004200"
			+ "070460003820000014500013020" + "001900000007048300000000045";
	private final String mediumPuzzle = "650000070000506000014000005"
			+ "007009000002314700000700800" + "500000630000201000030000097";
	private final String hardPuzzle = "009000000080605020501078000"
			+ "000000700706040102004000000" + "000720903090301080000000600";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		int diff = getIntent().getIntExtra(KEY_DIFICULTATE, DIFICULTATE_EASY);
		puzzle = getPuzzle(diff);
		calculateUsedTiles();
		puzzleView = new PuzzleView(this);
		setContentView(puzzleView);
		puzzleView.requestFocus();
	}

	private void calculateUsedTiles() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				used[x][y] = calculateUsedTiles(x, y);
				// Log.d(TAG, "used[" + x + "][" + y + "] = "
				// + toPuzzleString(used[x][y]));
			}
		}
	}

	private int[] getPuzzle(int diff) {
		String puz;
		// TODO: Continue last game
		switch (diff) {
		case DIFICULTATE_HARD:
			puz = hardPuzzle;
			break;
		case DIFICULTATE_MEDIUM:
			puz = mediumPuzzle;
			break;
		case DIFICULTATE_EASY:
		default:
			puz = easyPuzzle;
			break;
		}
		return fromPuzzleString(puz);
	}

	private int[] calculateUsedTiles(int x, int y) {
		int c[] = new int[9];
		// horizontal
		for (int i = 0; i < 9; i++) {
			if (i == y)
				continue;
			int t = getTile(x, i);
			if (t != 0)
				c[t - 1] = t;
		}
		// vertical
		for (int i = 0; i < 9; i++) {
			if (i == x)
				continue;
			int t = getTile(i, y);
			if (t != 0)
				c[t - 1] = t;
		}
		// same cell block
		int startx = (x / 3) * 3;
		int starty = (y / 3) * 3;
		for (int i = startx; i < startx + 3; i++) {
			for (int j = starty; j < starty + 3; j++) {
				if (i == x && j == y)
					continue;
				int t = getTile(i, j);
				if (t != 0)
					c[t - 1] = t;
			}
		}
		// compress
		int nused = 0;
		for (int t : c) {
			if (t != 0)
				nused++;
		}
		int c1[] = new int[nused];
		nused = 0;
		for (int t : c) {
			if (t != 0)
				c1[nused++] = t;
		}
		return c1;
	}

	/** Return the tile at the given coordinates */
	private int getTile(int x, int y) {
		return puzzle[y * 9 + x];
	}

	/** Change the tile at the given coordinates */
	private void setTile(int x, int y, int value) {
		puzzle[y * 9 + x] = value;
	}
	
	protected int[] getUsedTiles(int x, int y) {
		return used[x][y];
		}

	protected String getTileString(int x, int y) {
		int v = getTile(x, y);
		if (v == 0)
			return "";
		else
			return String.valueOf(v);
	}

	static protected int[] fromPuzzleString(String string) {
		int[] puz = new int[string.length()];
		for (int i = 0; i < puz.length; i++) {
			puz[i] = string.charAt(i) - '0';
		}
		return puz;
	}

	static private String toPuzzleString(int[] puz) {
		StringBuilder buf = new StringBuilder();
		for (int element : puz) {
			buf.append(element);
		}
		return buf.toString();
	}

}
