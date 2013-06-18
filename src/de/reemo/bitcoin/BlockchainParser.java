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
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Hex;

public class BlockchainParser {

	Map<BigInteger, Long> height = new TreeMap<>();
	Map<BigInteger, Date> time = new TreeMap<>();
	BigInteger toFind = new BigInteger(
			"00000000000002c727047296ee20b628599031c6ea5c09292513fddbb11d34df",
			16);

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		BlockchainParser blockchainParser = new BlockchainParser(new File(
				"/home/raimo/.bitcoin/blocks/"));
		blockchainParser.scan();
	}

	static long MAGIC_VALUE = 0xD9B4BEF9L;
	static long MAGIC_VALUE_NAMECOIN = 0xFEB4BEF9L;

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
			// System.out.println("Scanning Blockfile " + block);
			scanBlockfile(new BlockInputStreamReader(
					new FileInputStream(block)));
		}

	}

	protected void scanBlockfile(BlockInputStreamReader is)
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
				time.put(b.hash, b.timestamp);
//				System.out.println(timeDiff(b.timestamp,
//						time.get(b.previous_hash))/1000L/60L);
//				if(b.num_tx.longValue() > 1000L) {
//					System.out.println("Da sind wir!");
//				}
				//System.out.println(b.height+";"+b.getTarget().toString(16));
				System.out.println(new String(b.transactions[0].inputs[0].getScriptSigAsReadableString()));
				//System.out.println(b.timestamp.getTime()+";"+b.num_tx);
				// System.out.println(Block.formatHash(b.hash));
			} catch (IOException e) {
				break;
			}
		}
	}

	private long timeDiff(Date timestamp, Date date) {
		if (date == null || timestamp == null)
			return 0;
		return timestamp.getTime() - date.getTime();
	}

	protected long getHeight(Block b) {
		long currentHeight = -1L;
		if (!height.containsKey(b.previous_hash)) {
			currentHeight = 1L;
		} else {
			currentHeight = height.get(b.previous_hash) + 1L;
		}
		height.put(b.hash, currentHeight);
		return currentHeight;
	}
}
