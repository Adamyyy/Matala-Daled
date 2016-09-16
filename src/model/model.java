package model;
import java.util.Map;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import controller.Controller;
public interface model {

	/**
	 * 
	 * @param adam
	 * generatemaze - creates a maze
	 * display - displays createdmaze
	 * solvemaze - solves a maze
	 * displaysol - displays a solution
	 * getmazes - returns the mazes datamember in the model
	 * savemaze - saves the maze to file
	 * getcrossbyindex - creates the crosssection
	 * dirpath - creates the directory
	 * exit - exits from the program and closes all open threads
	 * load - loads a maze from a file and adds it to the mazes datamember
	 */
	public void generatemaze(String name, int z,int y, int x);
	public void display (String name);
	public void setController(Controller controller);
	public void displaysol (String name);
	public void solvemaze (String name,String algoname);
	public Map<String, Maze3d> getmazes();
	public void savemaze(String name, String filename);
	public void getcrossbyindex(int index, String x_y_z, String name);
	public void dirpath(String[] args);
	public void exit();
	public void load(String filename,String mazename);
}
