package de.reemo.blockchain;

import de.reemo.blockchain.namecoin.NamecoinBlockchainParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NamecoinMain {
    public static void main(String[] args) throws FileNotFoundException,
            IOException {
        NamecoinBlockchainParser namecoinBlockchainParser = new NamecoinBlockchainParser(
                new File("/media/data/nmc"), null);
        namecoinBlockchainParser.parse();
    }
}
