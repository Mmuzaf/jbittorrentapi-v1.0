package jBittorrentAPI.bencode.btypes;

import java.util.List;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.bencode
 * By @author Mmuzafarov
 * Created at 02.07.14
 */
public interface BList extends List<BElement>, BElement {
    public Integer getIntValue(int key);
    public String getStringValue(int key);
    public List<String> getAsStringsList();
}
