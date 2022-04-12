package edu.westga.cs3212.imageViewer.model;

import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class ServerCommunitcator {
    private static JSONObject serverReply;

    public static JSONObject sendMessage(String request) {
        Context context = ZMQ.context(1);
        try (@SuppressWarnings("deprecation")
		Socket socket = context.socket(ZMQ.REQ)) {
            socket.connect("tcp://127.0.0.1:5555");
            socket.send(request.getBytes(ZMQ.CHARSET),0);
            String help = socket.recvStr();
			
			serverReply = new JSONObject(help);
            
        }
        
        return serverReply;
    }
}
