package View;


import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
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
	LoadMazeWindow ld=new LoadMazeWindow();;
	WinWindow message;

	public Mywindow(int orech, int rochav) {
		super(orech, rochav);

	}

	@Override
	void initwidgets() {
		GridLayout grid = new GridLayout(2, false);
		shell.setLayout(grid);
		this.shell.setLayout(grid);
		shell.setBackgroundImage(new Image(null, "images/Kumar.jpg"));
		this.shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		this.shell.setSize(1000, 460);
		this.shell.setText("My Maze");

		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT | SWT.RESIZE|SWT.INHERIT_NONE| SWT.INHERIT_FORCE);
		//



		Composite buttons = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		buttons.setLayout(rowLayout);



		
		Button generatemazebutton = new Button(buttons, SWT.PUSH);
		generatemazebutton.setText("New game ");
		win.addObserver(this);

		generatemazebutton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Md==null){
				win.start(display);
				}
				else {
				Md.dispose();
				win.start(display);}


			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});



		Button btnSolveMaze = new Button(buttons, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		btnSolveMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(Md==null) {message=new WinWindow("No maze to solve");message.start(display);}
				else { String mazetosolve ="solvemaze " + Md.getMazename(); ; setChanged(); notifyObservers(mazetosolve);



				}


			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});


		Button savemazebtn = new Button(buttons, SWT.PUSH);
		savemazebtn.setText("Save maze ");
		savemazebtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(Md==null) {message=new WinWindow("No maze to save");message.start(display);}
				else {
				String mazetosave ="savemaze " + Md.getMazename() +" "+Md.getMazename();
				setChanged();
				notifyObservers(mazetosave);
				String insertmessage = Md.getMazename() + " has been saved!";
				message=new WinWindow(insertmessage);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		//User presses to start mazecreator and than game
				Button loadmazebutton = new Button(buttons, SWT.PUSH);
				
				loadmazebutton.setText("Load maze ");
				ld.addObserver(this);

				loadmazebutton.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (Md==null){
							win.start(display);
							}
							else {
							Md.dispose();
							ld.start(display);}
						
						



					}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub

					}
				});
		
		
		


		Button btnProperties = new Button(buttons, SWT.PUSH);
		btnProperties.setText("Properties  ");

		btnProperties.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("getproperties");



			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		
		Button btnE = new Button(buttons, SWT.PUSH);
		btnE.setText("     Exit        ");
		btnE.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.exit(0);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});


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
		if (o==ld) {setChanged(); notifyObservers(arg);}
	}

	@Override
	public void getmaze(Maze3d insert) {
		
		if (insert==null) return;
		
		else {
			Md=new MazeDisplay(shell, SWT.BORDER | SWT.DOUBLE_BUFFERED,insert);	
			Md.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1,12));
			Md.setFocus();
			shell.pack();

			;}

	}

	@Override
	public void getsolution(Solution<Position> sol) {
		if (Md.isOnswitch()==true){
			Md.setSol(sol);}
		Md.displaySol();

	}

	@Override
	public void getproperties(presenter.Properties p) {
		String properties =p.getAlgorithm() +" " + p.getCellchooser() +" "+ p.getViewtype()+" "+p.getSearchAlgorithm()+" " + p.getNumberOfThreads();
		message = new WinWindow(properties);
		message.start(display);

	}


}