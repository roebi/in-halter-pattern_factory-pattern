package ch.const1.lang.ja8;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

/**
 * Unit Test for ConstLastname
 * @since 1.0
 */
public class ConstLastnameTest {

  @Test()
  public void testCallPrivateConstrutor() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    final Class<ConstLastname> clazz = ConstLastname.class;
    final Constructor<?> c = clazz.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[])null);
  }

}

