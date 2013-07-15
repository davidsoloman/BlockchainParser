package de.reemo.blockchain.repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import de.reemo.blockchain.Block;
import de.reemo.blockchain.bitcoin.BitcoinBlock;
import de.reemo.blockchain.common.Transaction;
import de.reemo.blockchain.common.TransactionOut;
import de.reemo.blockchain.namecoin.NamecoinBlock;
import de.reemo.blockchain.namecoin.Script;

public class InMemoryBlockRepository implements BlockRepository {
	Map<BigInteger, Long> height = new TreeMap<>();

	@Override
	public void add(Block newBlock) {
//		long height = getHeight(newBlock);
//		newBlock.height = height;
//		NamecoinBlock nBlock = (NamecoinBlock) newBlock;
//		System.out.println(nBlock.height);
//		if(newBlock.version == NamecoinBlock.AUX_POW_VERSION) {
//			System.out.println("AUX");
//		} else {
//			System.out.println("NON_AUX");
//		}
//		System.out.println(nBlock.getTarget().toString(16));
//		for (Transaction tx : nBlock.txs) {
//			if (tx == null || tx.txOut == null)
//				break;
//			for (TransactionOut txout : tx.txOut) {
//				if (txout.script == null || txout.script.length == 0)
//					break;
//				if (txout.script[0] == Script.OP_NAME_FIRSTUPDATE) {
//					/*System.out.println(nBlock.height + ";"
//							+ getFee(nBlock.height, tx) + ";"
//							+ nBlock.timestamp.getTime() + ";"
//							+ getDomain(txout));*/
//					break;
//				}
//			}
//		}
	}

	private String getDomain(TransactionOut txout) {
		String substring = Script.scriptToString(txout.script).substring(20);
		if (!substring.startsWith("d/"))
			return "$$INVALID";
		return substring.substring(2, substring.indexOf(" "));
	}

	public String getFee(long nHeight, Transaction tx) {
		// Speed up network fee decrease 4x starting at 24000
		if (nHeight >= 24000L)
			nHeight += (nHeight - 24000L) * 3L;
		long nRes;
		if (!((nHeight >> 13) >= 60)) {
			long nStart = 5000000000L;
			nRes = nStart >> (nHeight >> 13);
			nRes -= (nRes >> 14) * (nHeight % 8192);
		} else {
			nRes = 0;
		}
		String result = nRes + ";";
		// Find Spend TxOut
		for (TransactionOut txOut : tx.txOut) {
			if (txOut.script.length == 1) {
				result += txOut.value;
				return result;
			}
		}
		return result+"0";

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
