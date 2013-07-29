package de.reemo.blockchain.namecoin;

import java.io.File;

import de.reemo.blockchain.BlockchainParser;
import de.reemo.blockchain.repository.BlockRepository;

public class NamecoinBlockchainParser extends BlockchainParser<NamecoinBlock> {

	public NamecoinBlockchainParser(File blockDirectory,
			BlockRepository<NamecoinBlock> blockRepository) {
		super(blockDirectory, blockRepository, 0xFEB4BEF9L, new NamecoinBlockParser());
		// TODO Auto-generated constructor stub
	}

}
