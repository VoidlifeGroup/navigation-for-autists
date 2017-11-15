package com.ibm.mysampleapp.parser;

import android.util.Xml;

import com.ibm.mysampleapp.graph.*;
import com.ibm.mysampleapp.graph.Room;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class XmlPullParserNFA {
    // We don't use namespaces
    private static final String ns = null;

    public ArrayList<Verticle> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }




    private ArrayList<Verticle> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Verticle> verticles = new ArrayList<Verticle>();

        parser.require(XmlPullParser.START_TAG, ns, "verticles");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("verticle")) {
                verticles.add(readVerticle(parser));
            } else {
                skip(parser);
            }
        }
        return verticles;
    }
    

    private Verticle readVerticle(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "verticle");
        ArrayList<Edge> edges = new ArrayList<Edge>();
        int id = Integer.parseInt(parser.getAttributeValue(null, "id"));
        String roomName = parser.getAttributeValue(null, "name");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("edge")) {
                edges.add(readEdge(parser));
            } else {
                skip(parser);
            }
        }
        if(roomName == null){
            return new Verticle(id, edges);
        } else {
            return new Room(id, edges, roomName);
        }
    }

    // Processes title tags in the feed.
    private Edge readEdge(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "edge");
        int from = Integer.parseInt(parser.getAttributeValue(null, "from"));
        int to = Integer.parseInt(parser.getAttributeValue(null, "to"));
        String img = parser.getAttributeValue(null, "img");
        int dis = Integer.parseInt(parser.getAttributeValue(null, "dis"));
        String l, r, u;
        Integer left = null, right = null, up = null;
        if((l = parser.getAttributeValue(null, "left")) != null){
            left = Integer.parseInt(l);
        }
        if((r = parser.getAttributeValue(null, "right")) != null){
            right = Integer.parseInt(r);
        }
        if((u = parser.getAttributeValue(null, "up")) != null){
            up = Integer.parseInt(u);
        }
        //int left = Integer.parseInt(parser.getAttributeValue(null, "left"));
        //int right = Integer.parseInt(parser.getAttributeValue(null, "right"));
        //int up = Integer.parseInt(parser.getAttributeValue("0", "up"));
        parser.nextTag();
        parser.require(XmlPullParser.END_TAG, ns, "edge");
        return new Edge(from, to, dis, left, right, up, img);
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
