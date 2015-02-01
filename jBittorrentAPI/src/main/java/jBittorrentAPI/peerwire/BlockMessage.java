package jBittorrentAPI.peerwire;

import jBittorrentAPI.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.peerwire
 * By @author Mmuzaf
 * Created at 11.07.14
 */
public class BlockMessage extends AbstractPeerProtocolMessage {
    public static final int MESSAGE_ID = 0x07;

    public int pieceIndex;
    public int begin;
    // TODO: ByteBuffer data;
    byte [] data;

    public BlockMessage(int pieceIndex, int begin, byte [] data) {
        super(MESSAGE_ID);
        this.pieceIndex = pieceIndex;
        this.begin = begin;
        this.data = data;

    }

    @Override
    public void write(OutputStream o) throws IOException {
        o.write(
                Utils.merge(
                        Utils.intTo4ByteArray(9 + data.length),
                        Utils.intTo4ByteArray(MESSAGE_ID),
                        Utils.intTo4ByteArray(pieceIndex),
                        Utils.intTo4ByteArray(begin),
                        this.data

                )
        );
    }

    @Override
    public void read(InputStream i) {

    }

    @Override
    public String toString() {
        return "BLOCK MESSAGE [pieceIndex=" + pieceIndex +
                ", begin=" + begin + "]";
    }

}
