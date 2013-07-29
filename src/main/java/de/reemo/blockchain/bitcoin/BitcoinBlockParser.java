package de.reemo.blockchain.bitcoin;

import de.reemo.blockchain.BlockInputStreamReader;
import de.reemo.blockchain.BlockParser;

import java.io.ByteArrayInputStream;

public class BitcoinBlockParser extends BlockParser<BitcoinBlock> {

    @Override
    public BitcoinBlock parseBlock(byte[] blockByteArray) {
        BlockInputStreamReader reader = new BlockInputStreamReader(new ByteArrayInputStream(blockByteArray));
        BitcoinBlock block = new BitcoinBlock();
        block.hash = getHeaderHash(blockByteArray, 0, 80);
        try {
            block.parse(reader, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return block;
    }

}
