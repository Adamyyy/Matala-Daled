package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.demo.Mazeadapter;
import algorithms.mazeGenerators.CellChoosing;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.LastCellChooser;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.RandomCellChooser;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;
import boot.RunProperties;
import presenter.Properties;

public class Mymodel extends Observable implements model {

	private ExecutorService executor;
	private Map<String,Maze3d> mazes;
	private Map<String, Solution<Position>> solutions;
	private Properties properties= new Properties("properties");
	
	
	public Mymodel() {
		executor = Executors.newFixedThreadPool(properties.getNumberOfThreads());
		mazes= new HashMap<String,Maze3d>();
		solutions = new HashMap<String,Solution<Position>>();
	
		properties= new Properties("properties");
	}
	
	
	@Override
	public void generatemaze(String name, int z, int y, int x) {
	
		
		
		executor.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				
				CellChoosing LCC= null;
				if (properties.getCellchooser().equals("LCC")) {LCC=new LastCellChooser();}
				if (properties.getCellchooser().equals("RANDOM")) {LCC=new RandomCellChooser();}
				GrowingTreeGenerator generator=new GrowingTreeGenerator(LCC);	
				Maze3d maze= generator.generate(z, y, x);
				mazes.put(name,maze);
				setChanged();
				if(properties.getViewtype().equals("CLI")){notifyObservers("Maze " + name + " is ready");}
				
				return maze;
			}
			
		});
			
	}



	


	public Properties getProperties() {
		return properties;
	}


	public void setProperties(Properties properties) {
		this.properties = properties;
	}


	@Override
	public void solvemaze(String name) {
		executor.submit(new Callable<Solution<Position>>() {

		
			@Override
			public Solution<Position> call() throws Exception {
				Maze3d tosolve= mazes.get(name);
				String algoname=properties.getSearchAlgorithm();
				Solution<Position> sol;
				Searchable<Position> SearchableMaze= new Mazeadapter(tosolve);
				Searcher<Position> searcher= null;
				
				if (algoname.equals("DFS")) {searcher=new DFS<State>();;}
				if (algoname.equals("BFS")) {searcher=new BFS<Position>();}
				 sol=searcher.search(SearchableMaze);
				 solutions.put(name,sol);
				 setChanged();
				 notifyObservers("Solution " + name + " is ready" );
				 return sol;
			}
			
		});
			
	}
		
	

	@Override
	public void savemaze(String name, String filename) {
		  Maze3d maze = mazes.get(name);
		  if (!mazes.containsKey(name)) {
			setChanged();
			notifyObservers("Error! can't find a maze solution with the name: '" + name + "'");
		    return;
		  }
		  byte[] bytearray = maze.toByteArray();
		  MyCompressorOutputStream out;
		  try {
		   out = new MyCompressorOutputStream(new FileOutputStream(filename));
		   out.write(bytearray);
		   setChanged();
			notifyObservers(name + " has been saved under the name " + filename);;


		  } catch (FileNotFoundException e1) {
			  setChanged();
				 notifyObservers("file error");
		  } catch (IOException e) {
			  setChanged();
				 notifyObservers("file error");;
		  }

		 }


	@Override
	public int[][] getcrossbyindex(int index, String x_y_z, String name) {
		int [][]toreturn = null;
		int firstnumber=0;
		int secondnumber=0;
		
		if (!(x_y_z.equals("y")) && !( x_y_z.equals( "z")) && !(x_y_z.equals("x"))) {setChanged(); notifyObservers("invalid index"); return null;}
		if (!mazes.containsKey(name)) {
			setChanged(); notifyObservers("There is no maze solution with the name: " + name);
			return null;
		}
		Maze3d tocut = mazes.get(name);

try{
if (x_y_z.equals("y")) {toreturn=tocut.getCrossSectionByY(index);}
if (x_y_z.equals("x")) {toreturn=tocut.getCrossSectionByX(index);}
if (x_y_z.equals("z")) {toreturn=tocut.getCrossSectionByZ(index);}
}
catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return toreturn;
	}

	@Override
	public void dirpath(String[] args) {
		if(args.length != 1) {
			setChanged();
			notifyObservers("Invalid path");
			return;
		}
		File file = new File(args[0].toString());
		if(!file.exists()) {
			setChanged();
			notifyObservers("Directory not found");
			return;
		}

		if(!file.isDirectory()) {
			setChanged();
			notifyObservers("Path is incorrect");
			return;
		}

		
	}

	@Override
	public void exit() {
		executor.shutdown();
		setChanged();
		notifyObservers("You decided to leave ");
		
	}

	@Override
	public void load(String filename, String mazename) {
		{
			File file = new File(filename);

			  if (!(file.exists())) {
			   setChanged();
			   notifyObservers("Error! File isn't exist");
			   return;
			  }
			  
			  
			  try {
			   MyDecompressorInputStream in = new MyDecompressorInputStream(new FileInputStream(filename));
			   byte bytearray[] = new byte[(int) file.length()*2];
			   in.read(bytearray);
			   in.close();
			   Maze3d newmaze = new Maze3d(bytearray);
			   mazes.put(mazename, newmaze); in .close();
			   setChanged();
			   notifyObservers(filename +" loaded to " + mazename);
			   return;
			  }

			  catch (FileNotFoundException e1) {
			 setChanged();
			 notifyObservers("file error");;
			  } catch (IOException e) {
				  setChanged();
					 notifyObservers("file error");;
			  }
			 


		}
	}
		
	


	@Override
	public Maze3d getmaze(String mazename) {
		Maze3d maze = mazes.get(mazename);
		if (!mazes.containsKey(mazename)) {
			setChanged();
			notifyObservers("The maze " + mazename + " doesnt exist ");
			return null;
		}
		else {return maze;}
		
	}


	@Override
	public Solution<Position> getsolution(String name) {
		Solution <Position> sol = solutions.get(name);
		if (!mazes.containsKey(name)) {
			setChanged();
			notifyObservers("The solution " + name + " doesnt exist ");
			return null;
		}
		else {return sol;}
	}
	

}
