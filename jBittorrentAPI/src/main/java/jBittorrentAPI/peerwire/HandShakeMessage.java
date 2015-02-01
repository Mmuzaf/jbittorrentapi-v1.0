package jBittorrentAPI.peerwire;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.peerwire
 * By @author Mmuzaf
 * Created at 14.07.14
 */
public class HandShakeMessage implements PeerProtocolMessage {
    public int protocolStringLength = 19;
    public String protocolString = "BitTorrent protocol";
    public long reserved = 0;
    public byte[] torrentHash;
    public String peerID;

    public HandShakeMessage() {
    }

    public HandShakeMessage(byte[] torrentHash, String peerID) {
        this.torrentHash = torrentHash;
        this.peerID = peerID;
    }

    @Override
    public void write(OutputStream o) throws IOException {

    }

    @Override
    public void read(InputStream i) {


    }

}
