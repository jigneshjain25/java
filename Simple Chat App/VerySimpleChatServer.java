import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class VerySimpleChatServer{
	ArrayList clientOutputStreams;
	
	public class ClientHandler implements Runnable{
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket){
			try{
				sock=clientSocket;
				reader=new BufferedReader(new InputStreamReader(sock.getInputStream()));
				
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
		public void run(){
			String message;
			try{
				while((message=reader.readLine())!=null){
					System.out.println("read " + message);
					tellEveryone(message);
				}
			}catch(Exception ex){ex.printStackTrace();}
		
		}
	}
	public static void main(String[] args){
		new VerySimpleChatServer().go();
	}
	
	public void go(){
		clientOutputStreams=new ArrayList();
		try{
			ServerSocket serverSock=new ServerSocket(5000);
			while(true){
				Socket clientSocket=serverSock.accept();
				PrintWriter writer=new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);
				Thread t=new Thread(new ClientHandler(clientSocket));
				t.start();
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	
	}
	public void tellEveryone(String message){
		Iterator it=clientOutputStreams.iterator();
		while(it.hasNext()){
			try{
				PrintWriter writer=(PrintWriter) it.next();
				writer.println(message);
				writer.flush();
				
			}catch(Exception ex){ex.printStackTrace();}
		}
	}
	
	
}
