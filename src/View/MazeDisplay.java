package View;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;


import algorithms.mazeGenerators.Position;


import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public class MazeDisplay extends Canvas{

	Timer timer;
	TimerTask timerTask;
	protected Character ch;;
	private int[][] mazeCurFloor;
	Position goal;
	Solution<Position> sol;
	Maze3d maze;
	int[][][] tempMaze;
	int[][] mazeData;
	int currentlevel;
	Position startingposition;
	WinWindow winwin;
	boolean onswitch = false;
	String mazename;

	
	public boolean isOnswitch() {
		return onswitch;
	}


	public void setOnswitch(boolean onswitch) {
		this.onswitch = onswitch;
	}
	
	
	
	


	public String getMazename() {
		return mazename;
	}


	public void setMazename(String mazename) {
		this.mazename = mazename;
	}


	public Solution<Position> getSol() {
		return sol;
	}
	
	public void setSol(Solution<Position> sol) {
		this.sol = sol;
	}
	
	public void displaySol() {
		ArrayList<State<Position>> solPath = (ArrayList<State<Position>>)sol.getres();
		Collections.reverse(solPath);
		timer = new Timer();
		timerTask = new TimerTask() {
			
			@Override
			public void run() {
				getDisplay().syncExec(new Runnable() {
					public void run() {
						Position currentPos;
						if(!solPath.isEmpty()) {
							currentPos = solPath.get(0).getState();
							solPath.remove(0);
							if(ch.getPos().z != currentPos.z){
								mazeCurFloor=(maze.getCrossSectionByZ(currentPos.z));
								mazeData = mazeCurFloor;
								currentlevel=ch.getPos().z;
							}
							ch.setPos(currentPos);
							redraw();
						} else {
							timer.cancel();
							timerTask.cancel();
							if(goal.equals(ch.getPos())) {
								winwin= new WinWindow("Win!");
								winwin.start(getDisplay()); 
							}
						}
					}
				});
				
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 500);
		
	}




	public MazeDisplay(Composite parent,int style,Maze3d insert){
		super(parent, style);
		ch=new Character();
		onswitch=true;
		try {
			mazeCurFloor=insert.getCrossSectionByZ(insert.getStartingposition().getZ());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		goal=insert.getGoalposition();
		sol=null;
		maze=insert;
		startingposition=maze.getStartingposition();
		tempMaze=insert.getMaze();
		mazeData = mazeCurFloor;
		mazename=insert.getMazename();
		maze.setfreewithPosition(insert.getStartingposition());
	
		maze.markgoal();
		ch.setPos(maze.getStartingposition());
		
	this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				Position p=ch.getPos();
				Position start=maze.getStartingposition();
				ArrayList<String> _posibleMoves =maze.getallpossiblemovesasstring(ch.getPos());
				String _myDer=null;
				boolean _validMove=false;
				boolean _found=false;
//				b
				switch (e.keyCode) {
				case SWT.PAGE_UP:	
					_myDer="Uplevel";
					break;
				case SWT.PAGE_DOWN:	
					_myDer="Downlevel";
					//_myDer=Character.Direction.Right;
					break;
				case SWT.ARROW_RIGHT:	
					_myDer="Right";
					//_myDer=Character.Direction.Right;
					break;
				case SWT.ARROW_LEFT:		
					//_myDer=Character.Direction.Left;
					_myDer="Left";
					break;
				case SWT.ARROW_UP:		
//					_myDer=Character.Direction.Up;
					_myDer="Up";
					break;
				case SWT.ARROW_DOWN:		
//					_myDer=Character.Direction.Down;
					_myDer="Down";
				
					break;
				}
				for (int i = 0; i < _posibleMoves.size(); i++) {
					if (_myDer==_posibleMoves.get(i)) {
						_validMove=true; 
					}
				}
				
				if(_validMove){
					ch.move(_myDer);
				
					if(ch.getPos().equals(goal)) {;
					winwin= new WinWindow("Win!");
					winwin.start(getDisplay()); 
					return;
					};
					if(ch.getPos().equals(goal)) {}
					
					if(_myDer.equals("Uplevel") ||_myDer.equals("Downlevel")){
						
						
						mazeCurFloor=(maze.getCrossSectionByZ(ch.getPos().z));
						currentlevel=ch.getPos().z;
					
						
						
						redraw();
						
					}
					
				redraw();	
				}
			}
		});
		{
			
			


			setBackground(new org.eclipse.swt.graphics.Color(null, 255, 255, 255));
			addPaintListener(new PaintListener() {

				@Override
				public void paintControl(PaintEvent e)
				{
					
					


					e.gc.setBackground(new Color(null, 0, 0, 0));
					e.gc.setForeground(new Color(null, 255, 255, 255));

					int width = getSize().x;
					int height = getSize().y;

					Image imgwall = new Image(getDisplay(), "images/wall.jpg");
					Image path = new Image(getDisplay(), "images/road.jpg");
					Image goalimage = new Image(getDisplay(), "images/goal.jpg");
					
					int w = width / mazeCurFloor.length;
					int h = height / mazeCurFloor.length;

					for (int i = 0; i < mazeCurFloor.length; i++)
						for (int j = 0; j < mazeCurFloor[i].length; j++) {
							int x = j * w;
							int y = i * h;
							
							if (mazeCurFloor[i][j] != 0) e.gc.drawImage(imgwall, 0, 0, imgwall.getBounds().width, imgwall.getBounds().height, x, y, w, h);
							if (mazeCurFloor[i][j]==0) e.gc.drawImage(path, 0, 0, path.getBounds().width, path.getBounds().height, x, y, w, h);
							if (mazeCurFloor[i][j]==2) e.gc.drawImage(goalimage, 0, 0, goalimage.getBounds().width, goalimage.getBounds().height, x, y, w, h);
							
								
						}
					
			

					ch.draw(w, h, e.gc);


				}});

		}




	}	}
