/**
 * 
 */
package in.halter.pattern.factorypattern.impl;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import in.halter.pattern.factorypattern.api.Command;
import in.halter.pattern.factorypattern.api.CommandContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Unit Test for Command and its CommandContext and CommandFactory. 
 * @author Roebi
 *
 */
public class Util {
	
	private static final Logger logger = LogManager.getLogger();

	public static final String [] commandSequence = {
		"CMD1234", // normal implemented Command
		"CMD2222", // Placeholder Command mapped to a generic implemented Command
		"CMD5678", // normal implemented Command
		"CMD2223", // Placeholder Command mapped to a generic implemented Command
		"CMD2224"  // Placeholder Command mapped to a other normal implemented Command
                   // useful for i.e. backward compatibility
	};

	/**
	 * @param commandId 
	 * @param commandContext
	 */
	public static void execCommand(String commandId, CommandContext commandContext) {
		commandContext.setCommandID(commandId);
		Command command = commandContext.createCommand();
		//		assertThat(command, notNullValue(Command.class));
		if(command != null) {
			assertThat(command, instanceOf(Command.class));
			logger.info("ClientApp: Command Class for CommandId " + commandId + " is: " + command.getClass().getName());
		} else {
			logger.info("ClientApp: Command Class for CommandId " + commandId + " is: not defined!");
		}
	}

	public static Properties loadPropertiesFromFullFileName(String propertiesFullFileName) {
		Properties properties = new Properties();
		ClassLoader classLoader = Util.class.getClassLoader();
		File propertiesFile = new File(classLoader.getResource(propertiesFullFileName).getFile());
		
		if(propertiesFile.exists()) {
			logger.info(propertiesFile.getName() + " exists.");
			try {
				properties.load(new FileReader(propertiesFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				// fails:
				assertThat("propertiesFullFileName " + propertiesFile.getCanonicalPath() +
						" does not exist.", null, notNullValue(File.class));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return properties;
	}
}
