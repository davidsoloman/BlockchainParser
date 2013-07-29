package de.reemo.blockchain.bitcoin;

import java.io.File;
import java.io.IOException;

public class BitcoinMain {
    public static void main(String[] args) throws IOException {
        BitcoinBlockchainParser bitcoinBlockchainParser = new BitcoinBlockchainParser(
                new File("/media/data/bitcoin/blocks"), new InMemoryBlockRepository());
        bitcoinBlockchainParser.parse();
    }
}
