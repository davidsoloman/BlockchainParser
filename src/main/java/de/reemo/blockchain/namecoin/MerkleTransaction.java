package de.reemo.blockchain.namecoin;

import java.io.IOException;
import java.math.BigInteger;

import de.reemo.blockchain.BlockInputStreamReader;
import de.reemo.blockchain.common.Transaction;

public class MerkleTransaction extends Transaction {
	public BigInteger hashBlock;
	public BigInteger[] merkleBranches;
	public long index;
	
	public void parse(BlockInputStreamReader reader) throws IOException {
		super.parse(reader);
		hashBlock = reader.readUInt256LE();
		int numBranches = reader.readUIntVarLE().intValue();
		merkleBranches = new BigInteger[numBranches];
		for(int i = 0; i < numBranches; i++) {
			merkleBranches[i] = reader.readUInt256LE();
		}
		index = reader.readUInt32LE();
	}
}
