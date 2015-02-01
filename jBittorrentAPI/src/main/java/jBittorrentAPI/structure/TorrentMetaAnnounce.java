package jBittorrentAPI.structure;

import jBittorrentAPI.bencode.btypes.BMap;

import java.util.List;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.structure
 * By @author Mmuzafarov
 * Created at 25.06.14
 */
public class TorrentMetaAnnounce {

    /** The announce URL of the tracker (string)
     */
    private String announceUrl;
    /** This is an extention to the official specification,
     *  offering backwards-compatibility. (list of lists of strings).
     */
    private List<String> announceList;

    public TorrentMetaAnnounce(BMap root) throws TorrentMetaException {
        if (root.containsKey(BConstantMeta.ANNOUNCE)) {
            this.announceUrl = root.getStringValue(BConstantMeta.ANNOUNCE);
        } else {
            throw new TorrentMetaException("Announce metadata not found");
        }
        if (root.containsKey(BConstantMeta.ANNOUNCE_LIST)) {
            this.announceList = root.getListValue(BConstantMeta.ANNOUNCE_LIST).getAsStringsList();
        }
    }

    public String getAnnounceUrl() {
        return announceUrl;
    }

    public List<String> getAnnounceList() {
        return announceList;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("ANNOUNCE [announceUrl=").append(announceUrl).append(", announceList=[");
        for (String entry : announceList) {
            result.append(", ").append(entry);
        }
        result.append("]]");
        return String.valueOf(result);
    }
}
