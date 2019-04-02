package org.zhangyc.test.rocksdb.KeySort;

import org.rocksdb.*;

public class WriteColumnFamily {

    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true)
                .setCreateMissingColumnFamilies(true);

        RocksDB client = RocksDB.open(options, "data/sortkey");

        write(client);

        client.close();
        options.dispose();
    }

    public static void write(RocksDB client){
        try {
            WriteBatch batch = new WriteBatch(1024 * 32);
            for (int index = 1; index <= 1024 * 1024; index++) {
                String key = String.format("%08d", index);
                batch.put(key.getBytes(), key.getBytes());
            }
            client.write(new WriteOptions(), batch);
        }catch (RocksDBException e){
            e.printStackTrace();
        }
    }
}
