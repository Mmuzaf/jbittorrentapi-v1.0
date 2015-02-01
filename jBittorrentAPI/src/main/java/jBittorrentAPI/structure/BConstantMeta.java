package jBittorrentAPI.structure;

import jBittorrentAPI.bencode.btypes.BString;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.bencode.btypes
 * By @author Mmuzafarov
 * Created at 26.06.14
 */
public final class BConstantMeta {
    public static BString ANNOUNCE = new BString("announce");
    public static BString ANNOUNCE_LIST = new BString("announce-list");
    public static BString CREATION_DATE = new BString("creation date");
    public static BString COMMENT = new BString("comment");
    public static BString CREATED_BY = new BString("created by");
    public static BString ENCODING = new BString("encoding");

    /** Info Dictionary */
    public static BString INFO = new BString("info");
    public static BString PIECE_LENGTH = new BString("piece length");
    public static BString PIECES = new BString("pieces");
    public static BString PRIVATE = new BString("private");

    /** Info in Single File Mode */
    public static BString NAME = new BString("name");
    public static BString LENGTH = new BString("length");
    public static BString MD5SUM = new BString("md5sum");

    /** Info in Multiple File Mode */
    public static BString FILES = new BString("files");
    public static BString PATH = new BString("path");

    /**
     * Tracker response
     */
    public static BString INTERVAL = new BString("interval");
    public static BString FAILURE_REASON = new BString("failure reason");
    public static BString PEERS = new BString("peers");
    public static BString PEER_ID = new BString("peer_id");
    public static BString PEER_PORT = new BString("port");
    public static BString PEER_IP = new BString("ip");

}
