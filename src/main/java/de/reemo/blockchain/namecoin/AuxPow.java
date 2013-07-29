package de.reemo.blockchain.namecoin;

import java.io.IOException;
import java.math.BigInteger;

import de.reemo.blockchain.BlockInputStreamReader;

public class AuxPow extends MerkleTransaction {

	public BigInteger[] chainMerkleBranches;
	public long chainIndex;
	public NamecoinBlock parentBlock;

	public void parse(BlockInputStreamReader reader) throws IOException {
		super.parse(reader);
		int numChainMerkleBranch = reader.readUIntVarLE().intValue();
		chainMerkleBranches = new BigInteger[numChainMerkleBranch];
		for(int i = 0; i < numChainMerkleBranch; i++) {
			chainMerkleBranches[i] = reader.readUInt256LE();
		}
		chainIndex = reader.readUInt32LE();
		parentBlock = new NamecoinBlock();
		parentBlock.parse(reader, true);
	}

}
