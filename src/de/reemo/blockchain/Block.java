package de.reemo.blockchain;

import java.math.BigInteger;
import java.util.Date;

public abstract class Block implements Comparable<Block> {
	private static final BigInteger MINUS_ONE = new BigInteger("-1");
	private static final BigInteger BASE = BigInteger.valueOf(256L);

	public long version;
	public long bits;
	public BigInteger previous_hash;
	public Date timestamp;
	public BigInteger hash;
	public long height;
	public byte[] raw_block;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Block) {
			return hash.equals(((Block) obj).hash);
		}
		return false;
	}
	
	public BigInteger getTarget() {
		BigInteger mantisse = BigInteger.valueOf(bits & 0x007fffffL);
		BigInteger exponent = BASE.pow((int)(bits >> 24L)-3); 
		return MINUS_ONE.pow((int)(bits & 0x00800000L)).multiply(mantisse).multiply(exponent);
	}
	
}
