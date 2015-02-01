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

package example;

import jBittorrentAPI.ConnectionManager;

import java.io.File;

/**
 * Simple example to show how it is possible to publish a .torrentMetaInfo file to a
 * tracker
 */
class ExamplePublish{
    public static void main(String[] args){
        if(args.length < 4){
            System.out.println(showHelp());
            System.exit(0);
        }
        File f = new File(args[0]);
        String comment = "";
        for(int i = 4; i < args.length; i++)
            comment += args[i];
        try{
            ConnectionManager.publish(args[0], args[1],
                    args[2], args[3],
                    f.getName(), "", comment, "7");
        }catch(Exception e){
            System.out.println(showHelp());
            System.exit(0);
        }
    }

    private static String showHelp() {
        return
                "ExamplePublish use:\r\n\tExamplePublish " +
                "torrentPath trackerUploadURL username password comment\r\n\r\n" +
                "If tracker does not need username and password, enter 'none' instead";
    }

}
