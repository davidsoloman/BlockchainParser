package de.reemo.blockchain;

public interface BlockRepository<T extends Block> {

    void add(T parseBlock);

}
