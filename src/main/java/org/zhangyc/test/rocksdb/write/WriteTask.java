package org.zhangyc.test.rocksdb.write;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.zhangyc.test.rocksdb.ByteUtils;

import java.util.concurrent.atomic.AtomicLong;

public class WriteTask extends Thread {
    private final AtomicLong start;
    private RocksDB client;

    public WriteTask(RocksDB client, int start){
        this.client = client;
        this.start = new AtomicLong(start);
    }
    @Override
    public void run() {
        while (true) {
            try {
                byte[] values = ByteUtils.longToBytes(this.start.get());
                client.put(values, values);
                this.start.getAndAdd(2);
            } catch (RocksDBException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
}
