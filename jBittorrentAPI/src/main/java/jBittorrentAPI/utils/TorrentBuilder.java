/*
 * Java Bittorrent API as its name indicates is a JAVA API that implements the Bittorrent Protocol
 * This project contains two packages:
 * 1. jBittorrentAPI is the "client" part, i.e. it implements all classes needed to publish
 *    files, share them and download them.
 *    This package also contains example classes on how a developer could create new applications.
 * 2. trackerBT is the "tracker" part, i.e. it implements a all classes needed to run
 *    a Bittorrent tracker that coordinates peers exchanges. *
 *
 * Copyright (C) 2007 Baptiste Dubuis, Artificial Intelligence Laboratory, EPFL
 *
 * This file is part of jbittorrentapi-v1.0.zip
 *
 * Java Bittorrent API is free software and a free user study set-up;
 * you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Java Bittorrent API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Java Bittorrent API; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * @version 1.0
 * @author Baptiste Dubuis
 * To contact the author:
 * email: baptiste.dubuis@gmail.com
 *
 * More information about Java Bittorrent API:
 *    http://sourceforge.net/projects/bitext/
 */

package jBittorrentAPI.utils;

import jBittorrentAPI.bencode.BCoder;
import jBittorrentAPI.bencode.btypes.BMap;
import jBittorrentAPI.structure.TorrentMetaException;
import jBittorrentAPI.structure.Torrent;

import java.io.File;

/**
 *
 * Class enabling to process a torrent file
 * @author Baptiste Dubuis
 * @version 0.1
 */
public class TorrentBuilder {

    private Torrent torrent;

    public TorrentBuilder(BMap decoded) {
        try {
            this.torrent = construct(decoded);
        } catch (TorrentMetaException e) {
            e.printStackTrace();
        }
    }

    public TorrentBuilder(File file) {
        new TorrentBuilder((BMap) BCoder.decode(IOManager.readBytesFromFile(file)).get(0));
    }

    public TorrentBuilder(String fileName) {
        new TorrentBuilder(new File(fileName));
    }
    /**
     * Returns the local Torrent in its current state
     * @return Torrent
     */
    public Torrent getTorrent(){
        return this.torrent;
    }

    /**
     * Retrieve all useful information and represent it as a Torrent object
     * @param decoded Map
     * @return Torrent
     */
    public Torrent construct(BMap decoded) throws TorrentMetaException {
        if(decoded == null)
            throw new TorrentMetaException();
        return new Torrent(decoded);
    }

//    /**
//     * Updates the Torrent object according to the given parameters
//     * @param url The ANNOUNCE url
//     * @param pieceLength The length of the pieces of the torrent
//     * @param comment The comments for the torrent
//     * @param encoding The encoding of the torrent
//     * @param filename The path of the file to be added to the torrent
//     */
//    public void updateTorrentMetaInfo(String url, int pieceLength, String comment,
//                                      String encoding, String filename) {
//        this.torrent.setAnnounce(new BString(url));
//        this.torrent.setPieceLength(pieceLength * 1024);
//        this.torrent.setCreatedBy(new BString(Constants.CLIENT));
//        this.torrent.setComment(new BString(comment));
//        this.torrent.setCreationDate(new BInteger(System.currentTimeMillis()));
//        this.torrent.setEncoding(new BString(encoding));
//        addFiles(Arrays.asList(filename));
//    }
//
//    /**
//     * Updates the Torrent object according to the given parameters
//     * @param url The ANNOUNCE url
//     * @param pieceLength The length of the pieces of the torrent
//     * @param comment The comments for the torrent
//     * @param encoding The encoding of the torrent
//     * @param name The name of the directory to save the files in
//     * @param filenames The path of the file to be added to the torrent
//     * @throws java.lang.Exception
//     */
//    public void setTorrentData(String url, int pieceLength, String comment,
//                               String encoding, String name, List<String> filenames) throws Exception {
//        this.torrent.setAnnounce(new BString(url));
//        this.torrent.setPieceLength(pieceLength * 1024);
//        this.torrent.setCreatedBy(new BString(Constants.CLIENT));
//        this.torrent.setComment(new BString(comment));
//        this.torrent.setCreationDate(new BInteger(System.currentTimeMillis()));
//        this.torrent.setEncoding(new BString(encoding));
//        this.torrent.setSaveAs(name);
//        addFiles(filenames);
//    }
//
//    /**
//     * Add the files in the list to the torrent
//     * @param filenames An array containing the files to be added
//     * @return int The number of files that have been added
//     */
//    public int addFiles(List<String> filenames) {
//        int countFileAddeds = 0;
//        if (this.torrent.getTotalLength() == -1)
//            this.torrent.setTotalLength(0);
//
//        List<String> filePaths = new ArrayList<String>();
//        List<byte[]> fileMD5Sums = new ArrayList<byte[]>();
//        List<Long> fileLengths = new ArrayList<Long>();
//
//        for (String fileName : filenames) {
//            File file = new File(fileName);
//            if (file.exists()) {
//                filePaths.add(file.getPath());
//                fileLengths.add(file.length());
//                fileMD5Sums.add(Utils.hash(IOManager.readBytesFromFile(file), Constants.MD5_ALGORITHM));
//            }
//            countFileAddeds++;
//        }
//        this.torrent.setFileLengths(fileLengths);
//        this.torrent.setFilePaths(filePaths);
//        this.torrent.setFileMD5Sums(fileMD5Sums);
//        this.torrent.setTotalLength(Utils.sum(fileLengths));
//        return countFileAddeds;
//    }
//
//    /**
//     * Generate the SHA-1 hashes for the file in the torrent in parameter
//     * @param tor Torrent
//     */
//    public static void generatePieceHashes(Torrent tor) {
//        ByteBuffer bb = ByteBuffer.allocate(tor.pieceLength);
//        int index = 0;
//        long total = 0;
//        tor.piece_hash_values_as_binary.clear();
//        for (int i = 0; i < tor.name.size(); i++) {
//            total += (Integer) tor.length.getValue(i);
//            File f = new File((String) tor.name.getValue(i));
//            if (f.exists()) {
//                try {
//                    FileInputStream fis = new FileInputStream(f);
//                    int read = 0;
//                    byte[] data = new byte[tor.pieceLength];
//                    while ((read = fis.read(data, 0, bb.remaining())) != -1) {
//                        bb.put(data, 0, read);
//                        if (bb.remaining() == 0) {
//                            tor.piece_hash_values_as_binary.add(Utils.hash(bb.
//                                    array()));
//                            bb.clear();
//                        }
//                    }
//                } catch (FileNotFoundException fnfe) {} catch (IOException ioe) {}
//            }
//        }
//        if (bb.remaining() != bb.capacity())
//            tor.piece_hash_values_as_binary.add(Utils.hash(Utils.subArray(
//                    bb.array(), 0, bb.capacity() - bb.remaining())));
//    }
//
//    /**
//     * Generate the bytes of the bencoded Torrent data
//     * @param t Torrent
//     * @return byte[]
//     */
//    public byte[] generateTorrent(Torrent t) {
//        SortedMap map = new TreeMap();
//        map.put("ANNOUNCE", t.announceURL);
//        if(t.comment.length() > 0)
//            map.put("comment", t.comment);
//        if(t.creationDate >= 0)
//            map.put("creation date", t.creationDate);
//        if(t.createdBy.length() > 0)
//            map.put("created by", t.createdBy);
//
//        SortedMap INFO = new TreeMap();
//        if (t.name.size() == 1) {
//            INFO.put("length", (Integer) t.length.getValue(0));
//            INFO.put("name", new File((String) t.name.getValue(0)).getName());
//        } else {
//            if (!t.saveAs.matches(""))
//                INFO.put("name", t.saveAs);
//            else
//                INFO.put("name", "noDirSpec");
//            ArrayList files = new ArrayList();
//            for (int i = 0; i < t.name.size(); i++) {
//                SortedMap file = new TreeMap();
//                file.put("length", (Integer) t.length.getValue(i));
//                String[] path = ((String) t.name.getValue(i)).split("\\\\");
//                File f = new File((String)(t.name.getValue(i)));
//
//                ArrayList pathList = new ArrayList(path.length);
//                for (int j = (path.length > 1) ? 1 : 0; j < path.length; j++)
//                    pathList.add(path[j]);
//                file.put("path", pathList);
//                files.add(file);
//            }
//            INFO.put("files", files);
//        }
//        INFO.put("piece length", t.pieceLength);
//        byte[] pieces = new byte[0];
//        for (int i = 0; i < t.piece_hash_values_as_binary.size(); i++)
//            pieces = Utils.concat(pieces,
//                                  (byte[]) t.piece_hash_values_as_binary.
//                                  getValue(i));
//        INFO.put("pieces", pieces);
//        map.put("INFO", INFO);
//        try {
//            byte[] data = BEncoder.encode(map);
//            return data;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * Generate the bytes for the current object Torrent
//     * @return byte[]
//     */
//    public byte[] generateTorrent() {
//        return this.generateTorrent(this.torrent);
//    }

}
