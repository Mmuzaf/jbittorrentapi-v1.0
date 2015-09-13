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
    Integer getIntValue(int key);
    String getStringValue(int key);
    List<String> getAsStringsList();
}
