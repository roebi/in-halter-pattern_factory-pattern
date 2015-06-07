/**
 * 
 */
package in.halter.pattern.factorypattern.impl;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
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
public class CommandFactoryWithConfigurationImplTest {

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandFactoryWithConfigurationImpl#CommandFactoryWithConfigurationImpl()}.
	 */
	@Test
	public void testCommandFactoryWithConfigurationImpl() {
		CommandFactory commandFactory = new CommandFactoryWithConfigurationImpl();
		assertThat(commandFactory, instanceOf(CommandFactory.class));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandFactoryWithConfigurationImpl#createCommand(in.halter.pattern.factorypattern.api.CommandContext)}.
	 * "CMD1234" is normal implemented Command
	 */
	@Test
	public  void testCreateCommandCMD1234withConfiguration() {
		CommandFactory commandFactoryWithConfiguration = new CommandFactoryWithConfigurationImpl();
		CommandContext commandContext = new CommandContextImpl();
		Properties allCommandsByConfigurationProperties = new Properties();
		allCommandsByConfigurationProperties.setProperty("CMD1234", "in.halter.pattern.factorypattern.impl.command.java.CMD1234J");
		commandContext.installConfigurationProperties(CommandFactoryWithConfigurationImpl.CMD_MAPPING, allCommandsByConfigurationProperties);
		commandContext.registerCommandFactory("commandFactoryWithConfiguration", commandFactoryWithConfiguration);
		commandContext.setCommandID("CMD1234");
		Command command = commandFactoryWithConfiguration.createCommand(commandContext);
		assertThat(command, notNullValue(Command.class));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandFactoryWithConfigurationImpl#createCommand(in.halter.pattern.factorypattern.api.CommandContext)}.
	 * "CMD1234" is normal implemented Command
	 */
	@Test
	public  void testCreateCommandCMD1234withConvention() {
		CommandFactory commandFactoryWithConfiguration = new CommandFactoryWithConfigurationImpl();
		CommandContext commandContext = new CommandContextImpl();
		Properties allCommandsByConfigurationProperties = new Properties();
		commandContext.installConfigurationProperties(CommandFactoryWithConfigurationImpl.CMD_MAPPING, allCommandsByConfigurationProperties);
		initFactoryWithPackageNames(commandContext);
		commandContext.registerCommandFactory("commandFactoryWithConfiguration", commandFactoryWithConfiguration);
		commandContext.setCommandID("CMD1234");
		Command command = commandFactoryWithConfiguration.createCommand(commandContext);
		assertThat(command, notNullValue(Command.class));
	}

	/**
	 * Test method for {@link in.halter.pattern.factorypattern.impl.CommandFactoryWithConfigurationImpl#createCommand(in.halter.pattern.factorypattern.api.CommandContext)}.
	 * "WriteToStepPageGeneric" is generic implemented Command
	 */
	@Test
	public  void testCreateCommandGenericwithConvention() {
		CommandFactory commandFactoryWithConfiguration = new CommandFactoryWithConfigurationImpl();
		CommandContext commandContext = new CommandContextImpl();
		Properties allCommandsByConfigurationProperties = new Properties();
		commandContext.installConfigurationProperties(CommandFactoryWithConfigurationImpl.CMD_MAPPING, allCommandsByConfigurationProperties);
		initFactoryWithPackageNames(commandContext);
		commandContext.registerCommandFactory("commandFactoryWithConfiguration", commandFactoryWithConfiguration);
		commandContext.setCommandID("WriteToStepPageGeneric");
		Command command = commandFactoryWithConfiguration.createCommand(commandContext);
		assertThat(command, notNullValue(Command.class));
	}
	
	private void initFactoryWithPackageNames(CommandContext commandContext) {
		Properties packageNamesProperties = new Properties();
		packageNamesProperties.setProperty(CommandFactoryWithConfigurationImpl.CMD_PACKAGE_IMPL_NORMAL, "in.halter.pattern.factorypattern.impl.command.java");
		packageNamesProperties.setProperty(CommandFactoryWithConfigurationImpl.CMD_PACKAGE_IMPL_GENERIC, "in.halter.pattern.factorypattern.impl.command.generic");
		commandContext.installConfigurationProperties(CommandFactoryWithConfigurationImpl.CMD_PACKAGE_MAPPING, packageNamesProperties);
	}
}
