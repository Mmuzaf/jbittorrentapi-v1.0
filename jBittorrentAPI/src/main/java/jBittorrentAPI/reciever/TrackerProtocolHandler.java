package jBittorrentAPI.reciever;

import jBittorrentAPI.Peer;
import jBittorrentAPI.bencode.BCoder;
import jBittorrentAPI.bencode.btypes.BElement;
import jBittorrentAPI.bencode.btypes.BList;
import jBittorrentAPI.bencode.btypes.BMap;
import jBittorrentAPI.structure.TorrentMetaAnnounce;
import jBittorrentAPI.structure.BConstantMeta;
import jBittorrentAPI.structure.Torrent;
import jBittorrentAPI.utils.IOManager;
import jBittorrentAPI.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.reciever
 * By @author Mmuzaf
 * Created at 09.07.14
 */
public class TrackerProtocolHandler {

    private TorrentMetaAnnounce address;
    private long nextAutoRequest;

    public TrackerProtocolHandler(Torrent t) {
        this.address = t.getTracker();
        this.nextAutoRequest = 0;
    }

    /**
     * Contact the tracker according to the HTTP/HTTPS tracker protocol and using
     * the information in the Torrent.
     * @param peerId byte[]
     * @param dl long
     * @param ul long
     * @param left long
     * @param event String
     * @return A Map containing the decoded tracker response
     */
    public BMap sendRequest(byte[] peerId, byte[] infoHash,
                            String port, long dl, long ul,
                            long left, TrackerEvent event) throws IOException {
        try {
            URL source = new URL(this.address.getAnnounceUrl() + "?info_hash=" +
                    Utils.byteArrayToURLString(infoHash) + "&peer_id=" +
                    Utils.byteArrayToURLString(peerId) + "&port="+
                    port +
                    "&downloaded=" + dl + "&uploaded=" + ul +
                    "&left=" +
                    left + "&numwant=100&compact=1&event=" + event);
            //&uk=H7aAa7vNd2
            System.out.println("Contact Tracker. URL source = " + source);
            URLConnection uc = source.openConnection();
            return BCoder.decode(IOManager.readBytesFromStream(uc.getInputStream()));
        } catch (MalformedURLException ue) {
            this.fireUpdateFailed(2,
                    "Tracker URL is not valid... Check if your data is correct and try again");
        } catch (UnknownHostException uhe) {
            this.fireUpdateFailed(3, "Tracker not available... Retrying...");
        } catch (IOException ioe) {
            this.fireUpdateFailed(4, "Tracker unreachable... Retrying");
        } catch (Exception e) {
            this.fireUpdateFailed(5, "Internal error");
        }
        return null;
    }

    public LinkedHashMap<String, Peer> getTrackerPeers (byte [] peerId, byte [] infoHash, int port) {
        LinkedHashMap<String, Peer> peers = new LinkedHashMap<String, Peer>();
        try {
            BMap trackerResponse = sendRequest(peerId, infoHash, Integer.toString(port), 0, 0, 0, TrackerEvent.STARTED);
            if (trackerResponse.containsKey(BConstantMeta.FAILURE_REASON)) {
                this.fireUpdateFailed(0, "The tracker returns the following error message:" +
                                "\t'" + trackerResponse.getStringValue(BConstantMeta.FAILURE_REASON) + "'");
                return peers;
            } else {
                if ((trackerResponse.getLongValue(BConstantMeta.INTERVAL)) < this.nextAutoRequest)
                    this.nextAutoRequest = trackerResponse.getLongValue(BConstantMeta.INTERVAL);
                else
                    this.nextAutoRequest *= 2;

                BElement oPeers = trackerResponse.get(BConstantMeta.PEERS);
                if (oPeers instanceof BList) {
                    BList peerList = trackerResponse.getListValue(BConstantMeta.PEERS);
                    for (BElement element : peerList) {
                        Peer peer = new Peer(element.toMap().getStringValue(BConstantMeta.PEER_ID),
                                element.toMap().getStringValue(BConstantMeta.PEER_IP),
                                element.toMap().getIntValue(BConstantMeta.PEER_PORT));
                        peers.put(peer.toString(), peer);
                    }
                } else {
                    byte [] p = ArrayUtils.toPrimitive(oPeers.toByteValue());
                    for (int i = 0; i < p.length; i += 6) {
                        Peer peer = new Peer(null,
                                             Utils.byteToUnsignedInt(p[i]) + "." +
                                             Utils.byteToUnsignedInt(p[i + 1]) + "." +
                                             Utils.byteToUnsignedInt(p[i + 2]) + "." +
                                             Utils.byteToUnsignedInt(p[i + 3]),
                                             Utils.twoByteToInt(Arrays.copyOfRange(p, i + 4, i + 6)));
                        peers.put(peer.toString(), peer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return peers;
    }

    private void fireUpdateFailed(int i, String s) {

    }

}
