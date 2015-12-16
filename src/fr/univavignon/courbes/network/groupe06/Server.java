package fr.univavignon.courbes.network.groupe06;

import fr.univavignon.courbes.network.ServerCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import fr.univavignon.courbes.common.Board;
import fr.univavignon.courbes.common.Direction;
import fr.univavignon.courbes.common.Profile;

/**
 * @author Loïc
 *
 */
public class Server implements ServerCommunication {

	/**
	 * 
	 */
	protected String ip;
	/**
	 * 
	 */
	protected int port = 2345;
	/**
	 * 
	 */
	protected boolean isRunning = false;
	/**
	 * 
	 */
	protected static int size = 6;
	/**
	 * 
	 */
	protected static int nbClients = 0;
	/**
	 * 
	 */
	protected String arrayOfIp[] = new String[size];
	/**
	 * 
	 */
	protected static Socket socketArray[] = new Socket[size];
	/**
	 * 
	 */
	private ServerSocket sSocket = null;
	
	@Override
	public String getIp() {
		
		return this.ip;
	}

	@Override
	public int getPort() {

		return this.port;
	}

	@Override
	public void setPort(int port) {
		
		this.port = port;
		
	}
	
	@Override
	public void launchServer() {
		//step 1 : define address ip
		String adressIp;
 	    try {
 	        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
 	        while (interfaces.hasMoreElements()) {
 	            NetworkInterface iface = interfaces.nextElement();
 	            // filters out 127.0.0.1 and inactive interfaces
 	            if (iface.isLoopback() || !iface.isUp())
 	                continue;

 	            Enumeration<InetAddress> addresses = iface.getInetAddresses();
 	            while(addresses.hasMoreElements()) {
 	                InetAddress addr = addresses.nextElement();
 	                adressIp = addr.getHostAddress();
 	                if (adressIp.startsWith("192.168."))
 	                	this.ip = adressIp;
 	            }
 	        }
 	    } catch (SocketException e) {
 	        throw new RuntimeException(e);
 	    }
		//step 2 : define port if value by default is impossible
 	   try {
    		this.sSocket = new ServerSocket(this.port);
    	} catch (IOException e) {
        	this.port = 0;
    	}
 	   
 	    if(this.port == 0) {
 	    	for(int portTest = 1; portTest <= 3000; portTest++){ // find a free port
 	    		try {
 	    			this.sSocket = new ServerSocket(portTest);
 	        		this.port = portTest;
 	        		break;
 	    		} catch (IOException e) {
 	    			; //error here is normal , so we don't need an action.
 	    		}
 	    	}
 	    }
		//step 3 : launch server
 	   
		try {
			sSocket.close();
			sSocket = new ServerSocket(this.port, Server.size, InetAddress.getByName(this.ip));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 	    Thread launch = new Thread(new Runnable(){
 	    	@Override
			public void run(){
 	    		isRunning = true;
 	    		while(isRunning == true){
 	    			try {
 	    				//wait client communication
 	    				Socket client = sSocket.accept(); 
 	    				socketArray[nbClients] = client;
 	    				arrayOfIp[nbClients] = client.getInetAddress().getHostAddress();
 	    				//System.out.println(arrayOfIp[nbClients]);
 	    				nbClients++;
 	    				Thread newClient = new Thread(new ClientProcessor(client));
 	    				newClient.start();
 	    			} catch (IOException e) {
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		try {
 	    			sSocket.close();
 	    		} catch (IOException e) {
 	    			e.printStackTrace();
 	    			sSocket = null;
 	    		}
 	    	}
 	    });
	      
	   launch.start();
	}

	@Override
	public void closeServer() {
		this.isRunning = false;
		//envoyer un message a tous les clients.
	}

	@Override
	public void sendPointThreshold(final int pointThreshold) {
		Thread send = new Thread(new Runnable(){
			@Override
			public void run(){
				Socket sock = null;
				int i = 0;
				while(i < nbClients)
				{
					sock = socketArray[i];
					try {
						PrintWriter writer = new PrintWriter(sock.getOutputStream());
						writer.write(pointThreshold);
						writer.flush();
						writer = null;
					} catch(SocketException e){
						//Vire un client, faire une fonction pour ça.
					} catch (IOException e) {
						e.printStackTrace();
					}
					i++;
				}
				sock = null;
			}
		});
		send.start();
		return;
	}

	@Override
	public void sendBoard(final Board board) {
		Thread send = new Thread(new Runnable(){
			@Override
			public void run(){
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					oos.writeObject(board);
					oos.flush();
					// get the byte array of the object
					byte[] Buf= baos.toByteArray();
					
					int number = Buf.length;;
					byte[] data = new byte[4];
					
					// int -> byte[]
					for (int i = 0; i < 4; ++i) {
						int shift = i << 3; // i * 8
						data[3-i] = (byte)((number & (0xff << shift)) >>> shift);
					}
					DatagramSocket socket = new DatagramSocket(port);
					int i=0;
					while(i < nbClients) { 
						InetAddress client = InetAddress.getByName(arrayOfIp[i]);
						DatagramPacket packet = new DatagramPacket(data, 4, client, port+1);
	        	      	socket.send(packet);
	        	      	// now send the Board
	        	      	packet = new DatagramPacket(Buf, Buf.length, client, port+1);
	        	      	socket.send(packet);
	        	      	i++;
					}
					socket.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		send.start();
	}

	@Override
	public Map<Integer, Direction> retrieveCommands() {
		
		return null;
	}

	@Override
	public void sendText(final String message) {
		Thread send = new Thread(new Runnable(){
			@Override
			public void run(){
				Socket sock = null;
				int i = 0;
				while(i < nbClients)
				{
					sock = socketArray[i];
					try {
						PrintWriter writer = new PrintWriter(sock.getOutputStream());
						writer.write(message);
						writer.flush();
						writer = null;
					} catch(SocketException e){
						//Vire un client, faire une fonction pour ça.
					} catch (IOException e) {
						e.printStackTrace();
					}
					i++;
				}
				sock = null;
			}
		});
		send.start();
		return ;
		
	}

	@Override
	public String[] retrieveText() {
		
		return null;
	}
	

	@Override
	public void sendProfiles(final List<Profile> profiles) {
		Thread send = new Thread(new Runnable(){
			@Override
			public void run(){
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					oos.writeObject(profiles);
					oos.flush();
					// get the byte array of the object
					byte[] Buf= baos.toByteArray();
			
					int number = Buf.length;;
					byte[] data = new byte[4];
			
					// int -> byte[]
					for (int i = 0; i < 4; ++i) {
						int shift = i << 3; // i * 8
						data[3-i] = (byte)((number & (0xff << shift)) >>> shift);
					}
					
					Socket sock = null;
					int i = 0;
					while(i < nbClients)
					{
						sock = socketArray[i];
						try {
							DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
							dos.write(data, 0, 4);
							dos.write(Buf, 0, Buf.length);
						} catch(SocketException e){
							//Vire un client, faire une fonction pour ça.
						} catch (IOException e) {
							e.printStackTrace();
						}
						i++;
					}
					sock = null;
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		send.start();
		return;
		
	}

	@Override
	public List<Profile> retrieveProfiles() {
		return null;
	}   
	
}