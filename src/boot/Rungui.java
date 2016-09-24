package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import View.Myview;
import View.Mywindow;
import model.Mymodel;
import presenter.Presenter;



public class Rungui {

	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
				
		Mywindow window= new Mywindow(300, 300);
		Mymodel model = new Mymodel();
		
		Presenter presenter = new Presenter(window, model);
		model.addObserver(presenter);
		window.addObserver(presenter);
				
		window.run();
	}

}