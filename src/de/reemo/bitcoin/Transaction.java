package de.reemo.bitcoin;
public class Transaction {
	long version;
	Number num_inputs;
	TransactionInput[] inputs;
	TransactionOutput[] outputs;
	long lock_time;
	Number num_outputs;
}
