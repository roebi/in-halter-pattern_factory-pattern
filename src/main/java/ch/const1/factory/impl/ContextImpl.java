/**
 * 
 */
package ch.const1.factory.impl;

import java.util.Properties;

import ch.const1.factory.api.Command;
import ch.const1.factory.api.Context;
import ch.const1.factory.api.Factory;

/**
 * @author Roebi
 *
 */
public class ContextImpl implements Context {

	Properties iaMappingProperties = null; 
	/**
	 * @return the iaMappingProperties
	 */
	@Override
	public Properties getIaMappingProperties() {
		return iaMappingProperties;
	}
	/**
	 * @return the commandID
	 */
	@Override
	public String getCommandID() {
		return commandID;
	}
	/**
	 * @param iaMappingProperties the iaMappingProperties to set
	 */
	@Override
	public void setIaMappingProperties(Properties iaMappingProperties) {
		this.iaMappingProperties = iaMappingProperties;
	}
	Factory factory = new FactoryImpl();
	String commandID = "";
	/**
	 * @param commandID the commandID to set
	 */
	@Override
	public void setCommandID(String commandID) {
		this.commandID = commandID;
	}
	/* (non-Javadoc)
	 * @see ch.const1.factory.api.Context#createCommand()
	 */
	@Override
	public Command createCommand() {
		Command command = factory.createCommand(this);
		return command;
	}

}
