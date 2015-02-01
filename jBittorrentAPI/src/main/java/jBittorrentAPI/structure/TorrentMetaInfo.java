package jBittorrentAPI.structure;

import jBittorrentAPI.Constants;
import jBittorrentAPI.bencode.btypes.BElement;
import jBittorrentAPI.bencode.btypes.BList;
import jBittorrentAPI.bencode.btypes.BMap;
import jBittorrentAPI.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.structure
 * By @author Mmuzafarov
 * Created at 25.06.14
 */
public class TorrentMetaInfo {

    /**
     *  Info Dictionary
     *  Analog to "name" parameter in BitTorrent Spec: the file path of the directory
     *  in which to store all the files. This is purely advisory. (string)
     */
    private String directory;
    private List<TorrentMetaFileResource> files;
    private List<TorrentMetaPiece> pieces;
    private byte[] hash;
    private Long totalFilesLength;

    public TorrentMetaInfo(BMap info) throws TorrentMetaException {
        /** Info in Single File Mode or in Multiple File Mode */
        this.files = new ArrayList<TorrentMetaFileResource>();
        if (info.containsKey(BConstantMeta.FILES)) {
            this.directory = info.getStringValue(BConstantMeta.NAME);
            BList fileList = info.getListValue(BConstantMeta.FILES);
            for (BElement singleFile : fileList) {
                    this.files.add(new TorrentMetaFileResource(singleFile.toMap()));
            }
        } else {
            this.files.add(new TorrentMetaFileResource(info));
        }

        if (info.containsKey(BConstantMeta.PIECES)) {
            int pieceLength = info.getIntValue(BConstantMeta.PIECE_LENGTH);
            Byte [] piecesString = info.get(BConstantMeta.PIECES).toByteValue();
            this.pieces = new ArrayList<TorrentMetaPiece>();
            for (Byte [] piece : Utils.splitArray(piecesString, Constants.SHA_1_LENGTH)) {
                this.pieces.add(new TorrentMetaPiece(pieceLength, ArrayUtils.toPrimitive(piece)));
            }
        } else {
            throw new TorrentMetaException(BConstantMeta.PIECES + " not found");
        }
        //System.out.println("INFO /n");
        //System.out.println(info.encode());

        //System.out.println(info.encode());
        this.hash = Utils.hash(Utils.getBytesOf(info.encode()), Constants.SHA_1_ALGORITHM);
        this.totalFilesLength = Utils.sum(this.files);
        //throw new TorrentMetaException("info = ");
    }

    public byte[] getHash() {
        return hash;
    }

    public String getDirectory() {
        return directory;
    }

    public List<TorrentMetaFileResource> getFiles() {
        return files;
    }

    public Long getTotalFilesLength() {
        return totalFilesLength;
    }

    public List<TorrentMetaPiece> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String separate = System.getProperty("line.separator");
        result.append(super.toString());
        result.append(separate);
        result.append(BConstantMeta.NAME);
        result.append(separate);
        result.append(this.directory);
        result.append("Total files length");
        result.append(separate);
        result.append(this.totalFilesLength);
        for (TorrentMetaFileResource file : this.files) {
            result.append(file.toString());
            result.append(separate);
        }
        result.append("Info hash");
        result.append(new String(this.hash));
        result.append(separate);
        result.append(BConstantMeta.PIECES);
        result.append(separate);
        for (TorrentMetaPiece piece : this.pieces) {
            result.append(piece.toString());
            result.append(separate);
        }
        return String.valueOf(result);
    }
}
