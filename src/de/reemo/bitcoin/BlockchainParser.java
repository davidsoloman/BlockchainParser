package de.reemo.bitcoin;
import java.io.EOFException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Hex;

public class BlockchainParser {

	Map<BigInteger, Long> height = new TreeMap<>();
	BigInteger toFind = new BigInteger("00000000000002c727047296ee20b628599031c6ea5c09292513fddbb11d34df", 16);
	
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		BlockchainParser blockchainParser = new BlockchainParser(new File(
				"/home/raimo/.bitcoin/blocks"));
		blockchainParser.scan();
	}

	static long MAGIC_VALUE = 0xD9B4BEF9L;

	File blockChainDirectory;

	public BlockchainParser(File blockChainDirectory) {
		this.blockChainDirectory = blockChainDirectory;
	}

	public void scan() throws IOException {
		File[] blocks = blockChainDirectory.listFiles(new FileFilter() {

			@Override
			public boolean accept(File arg0) {
				return arg0.getName().startsWith("blk")
						&& arg0.getName().endsWith(".dat");
			}
		});
		Arrays.sort(blocks);
		for (File block : blocks) {
			//System.out.println("Scanning Blockfile " + block);
			scanBlockfile(new ByteParsingPipeInputStreamReader(
					new FileInputStream(block)));
		}

	}

	protected void scanBlockfile(ByteParsingPipeInputStreamReader is)
			throws IOException {
		while (true) {
			try {
				// Read Magic bytes
				if (is.readUInt32LE() != MAGIC_VALUE) {
					continue;
				}
				long blockLength = is.readUInt32LE();
				Block b = BlockParser.parseBlock(is, blockLength);
				b.height = getHeight(b);
				if(b.transactions[0].inputs[0].previous_output_n == 0xFFFFFFFFL)
					System.out.println(b.height+";"+Hex.encodeHexString(b.transactions[0].inputs[0].scriptSig)+";"+b.transactions[0].inputs[0].scriptSigString.replace(' ', ';'));
				//System.out.println(Block.formatHash(b.hash));
			} catch (IOException e) {
				break;
			}
		}
	}
	
	protected long getHeight(Block b) {
		long currentHeight = -1L;
		if(!height.containsKey(b.previous_hash)) {
			currentHeight = 1L;
		} else {
			currentHeight = height.get(b.previous_hash)+1L;
		}
		height.put(b.hash, currentHeight);
		return currentHeight;
	}
}
