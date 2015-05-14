/**
 * 
 */
package ch.const1.factory.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;

import ch.const1.factory.api.Command;
import ch.const1.factory.api.Context;
import ch.const1.factory.api.Factory;

/**
 * @author Roebi
 *
 */
public class FactoryImpl implements Factory {

//	String pName = org.apache.commons.lang3.ClassUtils.getPackageName(this, iaClassName);
	static final Map<String, String> commandClassesNames;
	static {
		Reflections reflections = new Reflections("ch.const1.factory.impl");
		Set<Class<? extends Command>> commandClasses = reflections.getSubTypesOf(ch.const1.factory.api.Command.class);
		commandClassesNames = new HashMap<String, String>();
		System.out.println("All commandClasses");
		for (Class<? extends Command> commandClass : commandClasses) {
			String fullClassName = commandClass.getName();
			int lastPointindex = fullClassName.lastIndexOf(".");
			String className = fullClassName.substring(lastPointindex  + 1);
			commandClassesNames.put(className, fullClassName);
			System.out.println("- commandClass: " + className + ": " + fullClassName);
		}
	}
	/* (non-Javadoc)
	 * @see ch.const1.factory.api.Factory#createCommand(java.lang.String)
	 */
	@Override
	public Command createCommand(Context context) {
//		return createCommandWithPackages(context);
		return createCommandWithReflection(context);
	}

	private Command createCommandWithPackages(Context context) {
		// get Property
		Properties iaMappingProperties = context.getIaMappingProperties();
		
		// search for commandId
		String commandId = context.getCommandID();
		String iaMappingEntry = iaMappingProperties.getProperty(commandId);
		Command command = null;
		
		if(iaMappingEntry == null) {
			// if not found - Convention Command
			String packagePath = "ch.const1.factory.impl";
			command = createNewCommandInstance(packagePath, commandId);
			if(command == null) {
				String packagePathGroovy = "ch.const1.factory.impl.groovy";
				command = createNewCommandInstance(packagePathGroovy, commandId);
			}
		} else {
			// if found - Configuration Command
			String packagePath = "ch.const1.factory.impl.generic";
			command = createNewCommandInstance(packagePath, iaMappingEntry);
		}
		
		// search in all Command Implemented Commands
		
		return command;
	}

	private Command createCommandWithReflection(Context context) {
		// get Property
		Properties iaMappingProperties = context.getIaMappingProperties();
		
		// search for commandId
		String commandId = context.getCommandID();
		String iaMappingEntry = iaMappingProperties.getProperty(commandId);
		Command command = null;
		
		if(iaMappingEntry == null) {
			// if not found - Convention Command
			for (String className : commandClassesNames.keySet()) {
				if(className.startsWith(commandId)) {
					String fullClassName = commandClassesNames.get(className);
					command = createNewCommandInstance(fullClassName);
				}
			}
		} else {
			// if found - Configuration Command
			for (String className : commandClassesNames.keySet()) {
				if(className.startsWith(iaMappingEntry)) {
					String fullClassName = commandClassesNames.get(className);
					command = createNewCommandInstance(fullClassName);
				}
			}
		}
		return command;
	}

	private Command createNewCommandInstance(String fullClassName) {
		Class<?> commandClazz = null;
		try {
//			System.out.println("fullClassName: " + fullClassName);
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
	 * @param iaClassName
	 * @param commandId 
	 */
	private Command createNewCommandInstance(String packagePath, String iaClassName) {

		Class<?> commandClazz = null;
		try {
			String fullClassName = packagePath + "." + iaClassName;
//			System.out.println("fullClassName: " + fullClassName);
			commandClazz = Class.forName(packagePath + "." + iaClassName);
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
