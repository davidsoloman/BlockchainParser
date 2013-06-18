package de.reemo.blockchain;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Hex;

public class BlockInputStreamReader extends InputStream {
	InputStream is;
	private byte[] buffer64;
	private byte[] buffer256;

	private List<Integer> breaks = new ArrayList<>();

	int numRead = 0;

	public BlockInputStreamReader(InputStream parent) {
		this.is = parent;
	}

	public InputStream getParentStream() {
		return is;
	}

	@Override
	public int read() throws IOException {
		int read = is.read();
		breaks.add(numRead);
		numRead++;
		return read;
	}

	@Override
	public int available() throws IOException {
		return is.available();
	}

	@Override
	public void close() throws IOException {
		is.close();
	}

	@Override
	public synchronized void mark(int readlimit) {
		is.mark(readlimit);
	}

	@Override
	public boolean markSupported() {
		return is.markSupported();
	}

	@Override
	public int read(byte[] b) throws IOException {
		int read = is.read(b);
		breaks.add(numRead);
		numRead += read;
		return read;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int read = is.read(b, off, len);
		breaks.add(numRead);
		numRead += read;
		return read;
	}

	@Override
	public synchronized void reset() throws IOException {
		is.reset();
	}

	@Override
	public long skip(long n) throws IOException {
		long skip = is.skip(n);
		breaks.add((int) skip);
		numRead += skip;
		return skip;
	}

	public BigInteger readUInt256LE() throws IOException {
		if (buffer256 == null) {
			buffer256 = new byte[32];
		}
		readAndAssertLength(32);
		BEtoLE(buffer256);
		return new BigInteger(1, buffer256);
	}

	public BigInteger readUInt64LE() throws IOException {
		assertBuffer64IsInitialized();
		readAndAssertLength(8);
		BEtoLE(buffer64);
		return new BigInteger(buffer64);
	}

	public static void BEtoLE(byte[] array) {
		int i = 0;
		int j = array.length - 1;
		byte tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
	}

	public Number readUIntVarLE() throws IOException {
		long first = readUInt8();
		if (first < 0xfdL) {
			return first;
		} else if (first == 0xfdL) {
			return readUInt16LE();
		} else if (first == 0xfeL) {
			return readUInt32LE();
		} else {
			return readUInt64LE();
		}
	}

	public long readUInt32LE() throws IOException {
		return readUInt(4);
	}

	public String readString(int length) throws IOException {
		byte[] buffer = new byte[length];
		read(buffer);
		return new String(buffer, "utf-8");
	}

	public long readUInt16LE() throws IOException {
		return readUInt(2);
	}

	public long readUInt8() throws IOException {
		return readUInt(1);
	}

	public long readUInt(int length) throws IOException {
		assertBuffer64IsInitialized();
		readAndAssertLength(length);
		return byteArrayToUInt(buffer64, length);
	}

	private void readAndAssertLength(int length) throws IOException {
		int numReadNow;
		if (length > 8) {
			numReadNow = is.read(buffer256, 0, length);
		} else {
			numReadNow = is.read(buffer64, 0, length);
		}
		breaks.add(numRead);
		if (numReadNow != length) {
			if (numReadNow == -1)
				throw new EOFException();
			else
				throw new IOException("Read " + numReadNow + " instead of 4");
		}
		numRead += numReadNow;
	}

	private void assertBuffer64IsInitialized() {
		if (this.buffer64 == null) {
			this.buffer64 = new byte[8];
		}
	}

	private long byteArrayToUInt(byte[] bytes, int length) {
		long value = 0;
		for (int i = 0; i < length; i++) {
			value += ((long) bytes[i] & 0xffL) << (8 * i);
		}
		return value;
	}

	public long getNumRead() {
		return numRead;
	}

	public String toStringWithBreaks(byte[] block) {
		StringBuilder sb = new StringBuilder();
		int prev = 0;
		for (int brk : breaks) {
			if(brk > block.length || prev > block.length || prev > brk) break;
			sb.append(Hex.encodeHex(Arrays.copyOfRange(block, prev, brk)));
			prev = brk;
			sb.append(System.lineSeparator());
		}
		sb.append(Hex.encodeHex(Arrays.copyOfRange(block, prev, block.length+1)));
		return sb.toString();
	}
}
