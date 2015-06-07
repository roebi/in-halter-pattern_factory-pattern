/**
 * 
 */
package in.halter.pattern.factorypattern.impl;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import in.halter.pattern.factorypattern.api.Command;
import in.halter.pattern.factorypattern.api.CommandContext;
import in.halter.pattern.factorypattern.api.CommandFactory;

import java.util.Properties;

import org.junit.Test;

/**
 * @author Roebi
 *
 */
public class CommandContextImplTest {

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandContextImpl#getPropertiesById(java.lang.String)}.
	 */
	@Test
	public void testGetPropertiesById() {
		CommandContext commandContext = new CommandContextImpl();
		Properties properties = commandContext.getPropertiesById("");
		assertThat(properties, nullValue(Properties.class));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandContextImpl#getCommandID()}.
	 */
	@Test
	// IllegalArgumentException
	public void testGetCommandID() {
		CommandContext commandContext = new CommandContextImpl();
		commandContext.setCommandID("CMD1234");
		String commandId = commandContext.getCommandID();
		assertThat(commandId, instanceOf(String.class));
		assertThat(commandId, equalTo("CMD1234"));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandContextImpl#installConfigurationProperties(java.lang.String, java.util.Properties)}.
	 */
	@Test
	public void testInstallConfigurationProperties() {
		CommandContext commandContext = new CommandContextImpl();
		Properties configurationPropertiesIn = new Properties();
		commandContext.installConfigurationProperties("PROPS1", configurationPropertiesIn);
		Properties configurationPropertiesOut = commandContext.getPropertiesById("PROPS1");
		assertThat(configurationPropertiesOut, notNullValue(Properties.class));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandContextImpl#setCommandID(java.lang.String)}.
	 */
	@Test
	public void testSetCommandID() {
		CommandContext commandContext = new CommandContextImpl();
		commandContext.setCommandID("CMD1234");
		String commandId = commandContext.getCommandID();
		assertThat(commandId, instanceOf(String.class));
		assertThat(commandId, equalTo("CMD1234"));
	}

	private class DummyCommandFactory implements CommandFactory {
		/**
		 * @see in.halter.pattern.factorypattern.api.CommandFactory#createCommand(java.lang.String)
		 */
		@Override
		public Command createCommand(CommandContext commandContext) {
			Command command = new DummyCommand();
			return command;
		}
	}

	private class DummyCommand implements Command {
		/**
		 * @see in.halter.pattern.factorypattern.api.Command#exec(in.halter.pattern.factorypattern.api.CommandContext)
		 */
		@Override
		public void exec(CommandContext commandContext) {
		}
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandContextImpl#registerCommandFactory(java.lang.String, in.halter.pattern.factorypattern.api.CommandFactory)}.
	 */
	@Test
	public void testRegisterCommandFactory() {
		CommandContext commandContext = new CommandContextImpl();
		CommandFactory commandFactory = new DummyCommandFactory();
		commandContext.registerCommandFactory("DummyID", commandFactory);
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandContextImpl#createCommand()}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCommandWithoutFactory() {
		CommandContext commandContext = new CommandContextImpl();
		Command command = commandContext.createCommand();
		assertThat(command, nullValue(Command.class));
	}
	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandContextImpl#createCommand()}.
	 */
	@Test
	public void testCreateCommand() {
		CommandContext commandContext = new CommandContextImpl();
		CommandFactory commandFactory = new DummyCommandFactory();
		commandContext.registerCommandFactory("DummyID", commandFactory);
		Command command = commandContext.createCommand();
		assertThat(command, notNullValue(Command.class));
	}
}
