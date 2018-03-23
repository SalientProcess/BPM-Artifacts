/**
 * Expose the ability to perform WebSphere Application Server (WAS) administrative
 * commands as a callable BPM service class.
 */
package com.salientprocess.bpm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.ibm.websphere.management.Session;
import com.ibm.websphere.management.cmdframework.AdminCommand;
import com.ibm.websphere.management.cmdframework.CommandMgr;
import com.ibm.websphere.management.cmdframework.CommandResult;

import teamworks.TWList;
import teamworks.TWObject;

public class BPM_AdminTask {

	/**
	 * Run a WAS admin task using the supplied command name and parameters.
	 * @param command The command to execute.
	 * @param parameters Parameters for the command.  This should be a list of BPM
	 * NameValuePair objects where the name is the name of the parameter and the 
	 */
	static public void runAdminTask(
			String command,
			TWList parameters) {
		try {
			if (command == null || command.isEmpty()) {
				throw new Exception("Command may not be null or empty.");
			}
			CommandMgr commandMgr = CommandMgr.getCommandMgr();          // Get a command manager.
			AdminCommand cmd = commandMgr.createCommand(command);        // Get an Admin Command.
			Session session = new Session();                             // Build a session for the command.
			cmd.setConfigSession(session);                               // Associate the session withthe command.
			if (parameters != null) {                                    // Build parameters (if any) for the command.
				for (int i=0; i<parameters.getArraySize(); i++) {        // Loop over each of the parameters, associating them with the command.
					TWObject nameValuePair = (TWObject)parameters.getArrayData(i);
					cmd.setParameter((String)nameValuePair.getPropertyValue("name"), nameValuePair.getPropertyValue("value"));
				}
			}
			cmd.execute();                                               // Execute the command.
			CommandResult result = cmd.getCommandResult();
			System.out.println("Result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // runAdminTask

	
	/**
	 * A diagnostic command that obtains a list of the commands available and logs them to the console.
	 */
	static public void getCommands() {
		CommandMgr commandMgr = CommandMgr.getCommandMgr();
		try {
			@SuppressWarnings("unchecked")
			Collection<String> c = commandMgr.listAllCommands();
			ArrayList<String> listOfCommands = new ArrayList<>(c);
			for(Object o: c) {
				System.out.println("class: " + o.getClass() + ", o: " + o);
				
			}
			Collections.sort(listOfCommands);
			System.out.println("List: " + listOfCommands);
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // getCommands
} // BPM_AdminTask
