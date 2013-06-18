package de.reemo.blockchain;

import java.math.BigInteger;
import java.util.Date;

public abstract class Block implements Comparable<Block> {
	public long version;
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
}
