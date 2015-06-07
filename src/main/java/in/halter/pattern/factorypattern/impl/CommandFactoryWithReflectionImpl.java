/**
 * 
 */
package in.halter.pattern.factorypattern.impl;

import in.halter.pattern.factorypattern.api.Command;
import in.halter.pattern.factorypattern.api.CommandContext;
import in.halter.pattern.factorypattern.api.CommandFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

/**
 * @author Roebi
 *
 */
public class CommandFactoryWithReflectionImpl implements CommandFactory {

	private static final Logger logger = LogManager.getLogger();

	private static final Map<String, String> commandClassesNames;
	static {
		Reflections reflections = new Reflections("in.halter.pattern.factorypattern.impl.command");

		String commandClassName = "in.halter.pattern.factorypattern.api.Command";
		try {
			Class commandClass = reflections.getClass().getClassLoader().loadClass(commandClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<Class<? extends Command>> commandClasses = reflections.getSubTypesOf(in.halter.pattern.factorypattern.api.Command.class);
		commandClassesNames = new HashMap<String, String>();
		logger.info("All Command Classes");
		for (Class<? extends Command> aCommandClass : commandClasses) {
			String fullClassName = aCommandClass.getName();
			int lastPointindex = fullClassName.lastIndexOf(".");
			String className = fullClassName.substring(lastPointindex  + 1);
			String commandId = className.substring(0, className.length() - 1);
			commandClassesNames.put(commandId, fullClassName);
			logger.info("- Command Class: " + className + ": " + fullClassName);
		}
	}

	public static final String CMD_MAPPING = "ONLY_CMDS_OUT_OF_CONVENTION";
	
	public CommandFactoryWithReflectionImpl() {
		super();
	}

	/**
	 * @see in.halter.pattern.factorypattern.api.CommandFactory#createCommand(CommandContext)
	 */
	@Override
	public Command createCommand(CommandContext commandContext) {
		return createCommandWithReflectionInternal(commandContext);
	}

	private Command createCommandWithReflectionInternal(CommandContext commandContext) {
		// get Property
		Properties commandMappingProperties = commandContext.getPropertiesById(CMD_MAPPING);
		
		// search for commandId
		String commandId = commandContext.getCommandID();
		String commandMappingEntry = commandMappingProperties.getProperty(commandId);
		Command command = null;
		
		if(commandMappingEntry == null) {
			// if not found - found Command by Convention
			for (String className : commandClassesNames.keySet()) {
				if(className.startsWith(commandId)) {
					String fullClassName = commandClassesNames.get(className);
					command = createNewCommandInstance(fullClassName);
					break;
				}
			}
		} else {
			// if found - found Command by Configuration
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
}
