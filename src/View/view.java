package View;

import java.util.HashMap;
import java.util.Properties;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;






public interface view {
	public void start();
	public void getmaze(Maze3d insert);
	public void notifyMazeIsReady(String name);
	public void notifysolutionisready(String name);
	public void displayMaze(Maze3d maze);
	public void displayerror(String error);
	public void displaysolution (Solution <Position> insert); //SIMON OLAY BAYATI HAKETA SHEL HA SOLUTION
	public void notifymazehasbeensaved(String name);
	public void displaycross(int[][] toreturn);
	public void displayDirPath(String[] list);
	public void notifymazeloaded();
	public void displayloadmaze(String string);
	public void getsolution (Solution<Position> sol);
	public void getproperties (presenter.Properties p);
}


