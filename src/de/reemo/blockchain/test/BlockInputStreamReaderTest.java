package de.reemo.blockchain.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.junit.Test;

import de.reemo.blockchain.BlockInputStreamReader;

public class BlockInputStreamReaderTest {


	@Test
	public void itShouldAcceptAnInputStream() {
		new BlockInputStreamReader(new ByteArrayInputStream(new byte[]{0}));
	}
	
	@Test
	public void itShouldReadALittleEndian32BitNumber() throws IOException {
		BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(new byte[]{1, 0, 0, 0}));
		assertEquals(1, reader.readUInt32LE());
	}
	
	@Test
	public void itShouldFlipTheBits() throws IOException {
		BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(new byte[]{1, 0, 0, 1}));
		assertEquals(16777217, reader.readUInt32LE());
	}
	
	@Test(expected = EOFException.class)
	public void itShouldFailAtEOF() throws IOException {
		BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(new byte[]{}));
		reader.readUInt32LE();
	}
}
