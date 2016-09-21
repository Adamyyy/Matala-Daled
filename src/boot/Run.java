package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import View.Myview;
import model.Mymodel;
import presenter.Presenter;



public class Run {

	public static void main(String[] args) {
		
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
