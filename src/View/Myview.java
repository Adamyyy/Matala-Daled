package View;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class Myview extends Observable implements view,Observer {
	private BufferedReader in;
	private PrintWriter out;
	CLI cli;
	
	public Myview(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
				
		cli = new CLI(in, out);
		cli.addObserver(this);
	}	
	
	
	@Override
	public void start() {
		cli.start();
	}

	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifysolutionisready(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		System.out.println(" ");
		maze.print();
		System.out.println(" ");
		System.out.println("Please enter next command");

	}

	@Override
	public void displayerror(String error) {
		String exiterror= "You decided to leave ";
		System.out.println(" ");
		System.out.println(error);
		if (error.equals(exiterror)) {return;}
		System.out.println(" ");
		System.out.println("Please enter next command");

	}

	@Override
	public void displaysolution(Solution<Position> insert) {
		System.out.println(" ");
		System.out.println(insert.getres().toString());
		System.out.println(" ");
		System.out.println("Please enter next command");

		}

	@Override
	public void notifymazehasbeensaved(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaycross(int[][] toreturn) {
		for (int j = 0; j<toreturn[0].length; j++){
		     for (int i = 0; i<toreturn.length; i++){
		    	 System.out.print(toreturn[j][i] + " ");
		     }
		}
	}

	@Override
	public void displayDirPath(String[] list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifymazeloaded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayloadmaze(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	if (arg0==cli) {setChanged(); notifyObservers(arg1);}
	
	}


	@Override
	public void getmaze(Maze3d insert) {
		// TODO Auto-generated method stub
		
	}

}
