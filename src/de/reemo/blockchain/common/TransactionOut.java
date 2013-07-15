package de.reemo.blockchain.common;

import java.io.IOException;
import java.math.BigInteger;

import de.reemo.blockchain.BlockInputStreamReader;
import de.reemo.blockchain.namecoin.Script;

public class TransactionOut {

	public BigInteger value;
	public byte[] script;
	public String scriptString;

	public void parse(BlockInputStreamReader reader) throws IOException {
		value = reader.readUInt64LE();
		long scriptLength = reader.readUIntVarLE().longValue();
		if(scriptLength > Integer.MAX_VALUE) {
			script = new byte[0];
			return;
		}
		script = new byte[(int) scriptLength];
		reader.read(script);
		scriptString = Script.scriptToString(script);
	}

}
