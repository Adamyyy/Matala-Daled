package model;
import java.util.Map;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import presenter.Properties;
public interface model {

	
	public void generatemaze(String name, int z,int y, int x);
	public Maze3d getmaze(String mazename);
	public Solution<Position> getsolution (String name);
	public void solvemaze (String name);
	public void savemaze(String name, String filename);
	public int[][] getcrossbyindex(int index, String x_y_z, String name);
	public void dirpath(String[] args);
	public void exit();
	public Properties getProperties();
	public void load(String filename,String mazename);
}
