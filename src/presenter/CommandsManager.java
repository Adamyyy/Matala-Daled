package presenter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.model;
import View.view;


public class CommandsManager {

	private model model;
	private view view;

	
	
	public CommandsManager(model model, view view) {
		this.model = model;
		this.view = view;		
	}

	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generatemaze", new GenerateMazeCommand()); //harasho
		commands.put("display", new DisplayMazeCommand()); // harasho
		commands.put("solvemaze", new SolveMazeCommand()); //harasho
		commands.put("displaysolution", new DisplaySolutionCommand()); //harasho
		commands.put("savemaze",new Savemazecommand());//harasho
		commands.put("displaycross", new Displaycrosscommand());
		commands.put("dirpath", new dircommand());
		commands.put("load", new loadcommand());
		commands.put("exit", new exitcommand());
		return commands;
	}

	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[1];
			int z = Integer.parseInt(args[2]);
			int y = Integer.parseInt(args[3]);
			int x= Integer.parseInt(args[4]);
			model.generatemaze(name, z, y, x); //HARASHO
		}		
	}

	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[1];
			Maze3d maze = model.getmaze(name);
			if (!(maze==null)) { view.displayMaze(maze);}
			
		}

	}

	public class SolveMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {

		
			String name = args[1];
			
			model.solvemaze(name);
		}
	}

	public class Savemazecommand implements Command{

		@Override
		public void doCommand(String[] args)  {
			String name = args[1];
			String filename = args[2];
			model.savemaze(name,filename);
			
			
			}
	}
	public class DisplaySolutionCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[1];
			Solution<Position> sol = model.getsolution(name);
			if (!(sol==null)) { view.displaysolution(sol);
		}
	
		}
		
	}
public class Displaycrosscommand implements Command {

	@Override
	public void doCommand(String[] args) {
		int index=Integer.parseInt(args[1]);
		String z_y_x = args[2];
		String name = args[3];
		int[][] todisplay=model.getcrossbyindex(index,z_y_x,name);
		view.displaycross(todisplay);
		
	}
	
	
}

public class exitcommand implements Command{

	@Override
	public void doCommand(String[] args) {
		model.exit();
		
	}
	
	
}

public class loadcommand implements Command
{

	@Override
	public void doCommand(String[] args) {
		String fileName = args[1];
		String mazeName = args[2];
		model.load(fileName, mazeName);
		
	}
	
}
public class dircommand implements Command{
	


	@Override
	public void doCommand(String[] args) {
		model.dirpath (args);
		}
		}
		
	
}

	
	
	


