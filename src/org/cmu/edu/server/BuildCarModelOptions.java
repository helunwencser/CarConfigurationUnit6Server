package org.cmu.edu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import org.cmu.edu.adapter.BuildAuto;
import org.cmu.edu.socket.DefaultSocketClient;
/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/18/2016
 * */

/*
 * build car model options
 * */
public class BuildCarModelOptions implements AutoServer{
	
	/* BuildAuto */
	private BuildAuto buildAuto;
	
	/* server socket */
	private ServerSocket serverSocket; 
	
	public BuildCarModelOptions(){
		this.buildAuto = new BuildAuto();
		try {
			/* listen on port 8888 */
			this.serverSocket = new ServerSocket(8888);
			while(true){
				Socket socket = this.serverSocket.accept();
				new Thread(new DefaultSocketClient(socket, this)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BuildAuto getBuildAuto() {
		return buildAuto;
	}

	public void setBuildAuto(BuildAuto buildAuto) {
		this.buildAuto = buildAuto;
	}

	@Override
	public void buildAutoFromProperties(Properties properties) {
		// TODO Auto-generated method stub
		this.buildAuto.buildAutoFromProperties(properties);
	}
}
