package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;

public class CLI extends Observable {
	private BufferedReader in;
	private PrintWriter out;	
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;		
	}
	

	
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				printinstructions();

				while (true) {
				
					try {
						String commandLine = in.readLine();
						setChanged();
						notifyObservers(commandLine);
						
						if (commandLine.equals("exit"))
							break;
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
		});
		thread.start();		
	}

	
	
	public void printinstructions ()
	{
		System.out.println("Please enter the commnad with no capital letters,seperate words with 1 space (commands are on the left parameters in <>)");
		System.out.println("Command dirpath <path>");
		System.out.println("Command generatemaze <name> <floors> <rows> <cols>");
		System.out.println("Command display <name>");
		System.out.println("Command displaycross <index> <x/y/z> <name>");
		System.out.println("Command savemaze <name> <file name>");
		System.out.println("command load <file name> <name> ");
		System.out.println("command solvemaze <name>");
		System.out.println("Command displaysolution: <name>");
		System.out.println("Command Exit: Bye!");

	}
}
