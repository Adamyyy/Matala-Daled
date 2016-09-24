package boot;

import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import View.Myview;
import model.Mymodel;
import presenter.Presenter;
import presenter.Properties;



public class Run {

	public static void main(String[] args) {
		
		
		try 
		{//	public Properties(int numberOfThreads , String Algorithm , String searchAlgorithm , int maxMazeSize , String viewSetup) {
			File file = new File("properties");
			
			
			XMLEncoder xmlE = new XMLEncoder(new FileOutputStream(file));
			Properties towrite = new Properties(50, "GrowingTree", "BFS" , "LCC","GUI");
			xmlE.writeObject(towrite);
			xmlE.close();
			//System.out.println("XML File created successfuly!");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
				
		Myview view= new Myview(in, out);
		Mymodel model = new Mymodel();
		
		Presenter presenter = new Presenter(view, model);
		model.addObserver(presenter);
		view.addObserver(presenter);
				
		view.start();
	}

}
