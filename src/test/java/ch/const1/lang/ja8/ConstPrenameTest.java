package ch.const1.lang.ja8;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.junit.Test;

/**
 * Unit Test for ConstPrename
 * @since 1.0
 */
public class ConstPrenameTest {

  @Test()
  public void testCallPrivateConstrutor() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    final Class<ConstPrename> clazz = ConstPrename.class;
    final Constructor<?> c = clazz.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[])null);
  }

  @Test()
  public void testBenIsString() {
    String prename = ConstPrename.C_PRENAME_BEN;
    assertThat(prename, instanceOf(String.class));
  }

  @Test()
  public void testBenNotNullString() {
    String prename = ConstPrename.C_PRENAME_BEN;
    assertThat(prename, notNullValue(String.class));
  }

  @Test()
  public void testBenFirstUppercase() {
    String prename = ConstPrename.C_PRENAME_BEN;
    assertThat(prename, startsWith("B"));
  }

  @Test()
  public void testBenContainsInOrder() {
    String prename = ConstPrename.C_PRENAME_BEN;
    assertThat(prename, stringContainsInOrder(Arrays.asList("B", "e", "n")));
  }

}

