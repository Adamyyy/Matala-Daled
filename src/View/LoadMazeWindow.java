package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoadMazeWindow extends DialogWindow {
	Shell shell;


	public void start(Display display) {		
		shell = new Shell(display);
		initWidgets();
		shell.open();		
	}

	@Override
	protected void initWidgets() {

		shell.setText("Load maze window");
		shell.setSize(300, 230);		

		shell.setLayout(new GridLayout(2, false));	

		Label mazename = new Label(shell, SWT.NONE);
		mazename.setText("Maze Name: ");

		Text txtmaze = new Text(shell, SWT.BORDER);
		txtmaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));


		Label lblmazefile = new Label(shell, SWT.NONE);
		lblmazefile.setText("File name : ");

		Text txtfile = new Text(shell, SWT.BORDER);
		txtfile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));


		Button loadbutton = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(loadbutton);
		loadbutton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		loadbutton.setText("Generate maze");

		loadbutton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Title");

				String mazename= txtmaze.getText();
				String filename = txtfile.getText();


				msg.setMessage("loading: " + mazename);

				msg.open();
				setChanged();
				notifyObservers("load " + filename + " " + mazename);//(args);
				shell.close();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			

			}
		});	

	}

}