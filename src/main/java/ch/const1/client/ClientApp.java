/**
 * 
 */
package ch.const1.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import ch.const1.factory.api.Command;
import ch.const1.factory.api.Context;
import ch.const1.factory.impl.ContextImpl;

/**
 * @author Roebi
 *
 */
public class ClientApp {

	/**
	 * @param args Programmarguments
	 */
	public static void main(String[] args) {
		if(args.length > 0) {
			String iaMappingPropertyFileName = args[0];
			File iaMappingPropertyFile = new File(iaMappingPropertyFileName);
			if(iaMappingPropertyFile.exists()) {
			System.out.println(iaMappingPropertyFile.getName() + " exists.");
			} else {
				try {
					System.out.println(iaMappingPropertyFile.getCanonicalPath() + " does not exist.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(iaMappingPropertyFile.exists()) {
				Properties iaMappingProperties = new Properties();
				try {
					iaMappingProperties.load(new FileReader(iaMappingPropertyFile));
					Context context = new ContextImpl();
					context.setIaMappingProperties(iaMappingProperties);
					String [] commandIds = {"X1234", "X2222", "X5678", "X2223", "X5678G"};
					for (String commandId : commandIds) {
						execCommand(commandId, context);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @param commandId 
	 * @param context
	 */
	private static void execCommand(String commandId, Context context) {
		context.setCommandID(commandId);
		Command command = context.createCommand();
		if(command != null) {
		System.out.println("ClientApp: Command Class for CommandId " + commandId + " is: " + command.getClass().getName());
		} else {
			System.out.println("ClientApp: Command Class for CommandId " + commandId + " is: not defined!");
		}
	}

}
