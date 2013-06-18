package de.reemo.blockchain.namecoin;

import java.io.IOException;

import de.reemo.blockchain.BlockInputStreamReader;

public class Transaction {
	public long version;
	public TransactionIn[] txIn;
	public TransactionOut[] txOut;
	public long lockTime;
	
	public void parse(BlockInputStreamReader reader) throws IOException {
		version = reader.readUInt32LE();
		int numTxIn = reader.readUIntVarLE().intValue();
		txIn = new TransactionIn[numTxIn];
		for(int i = 0; i < numTxIn; i++) {
			txIn[i] = new TransactionIn();
			txIn[i].parse(reader);
		}
		int numTxOut = reader.readUIntVarLE().intValue();
		txOut = new TransactionOut[numTxOut];
		for(int i = 0; i < numTxOut; i++) {
			txOut[i] = new TransactionOut();
			txOut[i].parse(reader);
		}
		lockTime = reader.readUInt32LE();
	}
}
