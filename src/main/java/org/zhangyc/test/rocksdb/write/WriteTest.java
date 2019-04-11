package org.zhangyc.test.rocksdb.write;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteTest {

    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true)
                .setCreateMissingColumnFamilies(true);

        RocksDB client = RocksDB.open(options, "data/write-1");

        ExecutorService service = Executors.newFixedThreadPool(3);
        Runnable r1 = new WriteTask(client, 1);
        Runnable r2 = new WriteTask(client, 2);
        Runnable r3 = new ReadTask(client, 1);
        service.submit(r1);
        service.submit(r2);
        service.submit(r3);
    }


}
