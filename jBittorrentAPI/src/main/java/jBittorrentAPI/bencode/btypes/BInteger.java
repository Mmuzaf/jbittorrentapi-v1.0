package jBittorrentAPI.bencode.btypes;

import jBittorrentAPI.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project Bencoding
 * Created by Francis on 27/03/14.
 */

/**
 * Represents a Bencode number
 */
public class BInteger implements BElement {

    private final Long value;

    public BInteger(final long value) {
        this.value = value;
    }

    public String encode() {
        return "i" + value + "e";
    }

    public Integer toIntValue() {
        return value.intValue();
    }

    public Long toLongValue() {
        return value;
    }

    public String toStringValue() {
        return String.valueOf(value);
    }

    public Byte [] toByteValue() {
        return ArrayUtils.toObject(Utils.getBytesOf(String.valueOf(value)));
    }

    public BMap toMap() {
        return null;
    }

    public BList toList() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BInteger)) return false;

        BInteger bInteger = (BInteger) o;

        if (value != null ? !value.equals(bInteger.value) : bInteger.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public static BInteger read(final String encoded, final AtomicInteger index) {
        if (encoded.charAt(index.get()) == 'i') index.set(index.get() + 1);
        final int end = encoded.indexOf('e', index.get());
        final int value = Integer.valueOf(encoded.substring(index.get(), end));
        index.set(end + 1);
        BInteger result = new BInteger(value);
        //System.out.println(result.toIntValue());
        return result;
    }

}
