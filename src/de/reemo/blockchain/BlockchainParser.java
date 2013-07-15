package de.reemo.blockchain;

import java.io.EOFException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import de.reemo.blockchain.repository.BlockRepository;

public abstract class BlockchainParser {

	private File blockDirectory;
	private BlockRepository blockRepository;
	private long magicByte;
	private BlockParser blockParser;

	public BlockchainParser(File blockDirectory,
			BlockRepository blockRepository, long magicByte,
			BlockParser blockParser) {
		this.blockDirectory = blockDirectory;
		this.blockRepository = blockRepository;
		this.magicByte = magicByte;
		this.blockParser = blockParser;
	}

	public void parse() throws FileNotFoundException, IOException {
		File[] blocks = blockDirectory.listFiles(new FileFilter() {

			@Override
			public boolean accept(File arg0) {
				return arg0.getName().startsWith("blk")
						&& arg0.getName().endsWith(".dat");
			}
		});
		Arrays.sort(blocks);
		for (File block : blocks) {
			System.out.println("Scanning Blockfile " + block);
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
				is.read(block, 0, (int) blockLength);
				blockRepository.add(blockParser.parseBlock(block));
			} catch (EOFException e) {
				break;
			}
		}

	}

}
