package de.reemo.blockchain.bitcoin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.reemo.blockchain.repository.InMemoryBlockRepository;

public class BitcoinMain {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		BitcoinBlockchainParser bitcoinBlockchainParser = new BitcoinBlockchainParser(
				new File("/media/data/bitcoin/blocks"), new InMemoryBlockRepository());
		bitcoinBlockchainParser.parse();
	}
}
