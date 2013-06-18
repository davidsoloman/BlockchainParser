package de.reemo.blockchain.namecoin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.reemo.blockchain.repository.InMemoryBlockRepository;

public class NamecoinMain {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		NamecoinBlockchainParser namecoinBlockchainParser = new NamecoinBlockchainParser(
				new File("/media/data/nmc"), new InMemoryBlockRepository());
		namecoinBlockchainParser.parse();
	}
}
