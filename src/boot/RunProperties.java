package boot;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import presenter.Properties;

public class RunProperties {

	public static void main(String[] args) 
	{
		
		
		
		try 
		{//	public Properties(int numberOfThreads , String Algorithm , String searchAlgorithm , int maxMazeSize , String viewSetup) {
			File file = new File("properties");
			
			
			XMLEncoder xmlE = new XMLEncoder(new FileOutputStream(file));
			Properties towrite = new Properties(50, "GrowingTree", "BFS" , "LCC", "GUI");
			xmlE.writeObject(towrite);
			xmlE.close();
			System.out.println("XML File created successfuly!");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	Properties test = new Properties("properties");
	}
}