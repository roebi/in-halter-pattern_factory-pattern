/**
 * 
 */
package in.halter.pattern.factorypattern.api;


/**
 * Command is a single Execution Unit i.E. a Step, in a serial Execution Sequence.
 * In this Context is parallelization not desired.
 * Because it is a successively Execution Process.
 * In this Usecase Undo is not desired.
 * Commands usually contains Parameters (Key Value).
 * In this Architecual Study they are omitted to more focus on the Factory / Location Service Pattern.
 *  
 * @since 1.0
 * @author Roebi
 */
public interface Command {

	/**
	 * exec ist the execution Part of this Command.
	 * Information Exchange is processed over the given CommandContext.
	 *  
	 * @param commandContext the given CommandContext to process Information Exchange. 
	 * @since 1.0
	 */
	public void exec(CommandContext commandContext);
}
