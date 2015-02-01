package jBittorrentAPI.bencode.btypes;

import jBittorrentAPI.bencode.BCoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project Bencoding
 * Created by Francis on 27/03/14.
 */

/**
 * Represents a Bencode list
 */
public class ArrayBList extends ArrayList<BElement> implements BList {

    public ArrayBList() {
        super();
    }

    @Override
    public Integer toIntValue() {
        return null;
    }

    @Override
    public Long toLongValue() {
        return null;
    }

    @Override
    public String toStringValue() {
        return null;
    }

    @Override
    public Byte [] toByteValue() {
        return null;
    }

    @Override
    public BMap toMap() {
        return null;
    }

    @Override
    public BList toList() {
        return this;
    }

    @Override
    public Integer getIntValue(int key) {
        return this.get(key).toIntValue();
    }

    @Override
    public String getStringValue(int key) {
        return this.get(key).toStringValue();
    }

    @Override
    public List<String> getAsStringsList() {
        List<String> result = new ArrayList<String>();
        for (BElement item : this) {
            result.add(item.toStringValue());
        }
        return result;
    }

    @Override
    public String encode() {
        final StringBuilder builder = new StringBuilder();
        builder.append('l');
        for (final BElement element : this) {
            builder.append(element.encode());
        }
        return builder.append('e').toString();
    }

    /**
     * @param encoded the string we are decoding
     * @param index the index to decode from
     */
    public static ArrayBList read(final String encoded, final AtomicInteger index) {
        if (encoded.charAt(index.get()) == 'l') index.set(index.get() + 1);
        ArrayBList bList = new ArrayBList();
        while (encoded.charAt(index.get()) != 'e') {
            bList.add(BCoder.read(encoded, index));
        }
        index.set(index.get() + 1);
        return bList;
    }
}
