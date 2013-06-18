package de.reemo.blockchain.bitcoin;

import java.io.File;

import de.reemo.blockchain.BlockchainParser;
import de.reemo.blockchain.repository.BlockRepository;

public class BitcoinBlockchainParser extends BlockchainParser {

	public BitcoinBlockchainParser(File blockDirectory,
			BlockRepository blockRepository) {
		super(blockDirectory, blockRepository, 0xD9B4BEF9L, new BitcoinBlockParser());
	}

}
