package boot;


import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import View.Myview;
import View.Mywindow;
import model.Mymodel;
import presenter.Presenter;
import presenter.Properties;



public class Rungui {

	public static void main(String[] args) {
		
		
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		

		
		Mywindow window= new Mywindow(1000, 1000);
		Mymodel model = new Mymodel();
		
		Presenter presenter = new Presenter(window, model);
		model.addObserver(presenter);
		window.addObserver(presenter);
				
		window.run();
	}

}