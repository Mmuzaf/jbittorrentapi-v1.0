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
    public String encode();

    public Integer toIntValue();
    public Long toLongValue();
    public String toStringValue();
    public Byte [] toByteValue();
    public BMap toMap();
    public BList toList();

}
