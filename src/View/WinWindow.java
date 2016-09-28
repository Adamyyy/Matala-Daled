package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class WinWindow extends DialogWindow {
	Shell shell;
	String message;
	
	public WinWindow(String insertmessage) {
		message=insertmessage;
	}
	
	
	public void start(Display display) {		
		shell = new Shell(display);
		initWidgets();
		shell.open();
		shell.close();
	}
	
	@Override
	protected void initWidgets() {
	
	
		shell.setSize(300, 230);		
				
		shell.setLayout(new GridLayout(2, false));	
		
					
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Title");
		
				
				msg.setMessage(message);
				
				msg.open();
				//shell.close();
				//setChanged();
				
				//notifyObservers("generatemaze" +" " + mazename + " " + z + " " + y +" "+ x);//(args);
				
			
			}
			
			
		
	}

