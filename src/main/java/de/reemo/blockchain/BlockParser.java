package de.reemo.blockchain;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class BlockParser<T extends Block> {
	private final MessageDigest SHA256;

	{
		try {
			SHA256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public abstract T parseBlock(byte[] blockByteArray) throws IOException;

	public BigInteger getHeaderHash(byte[] block, int offset, int length) {
		SHA256.reset();
		SHA256.update(block, offset, length);
		byte[] digest = SHA256.digest(SHA256.digest());
		BlockInputStreamReader.BEtoLE(digest);
		return new BigInteger(1, digest);
	}
}
