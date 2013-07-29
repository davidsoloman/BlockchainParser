package de.reemo.blockchain.bitcoin;

import de.reemo.blockchain.Block;
import de.reemo.blockchain.BlockRepository;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

public class InMemoryBlockRepository implements BlockRepository<BitcoinBlock> {
    Map<BigInteger, Long> height = new TreeMap<>();

    @Override
    public void add(BitcoinBlock newBlock) {
        newBlock.height = getHeight(newBlock);
    }

    protected long getHeight(Block b) {
        long res = 0;
        if (height.containsKey(b.previous_hash)) {
            res = height.get(b.previous_hash) + 1L;
        }
        height.put(b.hash, res);
        return res;
    }
}
