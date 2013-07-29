package de.reemo.blockchain.repository;

import de.reemo.blockchain.Block;

public interface BlockRepository<T extends Block> {

	void add(T parseBlock);
	
}
