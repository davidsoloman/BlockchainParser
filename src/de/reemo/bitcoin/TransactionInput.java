package de.reemo.bitcoin;

import java.math.BigInteger;

public class TransactionInput {

	long sequence;
	byte[] scriptSig;
	String scriptSigString;
	BigInteger previous_output_hash;
	long previous_output_n;

}