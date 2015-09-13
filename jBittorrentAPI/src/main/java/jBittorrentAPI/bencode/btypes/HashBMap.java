package jBittorrentAPI.bencode.btypes;

import jBittorrentAPI.bencode.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a bencode Map & Dictionary
 */
public class HashBMap extends HashMap<BString, BElement> implements BMap {

    public Integer toIntValue() {
        return null;
    }

    public Long toLongValue() {
        return null;
    }

    public String toStringValue() {
        return null;
    }

    public Byte [] toByteValue() {
        return null;
    }

    public BMap toMap() {
        return this;
    }

    public BList toList() {
        return null;
    }

    public Integer getIntValue(BString key) {
        return this.containsKey(key) ? this.get(key).toIntValue() : null;
    }

    public Long getLongValue(BString key) {
        return this.containsKey(key) ? this.get(key).toLongValue() : null;
    }

    public String getStringValue(BString key) {
        return this.containsKey(key) ? this.get(key).toStringValue() : null;
    }

    public Byte[] getByteValue(BString key) {
        return this.containsKey(key) ? this.get(key).toByteValue() : null;
    }

    public BList getListValue(BString key) {
        return this.containsKey(key) ? this.get(key).toList() : null;
    }

    public BMap getMapValue(BString key) {
        return this.containsKey(key) ? this.get(key).toMap() : null;
    }

    public String encode() {
        final StringBuilder builder = new StringBuilder();
        builder.append('d');
        List<BString> sorted = new ArrayList<BString>(this.keySet());
        Collections.sort(sorted);
        int i = 0;
        for (final BString key : sorted) {

            System.out.println(i + " " + key.toStringValue().length() + ":" + key.toStringValue());
            System.out.println(this.get(key).encode().length() + ":" + this.get(key).encode());
            builder.append(key.encode()).append(this.get(key).encode());
            i++;
        }
        return builder.append('e').toString();
    }

    /**
     * Read elemets from input encoded string getParamName
     * @param encoded the string we are decoding
     * @param index the index to decode from
     */
    public static HashBMap read(final String encoded, final AtomicInteger index) {
        if (encoded.charAt(index.get()) == 'd') index.set(index.get() + 1);
        HashBMap hashMap = new HashBMap();
        while (encoded.charAt(index.get()) != 'e') {
            final BString key = BString.read(encoded, index);
            final BElement value = BCoder.read(encoded, index);
            hashMap.put(key, value);
        }
        index.set(index.get() + 1);
        return hashMap;
    }

}
