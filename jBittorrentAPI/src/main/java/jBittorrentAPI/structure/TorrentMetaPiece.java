package jBittorrentAPI.structure;

import jBittorrentAPI.Constants;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.structure
 * By @author Mmuzafarov
 * Created at 27.06.14
 */
public class TorrentMetaPiece {

    // What i would use to declare class parameters? Primivite type or Container?

    private int pieceLength; // number of bytes in each piece (integer)
    private byte [] pieceHash; // 20-byte SHA1 hash values (byte string, i.e. not URL-encoded)


    public TorrentMetaPiece(int pieceLength, byte[] pieceHash) throws TorrentMetaException {
        this.pieceLength = pieceLength;
        if (pieceHash.length == Constants.SHA_1_LENGTH) {
            this.pieceHash = pieceHash;
        } else
            throw new TorrentMetaException("Piece hash must be " + Constants.SHA_1_LENGTH + " byte length instead of " + pieceHash.length);
    }

    public int getPieceLength() {
        return pieceLength;
    }

    public byte[] getPieceHash() {
        return pieceHash;
    }

    public String toString() {
        return null;
    }
}
