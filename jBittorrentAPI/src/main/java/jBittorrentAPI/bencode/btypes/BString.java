package jBittorrentAPI.bencode.btypes;

import jBittorrentAPI.Constants;
import jBittorrentAPI.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project Bencoding
 * Created by Francis on 27/03/14.
 */

/**
 * Represents a Bencode string
 */
public class BString implements BElement, Comparable<BString> {

    private final String value;

    public BString(final String value) {
        this.value = value;
    }
    public String encode() {
        return value.length() + ":" + value;
    }
    public Integer toIntValue() {
        return Integer.parseInt(value);
    }
    public Long toLongValue() {
        return Long.parseLong(value);
    }
    public String toStringValue() {
        return value;
    }
    public Byte [] toByteValue() {
        return ArrayUtils.toObject(Utils.getBytesOf(this.value));
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
        if (!(o instanceof BString)) return false;

        BString bString = (BString) o;

        if (value != null ? !value.equals(bString.value) : bString.value != null) return false;

        return true;
    }
    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    /**
     * @param encoded the string we are decoding
     * @param index the index to decode from
     */
    public static BString read(final String encoded, AtomicInteger index) {
        final int colonIndex = encoded.indexOf(':', index.get());
        //final int length = Character.getNumericValue(encoded.charAt(index.getValue()));
        final int length = Integer.parseInt(encoded.substring(index.get(), colonIndex));
        index.set(colonIndex + 1);
        final String value = encoded.substring(index.get(), index.get() + length);
        System.out.println(value.length());
        index.set(index.get() + length);
        BString result = new BString(value);
        //System.out.println(result.toStringValue());
        return result;
    }

    public int compareTo(BString o) {
        byte b1[] = Utils.getBytesOf(this.toStringValue());
        byte b2[] = Utils.getBytesOf(o.toStringValue());
        int len = Math.min(b1.length, b2.length);
        for (int i = 0; i < len; i++) {
            if (b1[i] > b2[i]) {
                return 1;
            }
            if (b1[i] < b2[i]) {
                return -1;
            }
        }
        return b1.length - b2.length;
    }
}
