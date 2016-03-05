package org.cmu.edu.socket;
/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/18/2016
 * */
import java.net.*;
import java.util.HashSet;
import java.util.Properties;
import java.io.*;

import org.cmu.edu.model.Automobile;
import org.cmu.edu.server.BuildCarModelOptions;

public class DefaultSocketClient 
				extends Socket
				implements  SocketClientInterface, 
							SocketClientConstants,
							Runnable {
	
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket socket;
	
    /* BuildAuto */
	private BuildCarModelOptions buildCarModelOptions;
	
    /* constructor */
    public DefaultSocketClient(Socket socket, BuildCarModelOptions buildCarModelOptions){
    	this.socket = socket;
    	this.buildCarModelOptions = buildCarModelOptions;
    }

    /* run */
    public void run(){
    	if (openConnection()){
    		handleSession();
    		closeSession();
    	}
    }
    
    public boolean openConnection(){
    	try {
			this.writer = new ObjectOutputStream(this.socket.getOutputStream());
			this.reader = new ObjectInputStream(this.socket.getInputStream());
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }

    /*
     * handle incoming request from client
     * possible operations are: upload properties, get automobile list,
     * get automobile, close session
     * upload properties: client will send "upload_[automobile|properties]" message, then send properties object
     * get automobile list: client will send "get list" message
     * get automobile: client will send "getAuto_automobileName" message
     * close session: client will send "close" message
     * */
    public void handleSession(){
    	while(true){
    		try {
				String message = (String) this.reader.readObject();
				if(message.startsWith("upload")){
					if(message.endsWith("automobile")){
						Automobile automobile = (Automobile) this.reader.readObject();
						this.buildCarModelOptions.getBuildAuto().addAutomobile(automobile);
					}else{
						Properties properties = (Properties) this.reader.readObject();
						this.buildCarModelOptions.buildAutoFromProperties(properties);
					}
					this.writer.writeObject("Upload successfully!");
				}else if(message.equals("get list")){
					HashSet<String> automobileNames = this.buildCarModelOptions.getBuildAuto().getModelNames();
					this.writer.writeObject(automobileNames);
				}else if(message.startsWith("getAuto")){
					String modelName = message.substring(message.indexOf("_") + 1);
					this.writer.writeObject(this.buildCarModelOptions.getBuildAuto().getAutomobile(modelName));
				}else{
					this.writer.writeObject("Bye!");
					break;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }       
    
    public void sendOutput(String strOutput){

    }
    
    public void handleInput(String strInput){
    	
    }       

    public void closeSession(){
    	try {
    		this.writer.close();
    		this.reader.close();
    		socket.close();
    	}catch(IOException e){
    		if(DEBUG){
    			System.err.println("Error closing socket");
    		}
    	}
    }
}

