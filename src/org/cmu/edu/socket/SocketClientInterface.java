package org.cmu.edu.socket;
/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/18/2016
 * */
public interface SocketClientInterface {
	
	public boolean openConnection();
	
    public void handleSession();
    
    public void closeSession();

}
