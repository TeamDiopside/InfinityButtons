package nl.teamdiopside.infinitybuttons.util;

import com.mojang.datafixers.util.Pair;

import java.util.HashMap;

public class BiHashMap<K1, K2, V> extends HashMap<Pair<K1, K2>, V> {
    public void put(K1 k1, K2 k2, V v) {
        super.put(Pair.of(k1, k2), v);
    }

    public V get(K1 k1, K2 k2) {
        return super.get(Pair.of(k1, k2));
    }
}
