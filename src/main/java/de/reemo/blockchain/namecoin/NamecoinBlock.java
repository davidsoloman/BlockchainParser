package de.reemo.blockchain.namecoin;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

import de.reemo.blockchain.Block;
import de.reemo.blockchain.BlockInputStreamReader;
import de.reemo.blockchain.common.Transaction;

public class NamecoinBlock extends Block {

	public static final long AUX_POW_VERSION = 65793L;

	public BigInteger merkle_root;
	public long nounce;
	public AuxPow auxPow;

	public Transaction[] txs;

	public String header;

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
		if (version == AUX_POW_VERSION) {
			auxPow = new AuxPow();
			auxPow.parse(reader);
		}
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
