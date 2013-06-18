package de.reemo.blockchain.namecoin;

import java.io.EOFException;
import java.io.IOException;

import de.reemo.blockchain.BlockInputStreamReader;

public class TransactionIn {
	public OutPoint prevOut;
	public byte[] script;
	public long sequence;

	public void parse(BlockInputStreamReader reader) throws IOException {
		prevOut = new OutPoint();
		prevOut.parse(reader);
		int scriptLength = reader.readUIntVarLE().intValue();
		script = new byte[scriptLength];
		reader.read(script);
		try {
			sequence = reader.readUInt32LE();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
