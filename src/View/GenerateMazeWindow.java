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

public class GenerateMazeWindow extends DialogWindow {
	Shell shell;
	String name=new String();
	
	public void start(Display display) {		
		shell = new Shell(display);
		initWidgets();
		shell.open();		
	}
	
	@Override
	protected void initWidgets() {
	
		shell.setText("Generate maze window");
		shell.setSize(300, 230);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Label mazename = new Label(shell, SWT.NONE);
		mazename.setText("Maze Name: ");
		
		Text txtmaze = new Text(shell, SWT.BORDER);
		txtmaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		
		Label lblfloors = new Label(shell, SWT.NONE);
		lblfloors.setText("Floors: ");
		
		Text txtfloors = new Text(shell, SWT.BORDER);
		txtfloors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setText("Rows: ");
		
		Text txtRows = new Text(shell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblCols = new Label(shell, SWT.NONE);
		lblCols.setText("Cols: ");
		
		Text txtCols = new Text(shell, SWT.BORDER);
		txtCols.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				
		Button btnGenerateMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnGenerateMaze.setText("Generate maze");
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Title");
				//msg.setMessage("Button was clicked");
				String mazename= txtmaze.getText();
				String z= txtfloors.getText();
				String y = txtRows.getText();
				String x = txtRows.getText();
				
				
				msg.setMessage("Generating maze : " + mazename);
				
				msg.open();
				setChanged();
				notifyObservers("generatemaze" +" " + mazename + " " + z + " " + y +" "+ x);//(args);
				shell.close();
			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
