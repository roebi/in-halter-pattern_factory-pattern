/**
 * 
 */
package in.halter.pattern.factorypattern.api;

import java.util.Properties;

/**
 * The CommandContext is used for the surroundings around the Command.
 * The Content of the Commandcontext depends on the Businesscase in which the Commands are used.
 *
 * @since 1.0
 * @author Roebi
 */
public interface CommandContext {

	/**
	 * Return a newly created Command. The concrete Creation is delegated to the registered CommandFactories.
	 * The CommandContext Implementation define the behavior, if more than one CommandFactories are registered.
	 * 
	 * @return Command a newly created Command.
	 * @since 1.0
	 */
	public abstract Command createCommand();

	/**
	 * Set the Command ID as a String Identifier to define which Command the CommandContext should create.
	 * For each Command Creation the Command Id should be set first.
	 * 
	 * @param commandID the Command ID as a String Identifier to define which Command the CommandContext should create.
	 * @since 1.0
	 */
	public abstract void setCommandID(String commandID);

	/**
	 * Register a CommandFactory.
	 * The CommandContext Implementation define the behavior, if more than one CommandFactories are registered.
	 * 
	 * @param commandFactoryId a CommandFactory Id used for Identification, if more than one CommandFactories are registered.
	 * @param commandFactory a CommandFactory Implementation.
	 * @since 1.0
	 */
	public abstract void registerCommandFactory(String commandFactoryId, CommandFactory commandFactory);

	/**
	 * Install a Properties Object in the CommandContext.
	 * The Properties are Grouped Configurations in this Businesscase.
	 * i.E. to hold Mapping for the CommandFactories.
	 * i.E. to configure the CommandContext itself, i.E. define some environment URLs, Paths.
	 * The CommandContext Implementation define the behavior, if more than one Properties Objects are registered.
	 * 
	 * @param configurationPropertiesId a Property Id used for Identification, if more than one Properties Objects are registered.
	 * @param configurationProperties Properties Object.
	 * @since 1.0
	 */
	public abstract void installConfigurationProperties(String configurationPropertiesId, Properties configurationProperties);

	/**
	 * Return a previously installed Properties Object identified by a String Id.
	 * Gives Access to the installed Properties Objects in the CommandContext.
	 * 
	 * @param configurationPropertiesId the Properties Id for which the Properties be requested.  
	 * @return the requested Properties if available.
	 * @since 1.0
	 */
	public abstract Properties getPropertiesById(String configurationPropertiesId);

	/**
	 * Return the previously set Command ID.
	 * Normally used by a CommandFactory to know which Command is to be created.
	 *  
	 * @return the Command Id if available.
	 * @since 1.0
	 */
	public abstract String getCommandID();
}
