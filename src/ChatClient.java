import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ChatClient {
	public static void main(String[] args) throws IOException {

		String hostName = "172.17.240.138";
		int portNumber = 3175;
		
		String userName = JOptionPane.showInputDialog("Enter a User Name:");

		try {
			Socket chatSocket = new Socket(hostName, portNumber);

			PrintWriter out = new PrintWriter(chatSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					chatSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(
					System.in));
			
			String fromServer;
			String fromUser;

			while ((fromServer = in.readLine()) != null) {
				
				if (fromServer.equals("Bye."))
					break;

				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println(userName + ": " + fromUser);
					out.println(fromUser);
				}
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}
	}
}
