package View;

import java.awt.Color;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.LastCellChooser;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MazeDisplay extends Canvas{

	//private Character character;
	private int[][] mazeCurFloor;
	private Position goal;
	Solution<Position> sol;
	Maze3d maze;
	int[][][] tempMaze;

	 int[][] mazeData={
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			};

	
public void getmazeatodisplay(Maze3d insert)
{
	try {
	mazeCurFloor=insert.getCrossSectionByZ(1);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
goal=insert.getGoalposition();
sol=null;
maze=insert;
tempMaze=insert.getMaze();
mazeData = mazeCurFloor;
	
}
	
	
 public MazeDisplay(Composite parent,int style){
        super(parent, style);
        
        setBackground(new org.eclipse.swt.graphics.Color(null, 255, 255, 255));
        addPaintListener(new PaintListener() {
    		
        	@Override
			public void paintControl(PaintEvent e) {
        		e.gc.setForeground(new org.eclipse.swt.graphics.Color(null, 0, 0, 0));
				   e.gc.setBackground(new org.eclipse.swt.graphics.Color(null, 0, 0, 0));
				   

				   int width=getSize().x;
				   int height=getSize().y;

				   int w=width/mazeData[0].length;
				   int h=height/mazeData.length;

				   for(int i=0;i<mazeData.length;i++)
				      for(int j=0;j<mazeData[i].length;j++){
				          int x=j*w;
				          int y=i*h;
				          if(mazeData[i][j]!=0)
				              e.gc.fillRectangle(x,y,w,h);
				      }
				   
				 
			//	   character.draw(w, h, e.gc);
				
			}
		});

 }
 
 
 

	}



