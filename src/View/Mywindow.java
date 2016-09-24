package View;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class Mywindow extends Basicwindow implements view,Observer {
	private BufferedReader in;
	private PrintWriter out;
	public GenerateMazeWindow win = new GenerateMazeWindow();
	MazeDisplay Md;
	
	public Mywindow(int orech, int rochav) {
		super(orech, rochav);
		
	}

	@Override
	void initwidgets() {
		GridLayout grid = new GridLayout(2, false);
		shell.setLayout(grid);
		Composite buttons = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		buttons.setLayout(rowLayout);
		
		
		
		//User presses to start mazecreator and than game
		Button generatemazebutton = new Button(buttons, SWT.PUSH);
		generatemazebutton.setText("New game");
		win.addObserver(this);
		
		generatemazebutton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				win.start(display);
				
				
			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
		
		Button btnSolveMaze = new Button(buttons, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		
		
		Button btnDisplayMaze = new Button(buttons, SWT.PUSH);
		btnDisplayMaze.setText("Display maze");
		
		
		Button btnProperties = new Button(buttons, SWT.PUSH);
		btnProperties.setText("Properties");

		Button btnE = new Button(buttons, SWT.PUSH);
		btnE.setText("Exit");
		
		Md=new MazeDisplay(shell, SWT.BORDER);	
		Md.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1,12));
		Md.setFocus();
		shell.pack();
	}

	
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayerror(String error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaysolution(Solution<Position> insert) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifymazehasbeensaved(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaycross(int[][] toreturn) {
		// TODO Auto-generated method stub
		
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
	public void update(Observable o, Object arg) {
		if (o==win) {setChanged(); notifyObservers(arg);}
	}

	@Override
	public void getmaze(Maze3d insert) {
		if (insert==null) return;
		else {Md.getmazeatodisplay(insert);}
		
	}
}