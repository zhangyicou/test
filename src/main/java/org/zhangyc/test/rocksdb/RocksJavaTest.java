package org.zhangyc.test.rocksdb;

import org.rocksdb.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RocksJavaTest {
    private static final String dbPath = "data";
    static {
        RocksDB.loadLibrary();
    }

    RocksDB rocksDB;

    public RocksJavaTest() throws RocksDBException {

    }

    //  RocksDB.DEFAULT_COLUMN_FAMILY
    public void testDefaultColumnFamily() throws RocksDBException {

        final BlockBasedTableConfig tableConfig = new BlockBasedTableConfig();
        tableConfig.setBlockCacheSize(1024);
        tableConfig.setFilter(new BloomFilter());
        final Options options = new Options().setCreateIfMissing(true).setTableFormatConfig(tableConfig);

        rocksDB = RocksDB.open(options, dbPath);
        byte[] key = "Hello".getBytes();
        byte[] value = "World".getBytes();
        rocksDB.put(key, value);

        List<byte[]> cfs = RocksDB.listColumnFamilies(options, dbPath);
        for(byte[] cf : cfs) {
            System.out.println(new String(cf));
        }

        byte[] getValue = rocksDB.get(key);
        System.out.println(new String(getValue));

        rocksDB.put("SecondKey".getBytes(), "SecondValue".getBytes());
        for(int i = 0 ; i < 100000; i++){
            rocksDB.put(("key-"+i).getBytes(), ("value-"+i).getBytes());
        }

        List<byte[]> keys = new ArrayList<>();
        keys.add(key);
        keys.add("key-50000".getBytes());

        Map<byte[], byte[]> valueMap = rocksDB.multiGet(keys);
        for(Map.Entry<byte[], byte[]> entry : valueMap.entrySet()) {
            System.out.println(new String(entry.getKey()) + ":" + new String(entry.getValue()));
        }

        System.out.println("iterator............................");
        System.out.println("iterator............................");
        System.out.println("iterator............................");
        System.out.println("iterator............................");
        RocksIterator iter = rocksDB.newIterator();
        for(iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println("iter key:" + new String(iter.key()) + ", iter value:" + new String(iter.value()));
        }

//        rocksDB.remove(key);
//        System.out.println("after remove key:" + new String(key));


        ReadOptions readOptions = new ReadOptions();

        iter = rocksDB.newIterator();
        for(iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println("iter key:" + new String(iter.key()) + ", iter value:" + new String(iter.value()));
        }

    }

    public void testCertainColumnFamily() throws RocksDBException {
        String table = "CertainColumnFamilyTest";
        String key = "certainKey";
        String value = "certainValue";

        List<ColumnFamilyDescriptor> columnFamilyDescriptors = new ArrayList<>();
        Options options = new Options();
        options.setCreateIfMissing(true);

        List<byte[]> cfs = RocksDB.listColumnFamilies(options, dbPath);
        if(cfs.size() > 0) {
            for(byte[] cf : cfs) {
                columnFamilyDescriptors.add(new ColumnFamilyDescriptor(cf, new ColumnFamilyOptions()));
            }
        } else {
            columnFamilyDescriptors.add(new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY, new ColumnFamilyOptions()));
        }

        List<ColumnFamilyHandle> columnFamilyHandles = new ArrayList<>();
        DBOptions dbOptions = new DBOptions();
        dbOptions.setCreateIfMissing(true);

        rocksDB = RocksDB.open(dbOptions, dbPath, columnFamilyDescriptors, columnFamilyHandles);
        for(int i = 0; i < columnFamilyDescriptors.size(); i++) {
            if(new String(columnFamilyDescriptors.get(i).columnFamilyName()).equals(table)) {
                rocksDB.dropColumnFamily(columnFamilyHandles.get(i));
            }
        }

        ColumnFamilyHandle columnFamilyHandle = rocksDB.createColumnFamily(new ColumnFamilyDescriptor(table.getBytes(), new ColumnFamilyOptions()));
        rocksDB.put(columnFamilyHandle, key.getBytes(), value.getBytes());

        byte[] getValue = rocksDB.get(columnFamilyHandle, key.getBytes());
        System.out.println("get Value : " + new String(getValue));

        rocksDB.put(columnFamilyHandle, "SecondKey".getBytes(), "SecondValue".getBytes());

        List<byte[]> keys = new ArrayList<byte[]>();
        keys.add(key.getBytes());
        keys.add("SecondKey".getBytes());

        List<ColumnFamilyHandle> handleList = new ArrayList<>();
        handleList.add(columnFamilyHandle);
        handleList.add(columnFamilyHandle);

        Map<byte[], byte[]> multiGet = rocksDB.multiGet(handleList, keys);
        for(Map.Entry<byte[], byte[]> entry : multiGet.entrySet()) {
            System.out.println(new String(entry.getKey()) + "--" + new String(entry.getValue()));
        }

        rocksDB.remove(columnFamilyHandle, key.getBytes());

        RocksIterator iter = rocksDB.newIterator(columnFamilyHandle);
        for(iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println(new String(iter.key()) + ":" + new String(iter.value()));
        }
    }

    public static void main(String[] args) throws RocksDBException {
        RocksJavaTest test = new RocksJavaTest();
        test.testDefaultColumnFamily();
        //test.testCertainColumnFamily();
    }
}
