package de.reemo.blockchain.namecoin;

import de.reemo.blockchain.BlockRepository;
import de.reemo.blockchain.BlockchainParser;

import java.io.File;

public class NamecoinBlockchainParser extends BlockchainParser<NamecoinBlock> {

    public NamecoinBlockchainParser(File blockDirectory,
                                    BlockRepository<NamecoinBlock> blockRepository) {
        super(blockDirectory, blockRepository, 0xFEB4BEF9L, new NamecoinBlockParser());
    }

}
