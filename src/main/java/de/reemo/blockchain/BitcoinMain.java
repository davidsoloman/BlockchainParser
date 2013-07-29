package de.reemo.blockchain;

import de.reemo.blockchain.bitcoin.BitcoinBlockchainParser;
import de.reemo.blockchain.bitcoin.InMemoryBlockRepository;

import java.io.File;
import java.io.IOException;

public class BitcoinMain {
    public static void main(String[] args) throws IOException {
        BitcoinBlockchainParser bitcoinBlockchainParser = new BitcoinBlockchainParser(
                new File("/media/data/bitcoin/blocks"), new InMemoryBlockRepository());
        bitcoinBlockchainParser.parse();
    }
}
