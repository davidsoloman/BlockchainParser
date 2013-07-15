package de.reemo.blockchain.bitcoin;

import java.io.ByteArrayInputStream;

import org.apache.commons.codec.binary.Hex;

import de.reemo.blockchain.Block;
import de.reemo.blockchain.BlockInputStreamReader;
import de.reemo.blockchain.BlockParser;
import de.reemo.blockchain.namecoin.NamecoinBlock;

public class BitcoinBlockParser extends BlockParser {

	@Override
	public Block parseBlock(byte[] blockByteArray) {
		System.out.println(Hex.encodeHex(blockByteArray));
		BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(blockByteArray));
		BitcoinBlock block = new BitcoinBlock();
		block.hash = getHeaderHash(blockByteArray, 0, 80);
		try {
			block.parse(reader, false);
			System.out.println(Hex.encodeHex(blockByteArray));
			System.out.println(block.hash.toString(16));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return block;
	}

}
