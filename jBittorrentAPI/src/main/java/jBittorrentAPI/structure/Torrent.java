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

package jBittorrentAPI.structure;

import jBittorrentAPI.bencode.btypes.BMap;

/**
 * Representation of a torrentMetaInfo file
 *
 * @author Baptiste Dubuis
 * @version 0.1
 */
public class Torrent {

    private String comment;
    private String createdBy;
    private Long  creationDate;

    private TorrentMetaAnnounce tracker;
    private TorrentMetaInfo info;

    private String encoding;
    private String saveAs;

    public Torrent(BMap root) throws TorrentMetaException {
        this.tracker = new TorrentMetaAnnounce(root);
        if (root.containsKey(BConstantMeta.INFO)) {
            this.info = new TorrentMetaInfo(root.getMapValue(BConstantMeta.INFO));
        } else {
            throw new TorrentMetaException("Unknown meta type " + root.getStringValue(BConstantMeta.INFO));
        }

        this.createdBy = root.getStringValue(BConstantMeta.CREATED_BY);
        this.creationDate = root.getLongValue(BConstantMeta.CREATION_DATE);
        this.comment = root.getStringValue(BConstantMeta.COMMENT);
        this.encoding = root.getStringValue(BConstantMeta.ENCODING);
        this.saveAs = root.getStringValue(BConstantMeta.NAME);
    }

    public String getEncoding() {
        return encoding;
    }

    public String getSaveAs() {
        return saveAs;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public TorrentMetaAnnounce getTracker() {
        return tracker;
    }

    public TorrentMetaInfo getInfo() {
        return info;
    }

    public byte [] getInfoHash() {return this.info.getHash(); }

    /**
     * Print the torrentMetaInfo information in a readable manner.
     * Choose if we want a detailed output or not. Detailed
     * output prints the comment, the files list and the pieces hashes while the
     * standard output simply prints tracker url, creator, creation date and
     * INFO hash
     */

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String separate = System.getProperty("line.separator");
        result.append(super.toString());
        result.append(separate);
        result.append(this.tracker.toString());
        result.append(separate);
        result.append(BConstantMeta.CREATED_BY.toStringValue());
        result.append(separate);
        result.append(this.createdBy);
        result.append(BConstantMeta.CREATION_DATE.toStringValue());
        result.append(separate);
        result.append(this.creationDate);
        result.append(separate);
        result.append("INFO HASH");
        result.append(separate);
        result.append(new String(this.info.getHash()));
        return String.valueOf(result);
    }

}
