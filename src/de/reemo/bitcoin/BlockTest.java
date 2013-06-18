package de.reemo.bitcoin;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class BlockTest {

	@Test
	public void testCorrectTarget() {
		Block b = new Block();
		b.bits = 0x1b0404cbL;
		assertEquals(new BigInteger("00000000000404CB000000000000000000000000000000000000000000000000", 16), b.getTarget());
	}

	@Test
	public void easiestTarget() {
		Block b = new Block();
		b.bits = 0x1d00ffffL;
		assertEquals(new BigInteger("00000000FFFF0000000000000000000000000000000000000000000000000000", 16), b.getTarget());
	}
}
