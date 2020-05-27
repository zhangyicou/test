package org.zhangyc.test.map;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yichu.zhang
 * @Date: 2020-05-19 22:54
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<Integer, Map<Integer, Integer>> counterRouterMap  = new HashMap<>(32);
        for(int i = 0; i < 2; i++){

        }
    }

    private void initMap(Integer counterId,
                         List<Integer> symbolList,
                         List<Integer> moduloList,
                         Map<Integer, Map<Integer, Integer>> CounterMap) {

        symbolList.forEach(symbol->
                moduloList.forEach(modulo-> {
                    Map<Integer, Integer> map = CounterMap.get(symbol);
                    if(MapUtils.isEmpty(map)){
                        map = new HashMap<>(128);
                        CounterMap.put(symbol, map);
                    }

                    map.put(modulo, counterId);
                })
        );
    }
}
