package View;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class Basicwindow extends Observable implements Runnable{



	Display display;  // our display
	Shell shell;

	public Basicwindow(int orech,int rochav) {
		display=new Display();
		shell = new Shell(display);
		shell.setSize(orech, rochav);

	}
	abstract void initwidgets();

	@Override
	public void run() {
		initwidgets();
		shell.open();
		while(!shell.isDisposed()){ 

			if(!display.readAndDispatch()){ 	// if the queue is empty
				display.sleep(); 			
			}

		} // shell is disposed

		display.dispose(); 
	}


}