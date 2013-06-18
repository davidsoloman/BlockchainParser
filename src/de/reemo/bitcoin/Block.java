package de.reemo.bitcoin;
import java.math.BigInteger;
import java.util.Date;

public class Block implements Comparable<Block>{
	private static final BigInteger MINUS_ONE = new BigInteger("-1");
	private static final BigInteger BASE = BigInteger.valueOf(256L);
	long version;
	BigInteger previous_hash;
	BigInteger merkle_root;
	Date timestamp;
	long bits;
	long nounce;
	BigInteger hash;
	long height;
	Number num_tx;
	Transaction[] transactions;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		return true;
	}
	
	public BigInteger getTarget() {
		BigInteger mantisse = BigInteger.valueOf(bits & 0x007fffffL);
		BigInteger exponent = BASE.pow((int)(bits >> 24L)-3); 
		return MINUS_ONE.pow((int)(bits & 0x00800000L)).multiply(mantisse).multiply(exponent);
	}
	

	public static String formatHash(BigInteger hash2) {
		return String.format("%64s", hash2.toString(16)).replace(' ', '0');
	}

	@Override
	public int compareTo(Block o) {
		return this.hash.compareTo(o.hash);
	}
}
