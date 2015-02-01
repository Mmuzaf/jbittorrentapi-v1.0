package examples;

import jBittorrentAPI.Peer;
import jBittorrentAPI.bencode.BCoder;
import jBittorrentAPI.reciever.TrackerProtocolHandler;
import jBittorrentAPI.utils.IOManager;
import jBittorrentAPI.utils.TorrentBuilder;
import jBittorrentAPI.utils.Utils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage examples
 * By @author Mmuzafarov
 * Created at 03.07.14
 */
public class ApplicationTracker {
    public static void main(final String[] args) {
        String fileName = "Examples/resources/[NNM-Club.me]_IntelliJ IDEA Ultimate Edition 13.0.1.torrent";

        TorrentBuilder builder =
                new TorrentBuilder(BCoder.decode(IOManager.readBytesFromFile(new File(fileName))));

        TrackerProtocolHandler tm = new TrackerProtocolHandler(builder.getTorrent());
        LinkedHashMap<String, Peer> result = tm.getTrackerPeers(Utils.generateID(), builder.getTorrent().getInfo().getHash(), 6881);
        for (Map.Entry<String, Peer> entry : result.entrySet()) {
            System.out.println(entry.getKey());
            //System.out.println(entry.getValue());
        }
        //System.out.println(peerList.toString());
    }
}
