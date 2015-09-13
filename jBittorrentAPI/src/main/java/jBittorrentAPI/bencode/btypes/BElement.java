package jBittorrentAPI.bencode.btypes;

import jBittorrentAPI.bencode.btypes.BList;
import jBittorrentAPI.bencode.btypes.BMap;

/**
 * Project Bencoding
 * Created by Francis on 27/03/14.
 */
public interface BElement {

    /**
     * @return the encoded getParamName
     */
    String encode();

    Integer toIntValue();
    Long toLongValue();
    String toStringValue();
    Byte [] toByteValue();
    BMap toMap();
    BList toList();

}
