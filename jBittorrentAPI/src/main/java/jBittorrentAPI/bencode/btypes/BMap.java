package jBittorrentAPI.bencode.btypes;

import java.util.Map;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.bencode
 * By @author Mmuzafarov
 * Created at 02.07.14
 */
public interface BMap extends Map<BString,BElement>, BElement {
    Integer getIntValue(BString key);
    Long getLongValue(BString key);
    String getStringValue(BString key);
    Byte [] getByteValue(BString key);
    BList getListValue(BString key);
    BMap getMapValue(BString key);
}
