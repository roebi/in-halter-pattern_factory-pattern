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

/**
 * @author Roebi
 *
 */
public class CommandContextImpl implements CommandContext {

	private Map<String, CommandFactory> commandFactoriesMap = new HashMap<String, CommandFactory>();
	private Map<String, Properties> configurationPropertiesMap = new HashMap<String, Properties>();
	/**
	 * @return ConfigurationProperties identified by configurationPropertiesId 
	 */
	@Override
	public Properties getPropertiesById(String configurationPropertiesId) {
		return configurationPropertiesMap.get(configurationPropertiesId);
	}
	/**
	 * @return the commandID
	 */
	@Override
	public String getCommandID() {
		return commandID;
	}
	/**
	 * @param configurationPropertiesId Id of this ConfigurationProperties
	 * @param configurationProperties the commandMappingProperties to set
	 */
	@Override
	public void installConfigurationProperties(String configurationPropertiesId, Properties configurationProperties) {
		if(configurationPropertiesId != null && !"".equals(configurationPropertiesId)) {
			this.configurationPropertiesMap.put(configurationPropertiesId, configurationProperties);
		} else {
			throw new IllegalArgumentException("configurationPropertiesId is undefined.");
		}
	}
	String commandID = "";
	/**
	 * @param commandID the commandID to set
	 */
	@Override
	public void setCommandID(String commandID) {
		this.commandID = commandID;
	}
	/**
	 * @see in.halter.pattern.factorypattern.api.CommandContext#createCommand()
	 */
	@Override
	public Command createCommand() {
		Command command = null;
		if(commandFactoriesMap.isEmpty()) {
			throw new IllegalArgumentException("No CommandFactories registered.");
		} else {
			for (CommandFactory commandFactory : commandFactoriesMap.values()) {
				  command = commandFactory.createCommand(this);
				  if(command != null) break;
			}
		}
		return command;
	}
	@Override
	public void registerCommandFactory(String commandFactoryId,
			CommandFactory commandFactory) {
		if(commandFactoryId != null && !"".equals(commandFactoryId)) {
			commandFactoriesMap.put(commandFactoryId, commandFactory);
		} else {
			throw new IllegalArgumentException("CommandFactoryId is undefined.");
		}
	}

}
