package org.zhangyc.test.rocksdb.KeySort;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;

public class ReadColumnFamily {
    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true)
                .setCreateMissingColumnFamilies(true);



        RocksDB client = RocksDB.open(options, "data/sortkey");

        read(client);

        client.close();
        options.dispose();
    }

    public static void read(RocksDB client){
        RocksIterator iterator = client.newIterator();

        StringBuffer stringBuffer = new StringBuffer();
//        boolean flag = client.keyMayExist("0000000000000015".getBytes(), stringBuffer);
//        System.out.println("flag="+flag+"; stringBuffer="+stringBuffer);

        for (iterator.seek(("01048*").getBytes()); iterator.isValid(); iterator.next()) {
            String key = new String(iterator.key());
            String value = new String(iterator.value());
            System.out.println(key + " --> " + value);
        }
    }
}
