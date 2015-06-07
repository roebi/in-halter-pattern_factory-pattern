/**
 * 
 */
package in.halter.pattern.factorypattern.api;


/**
 * CommandFactory creates Commands.
 * Information Exchange is processed over the given CommandContext.
 * 
 * @since 1.0
 * @author Roebi
 */
public interface CommandFactory {
	
	/**
	 * Create in is Implementation a Command.
	 * Information Exchange is processed over the given CommandContext.
	 * 
	 * @param commandContext The CommandFactory ask the CommandContext which Command is to be created.  
	 * @return Command the newly created Command Instance.
	 * @since 1.0
	 */
	public abstract Command createCommand(CommandContext commandContext);
}
