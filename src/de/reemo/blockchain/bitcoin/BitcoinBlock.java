package de.reemo.blockchain.bitcoin;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

import de.reemo.blockchain.Block;
import de.reemo.blockchain.BlockInputStreamReader;
import de.reemo.blockchain.common.Transaction;

public class BitcoinBlock extends Block {

	public long nounce;

	public Transaction[] txs;

	public BigInteger merkle_root;

	@Override
	public int compareTo(Block o) {
		return 0;
	}

	public void parse(BlockInputStreamReader reader, boolean headerOnly)
			throws IOException {
		version = reader.readUInt32LE();
		previous_hash = reader.readUInt256LE();
		merkle_root = reader.readUInt256LE();
		timestamp = new Date(reader.readUInt32LE() * 1000);
		bits = reader.readUInt32LE();
		nounce = reader.readUInt32LE();
		if (headerOnly)
			return;
		Number readUIntVarLE = reader.readUIntVarLE();
		int numTx = readUIntVarLE.intValue();
		txs = new Transaction[numTx];
		for(int i = 0; i < numTx; i++) {
			txs[i] = new Transaction();
			txs[i].parse(reader);
		}
	}

	public void parse(BlockInputStreamReader reader) throws IOException {
		parse(reader, false);
	}
}
