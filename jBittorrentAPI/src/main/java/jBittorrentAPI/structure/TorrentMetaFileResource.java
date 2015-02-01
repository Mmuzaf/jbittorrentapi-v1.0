package jBittorrentAPI.structure;

import jBittorrentAPI.bencode.btypes.BList;
import jBittorrentAPI.bencode.btypes.BMap;
import jBittorrentAPI.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;
import sun.security.util.Length;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.structure
 * By @author Mmuzafarov
 * Created at 25.06.14
 */
public class TorrentMetaFileResource implements Length {

    private String path;
    private int length; // length of the file in bytes (integer)
    private byte [] md5sum; //(optional) a 32-character hexadecimal string corresponding to the MD5 sum of the file

    public String getPath() {
        return path;
    }

    public int length() {
        return this.length;
    }

    public byte[] getMd5sum() {
        return md5sum;
    }

    public TorrentMetaFileResource(BMap file) throws TorrentMetaException {
        this.md5sum = ArrayUtils.toPrimitive(file.getByteValue(BConstantMeta.MD5SUM));
        if (file.containsKey(BConstantMeta.LENGTH)) {
            this.length = file.getIntValue(BConstantMeta.LENGTH);
        } else {
            throw new TorrentMetaException(BConstantMeta.LENGTH + " not found");
        }
        // INFO in Multiple File Mode - then getValue PATH
        if (file.containsKey(BConstantMeta.PATH)) {
            final BList pathList = file.getListValue(BConstantMeta.PATH);
            this.path = Utils.pathToString(pathList);
        } else {
            // INFO in Single File Mode - then getValue NAME
            this.path = file.getStringValue(BConstantMeta.NAME);
        }
    }

    public String toString () {
        StringBuilder result = new StringBuilder();
        String separate = System.getProperty("line.separator");
        result.append(super.toString());
        result.append(separate);
        result.append(BConstantMeta.PATH);
        result.append(separate);
        result.append(this.path);
        result.append(BConstantMeta.LENGTH);
        result.append(separate);
        result.append(this.length);
        result.append(BConstantMeta.MD5SUM);
        result.append(separate);
        result.append(new String(this.md5sum));
        return String.valueOf(result);
    }

}
