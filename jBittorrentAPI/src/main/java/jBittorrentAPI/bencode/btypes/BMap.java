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
    public Integer getIntValue(BString key);
    public Long getLongValue(BString key);
    public String getStringValue(BString key);
    public Byte [] getByteValue(BString key);
    public BList getListValue(BString key);
    public BMap getMapValue(BString key);
}
