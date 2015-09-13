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
 * Created at 10.07.14
 */
public interface PeerProtocolMessage {

    void write(OutputStream o) throws IOException;

    void read(InputStream i);
}
