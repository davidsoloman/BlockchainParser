package de.reemo.blockchain;

import java.io.*;
import java.util.Arrays;

public abstract class BlockchainParser<T extends Block> {

    private File blockDirectory;
    private BlockRepository<T> blockRepository;
    private long magicByte;
    private BlockParser<T> blockParser;

    public BlockchainParser(File blockDirectory,
                            BlockRepository<T> blockRepository, long magicByte,
                            BlockParser<T> blockParser) {
        this.blockDirectory = blockDirectory;
        this.blockRepository = blockRepository;
        this.magicByte = magicByte;
        this.blockParser = blockParser;
    }

    public void parse() throws IOException {
        File[] blocks = blockDirectory.listFiles(new FileFilter() {

            @Override
            public boolean accept(File arg0) {
                return arg0.getName().startsWith("blk")
                        && arg0.getName().endsWith(".dat");
            }
        });
        Arrays.sort(blocks);
        for (File block : blocks) {
            parseBlockfile(new BlockInputStreamReader(
                    new FileInputStream(block)));
        }

    }

    public void parseBlockfile(BlockInputStreamReader is) throws IOException {
        while (true) {
            try {
                try {
                    // Read Magic bytes
                    if (is.readUInt32LE() != magicByte) {
                        continue;
                    }
                } catch (IOException e) {
                    break;
                }
                long blockLength = is.readUInt32LE();
                byte[] block = new byte[(int) blockLength];
                //noinspection ResultOfMethodCallIgnored
                is.read(block, 0, (int) blockLength);
                blockRepository.add(blockParser.parseBlock(block));
            } catch (EOFException e) {
                break;
            }
        }

    }

}
