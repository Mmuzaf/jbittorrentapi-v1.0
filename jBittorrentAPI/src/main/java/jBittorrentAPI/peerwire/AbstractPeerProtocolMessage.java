package jBittorrentAPI.peerwire;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.peerwire
 * By @author Mmuzaf
 * Created at 10.07.14
 */
public abstract class AbstractPeerProtocolMessage implements PeerProtocolMessage {
    public final int messageId;

    protected AbstractPeerProtocolMessage(int messageId) {
        this.messageId = messageId;
    }
}
