package src;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * 
 * @author tomas
 * 
 *         pruebassadkfjasdlkfj
 */

public class EjemploTest {

	private Integer i;

	/**
	 * asdfasdfasdf
	 */
	@Before
	public void setup() {
		i = new Integer(5);
	}

	/**
	 * asdfasdfasefaes
	 */
	@Test
	public void testeoAlgo() {
		assertTrue(5 == i);
		assertEquals(new Integer(5), i);
	}

	/**
	 * alsdfjasdlkfjasdf
	 */
	@Test(expected = NullPointerException.class)
	public void testeoQueLanzaExepcion() {
		throw new NullPointerException();
	}
}
