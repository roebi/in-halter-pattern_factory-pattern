/**
 * 
 */
package in.halter.pattern.factorypattern.impl;

import in.halter.pattern.factorypattern.api.Command;
import in.halter.pattern.factorypattern.api.CommandContext;
import in.halter.pattern.factorypattern.api.CommandFactory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Roebi
 *
 */
public class CommandFactoryWithConfigurationImpl implements CommandFactory {

	private static final Logger logger = LogManager.getLogger();

	public static final String CMD_MAPPING = "ALL_CMDS_BY_CONFIGURATION";
	public static final String CMD_PACKAGE_MAPPING = "CMD_PACKAGE_MAPPING";
	public static final String CMD_PACKAGE_IMPL_NORMAL = "CMD_PACKAGE_IMPL_NORMAL";
	public static final String CMD_PACKAGE_IMPL_GENERIC = "CMD_PACKAGE_IMPL_GENERIC";

	
	public CommandFactoryWithConfigurationImpl() {
	}

	/**
	 * @see in.halter.pattern.factorypattern.api.CommandFactory#createCommand(CommandContext)
	 */
	@Override
	public Command createCommand(CommandContext commandContext) {
		return createCommandWithConfigurationInternal(commandContext);
	}

	private Command createCommandWithConfigurationInternal(CommandContext commandContext) {
		// get Property
		Properties commandMappingProperties = commandContext.getPropertiesById(CMD_MAPPING);
		
		// search for commandId
		String commandId = commandContext.getCommandID();
		String commandMappingEntry = commandMappingProperties.getProperty(commandId);
		Command command = null;
		
		if(commandMappingEntry == null) {
			// if not found - Convention Command
			Properties packageNamesProperties = commandContext.getPropertiesById(CMD_PACKAGE_MAPPING);
			String packagePath = packageNamesProperties.getProperty(CMD_PACKAGE_IMPL_NORMAL);
			command = createNewCommandInstance(packagePath, commandId + "J");
			if(command == null) {
				String packagePathGenericCommand = packageNamesProperties.getProperty(CMD_PACKAGE_IMPL_GENERIC);
				command = createNewCommandInstance(packagePathGenericCommand, commandId);
			}
		} else {
			// if found - Configuration Command
			String fullClassName = commandMappingEntry;
			command = createNewCommandInstance(fullClassName);
		}
		return command;
	}

	/**
	 * @param fullClassName full qualified Class Name
	 * @return a new Command 
	 */
	private Command createNewCommandInstance(String fullClassName) {
		Class<?> commandClazz = null;
		try {
//			logger.info("fullClassName: " + fullClassName);
			commandClazz = Class.forName(fullClassName);
			Command command = (Command) commandClazz.newInstance();
			return command;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param packagePath Package of der Command Class
	 * @param commandClassName Command Class Name
	 * @return a new Command 
	 */
	private Command createNewCommandInstance(String packagePath, String commandClassName) {

		String fullClassName = packagePath + "." + commandClassName;
		Command command = createNewCommandInstance(fullClassName);
		return command;
	}

}
