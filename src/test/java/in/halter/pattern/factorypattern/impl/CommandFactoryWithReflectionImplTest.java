/**
 * 
 */
package in.halter.pattern.factorypattern.impl;

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
public class CommandFactoryWithReflectionImplTest {

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandFactoryWithReflectionImpl#CommandFactoryWithReflectionImpl()}.
	 */
	@Test
	public final void testCommandFactoryWithReflectionImpl() {
		CommandFactory commandFactory = new CommandFactoryWithReflectionImpl();
		assertThat(commandFactory, instanceOf(CommandFactory.class));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandFactoryWithReflectionImpl#createCommand(in.halter.pattern.factorypattern.api.CommandContext)}.
	 * "CMD1234" is normal implemented Command
	 */
	@Test
	public final void testCreateCommandByConvention() {
		CommandFactory commandFactoryWithReflection = new CommandFactoryWithReflectionImpl();
		CommandContext commandContext = new CommandContextImpl();
		Properties allCommandsByReflectionProperties = new Properties();
//		allCommandsByReflectionProperties.setProperty("CMD1234", "in.halter.pattern.factorypattern.impl.command.java.CMD1234J");
		commandContext.installConfigurationProperties(CommandFactoryWithReflectionImpl.CMD_MAPPING, allCommandsByReflectionProperties);
		commandContext.registerCommandFactory("commandFactoryWithReflection", commandFactoryWithReflection);
		commandContext.setCommandID("CMD1234");
		Command command = commandFactoryWithReflection.createCommand(commandContext);
		assertThat(command, notNullValue(Command.class));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandFactoryWithReflectionImpl#createCommand(in.halter.pattern.factorypattern.api.CommandContext)}.
	 * "CMD2222" is Placeholder Command mapped to a generic implemented Command
	 */
	@Test
	public final void testCreateCommandByConfigurationGeneric() {
		CommandFactory commandFactoryWithReflection = new CommandFactoryWithReflectionImpl();
		CommandContext commandContext = new CommandContextImpl();
		Properties allCommandsByReflectionProperties = new Properties();
		allCommandsByReflectionProperties.setProperty("CMD2222", "in.halter.pattern.factorypattern.impl.command.generic.WriteToStepPageGeneric");
		commandContext.installConfigurationProperties(CommandFactoryWithReflectionImpl.CMD_MAPPING, allCommandsByReflectionProperties);
		commandContext.registerCommandFactory("commandFactoryWithReflection", commandFactoryWithReflection);
		commandContext.setCommandID("CMD2222");
		Command command = commandFactoryWithReflection.createCommand(commandContext);
		assertThat(command, notNullValue(Command.class));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandFactoryWithReflectionImpl#createCommand(in.halter.pattern.factorypattern.api.CommandContext)}.
	 * "CMD9922" is Placeholder Command mapped to a not existing implemented Command
	 */
	@Test
	public final void testCreateCommandByConfigurationNonExistingKlassname() {
		CommandFactory commandFactoryWithReflection = new CommandFactoryWithReflectionImpl();
		CommandContext commandContext = new CommandContextImpl();
		Properties allCommandsByReflectionProperties = new Properties();
		allCommandsByReflectionProperties.setProperty("CMD9922", "in.halter.pattern.factorypattern.impl.command.generic.NotExistGeneric");
		commandContext.installConfigurationProperties(CommandFactoryWithReflectionImpl.CMD_MAPPING, allCommandsByReflectionProperties);
		commandContext.registerCommandFactory("commandFactoryWithReflection", commandFactoryWithReflection);
		commandContext.setCommandID("CMD9922");
		Command command = commandFactoryWithReflection.createCommand(commandContext);
		assertThat(command, nullValue(Command.class));
	}
}
