package de.reemo.blockchain.bitcoin;

import de.reemo.blockchain.BlockRepository;
import de.reemo.blockchain.BlockchainParser;

import java.io.File;

public class BitcoinBlockchainParser extends BlockchainParser<BitcoinBlock> {

    public BitcoinBlockchainParser(File blockDirectory,
                                   BlockRepository<BitcoinBlock> blockRepository) {
        super(blockDirectory, blockRepository, 0xD9B4BEF9L, new BitcoinBlockParser());
    }

}
