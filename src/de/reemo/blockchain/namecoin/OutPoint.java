package de.reemo.blockchain.namecoin;

import java.io.IOException;
import java.math.BigInteger;

import de.reemo.blockchain.BlockInputStreamReader;

public class OutPoint {

	public long n;
	public BigInteger hash;

	public void parse(BlockInputStreamReader reader) throws IOException {
		hash = reader.readUInt256LE();
		n = reader.readUInt32LE();
	}

	
}
