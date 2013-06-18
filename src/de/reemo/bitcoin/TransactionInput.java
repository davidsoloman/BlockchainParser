package de.reemo.bitcoin;

import java.math.BigInteger;

public class TransactionInput {

	long sequence;
	byte[] scriptSig;
	String scriptSigString;
	BigInteger previous_output_hash;
	long previous_output_n;

	public String getScriptSigAsReadableString() {
		StringBuilder sb = new StringBuilder();
		for(byte b : scriptSig) {
			if((b > 'a' && b < 'z') || (b > 'A' && b < 'Z')) {
				sb.appendCodePoint(b);
			} else {
				sb.append(' ');
			}
		}
		return sb.toString();
	}
}