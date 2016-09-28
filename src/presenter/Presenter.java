package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import View.view;
import model.model;

public class Presenter implements Observer{

	private view view;
	private model model;
	private CommandsManager commandsManager;
	private HashMap<String, Command> commands;

	public Presenter(view insertview, model insertmodel) {
		this.view=insertview;
		this.model=insertmodel;

		commandsManager = new CommandsManager(model, view);
		commands = commandsManager.getCommandsMap();
	}


	@Override
	public void update(Observable o, Object arg) {
		if (o==view) {
			String commandline =(String)arg;
			String[] commandarr=commandline.split(" ");
			String maincommand = commandarr[0];
			if (commands.get(maincommand) !=null) {commands.get(maincommand).doCommand(commandarr);} 
			if (commands.get(maincommand)==null) {view.displayerror("Invalid command");	}
		}
		if (o==model) {

			String viewtype = model.getProperties().getViewtype();
			if (viewtype.equals("CLI")) {
				String message=(String) arg;
				view.displayerror(message);}

		}

	}



}
