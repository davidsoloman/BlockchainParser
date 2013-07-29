package de.reemo.blockchain.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.junit.Test;

import de.reemo.blockchain.BlockInputStreamReader;

public class BlockInputStreamReaderTest {


	@SuppressWarnings("resource")
	@Test
	public void itShouldAcceptAnInputStream() {
		new BlockInputStreamReader(new ByteArrayInputStream(new byte[]{0}));
	}
	
	@SuppressWarnings("resource")
	@Test
	public void itShouldReadALittleEndian32BitNumber() throws IOException {
		BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(new byte[]{1, 0, 0, 0}));
		assertEquals(1, reader.readUInt32LE());
	}
	
	@SuppressWarnings("resource")
	@Test
	public void itShouldFlipTheBits() throws IOException {
		BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(new byte[]{1, 0, 0, 1}));
		assertEquals(16777217, reader.readUInt32LE());
	}
	
	@SuppressWarnings("resource")
	@Test(expected = EOFException.class)
	public void itShouldFailAtEOF() throws IOException {
		BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(new byte[]{}));
		reader.readUInt32LE();
	}
}
