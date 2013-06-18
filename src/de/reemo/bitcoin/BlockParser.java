package de.reemo.bitcoin;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;




public class BlockParser {
	private static final MessageDigest SHA256;
	
	static {
		try {
			SHA256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static Block parseBlock(BlockInputStreamReader ois, long blockLength) throws IOException {
		Block block = new Block();
		byte[] header = new byte[80];
		ois.read(header);
		
		@SuppressWarnings("resource")
		BlockInputStreamReader is = new BlockInputStreamReader(new ByteArrayInputStream(header));
		
		block.version = is.readUInt32LE();
		block.previous_hash = is.readUInt256LE();
		block.merkle_root = is.readUInt256LE();
		block.timestamp = new Date(is.readUInt32LE()*1000);
		block.bits = is.readUInt32LE();
		block.nounce = is.readUInt32LE();
		block.hash = getHeaderHash(header);
		
		block.num_tx = ois.readUVarIntLE();
		int num_tx = block.num_tx.intValue();
		block.transactions = new Transaction[num_tx];

		for(int i = 0; i < num_tx; i++) {
			block.transactions[i] = parseTx(ois);
		}
		
		//ois.skip(blockLength-80);
		return block;
	}
	
	private static Transaction parseTx(BlockInputStreamReader ois) throws IOException {
		Transaction tx = new Transaction();
		tx.version = ois.readUInt32LE();
		tx.num_inputs = ois.readUVarIntLE();
		
		int num_inputs = tx.num_inputs.intValue();
		tx.inputs = new TransactionInput[num_inputs];
		for(int i = 0; i < num_inputs; i++) {
			tx.inputs[i] = new TransactionInput();
			tx.inputs[i].previous_output_hash = ois.readUInt256LE();
			tx.inputs[i].previous_output_n = ois.readUInt32LE();
			Number length = ois.readUVarIntLE();
			byte[] buffer = new byte[length.intValue()];
			ois.read(buffer);
			tx.inputs[i].scriptSig = buffer;
			try {
				tx.inputs[i].scriptSigString = Script.scriptToString(buffer);
			} catch(ArrayIndexOutOfBoundsException e) {
				tx.inputs[i].scriptSigString = "ILLEGAL_SCRIPT";
			}
			tx.inputs[i].sequence = ois.readUInt32LE();
		}
		tx.num_outputs = ois.readUVarIntLE();
		int num_outputs = tx.num_outputs.intValue();
		tx.outputs = new TransactionOutput[num_outputs];
		for(int i = 0; i < num_outputs; i++) {
			tx.outputs[i] = new TransactionOutput();
			tx.outputs[i].value = ois.readUInt64LE();
			int scriptLength = ois.readUVarIntLE().intValue();
			byte[] buffer = new byte[scriptLength];
			ois.read(buffer);
			tx.outputs[i].script = buffer;
			tx.outputs[i].scriptString = Script.scriptToString(buffer);
		}
		tx.lock_time = ois.readUInt32LE();
		return tx;
	}

	public static BigInteger getHeaderHash(byte[] header) {
		byte[] digest = SHA256.digest(SHA256.digest(header));
		BlockInputStreamReader.BEtoLE(digest);
		return new BigInteger(1, digest);
	}
}
