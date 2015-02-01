package jBittorrentAPI.bencode;

import jBittorrentAPI.Constants;
import jBittorrentAPI.bencode.btypes.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project Bencoding
 * Created by Francis on 27/03/14.
 */
public class BCoder {

    public static String encode (Map<BElement, BElement> decoded) {
        String result = "";
        for (Map.Entry<BElement, BElement> entry : decoded.entrySet()) {
            result += entry.getKey() + ":" + entry.getValue();
        }
        return result;
    }

    public static BMap decode(final byte[] encoded) {
        List<BElement> result = new ArrayList<BElement>();
        String sEncoded = new String(encoded, Constants.BYTE_CHARSET);
        final AtomicInteger index = new AtomicInteger(0);
        while (index.get() != sEncoded.length()) {
            try {
                result.add(read(sEncoded, index));
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            } catch (EnumConstantNotPresentException e) {
                e.printStackTrace();
            }
        }
        // Always return first element
        return result.get(0).toMap();
    }

    public static BElement[] read(final String encoded) {
        final AtomicInteger index = new AtomicInteger(0);
        final List<BElement> elements = new ArrayList<BElement>();
        while (index.get() != encoded.length()) {
            elements.add(read(encoded, index));
        }
        return elements.toArray(new BElement[elements.size()]);
    }

    public static BElement read(final String encoded, final AtomicInteger index) {
        switch (encoded.charAt(index.get())) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return BString.read(encoded, index);
            case 'i':
                return BInteger.read(encoded, index);
            case 'l':
                return ArrayBList.read(encoded, index);
            case 'd':
                return HashBMap.read(encoded, index);
        }
        throw new RuntimeException("Failed to identify type{" + encoded.substring(0, index.get()) + "} at index " + index.get());
    }
}
