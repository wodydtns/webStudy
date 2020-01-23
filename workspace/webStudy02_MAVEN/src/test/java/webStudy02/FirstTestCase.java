package webStudy02;

import static org.junit.Assert.*;

import org.junit.Test;

public class FirstTestCase {

	@Test
	public void test() {
		int result = 3+4;
		assertEquals(7, result);
	}

}
