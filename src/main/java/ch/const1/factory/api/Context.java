/**
 * 
 */
package ch.const1.factory.api;

import java.util.Properties;

/**
 * @author Roebi
 *
 */
public interface Context {

	public abstract Command createCommand();

	public abstract void setCommandID(String commandID);

	public abstract void setIaMappingProperties(Properties iaMappingProperties);

	public abstract Properties getIaMappingProperties();

	public abstract String getCommandID();
}
