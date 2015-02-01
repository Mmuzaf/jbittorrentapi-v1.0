package jBittorrentAPI.peerwire;

import jBittorrentAPI.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.peerwire
 * By @author Mmuzaf
 * Created at 11.07.14
 */
public class RequestMessage extends AbstractPeerProtocolMessage {
    public static final int MESSAGE_ID = 0x06;
    public static final int LENGTH = 0x00000013;

    public int pieceIndex;
    public int begin;
    public int length;

    public RequestMessage(int pieceIndex, int begin, int length) {
        super(MESSAGE_ID);
        this.pieceIndex = pieceIndex;
        this.begin = begin;
        this.length = length;
    }

    @Override
    public void write(OutputStream o) throws IOException {
        o.write(
                Utils.merge(
                        Utils.intTo4ByteArray(LENGTH),
                        Utils.intTo4ByteArray(MESSAGE_ID),
                        Utils.intTo4ByteArray(pieceIndex),
                        Utils.intTo4ByteArray(begin),
                        Utils.intTo4ByteArray(length)
                )
        );
    }

    @Override
    public void read(InputStream i) {
    }

    @Override
    public String toString() {
        return "REQUEST MESSAGE [pieceIndex=" + pieceIndex +
                ", begin=" + begin + ", length=" + length + "]";
    }
}
