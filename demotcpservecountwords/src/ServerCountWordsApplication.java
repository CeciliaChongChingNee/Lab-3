import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class launch the server side application using TCP.
 * The server count and display the number of words in the text.
 * Each connected client will received number of words from the server.
 * 
 * @author Cecilia
 *
 */

public class ServerCountWordsApplication {

	/**
	 * Main entry point to the server side application
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// Launch the server frame
		ServerCountWordsFrame serverFrame = new ServerCountWordsFrame();
		serverFrame.setVisible(true);
		
		// Binding to a port or any other port no you are fancy of
		int portNo = 4228;
		ServerSocket serverSocket = new ServerSocket(portNo);
		
		// Counter to keep track the number of requested connection
		int totalRequest = 0;
		
		// Server needs to be alive forever
		while (true) {
			
			// Message to indicate server is alive
			serverFrame.updateServerStatus(false);
			
			
			// Accept client request for connection
			Socket clientSocket = serverSocket.accept();
			
			// Initialize text and count it in integer
			String text = "I am a text.";
			String[] words = text.split("\\s+");
			int countWords = words.length;
			
			// Create stream to write data on the network
			DataOutputStream outputStream = 
					new DataOutputStream(clientSocket.getOutputStream());
			
			// Send number of words back to the client
			outputStream.writeBytes(Integer.toString(countWords));
			
			// Close the socket
			clientSocket.close();
		
			// Update the request status
			serverFrame.updateRequestStatus(
					"Data sent to the client: The number of words in the text is " + countWords);
			serverFrame.updateRequestStatus("Accepted connection to from the "
					+ "client. Total request = " + ++totalRequest );
			
		}
		
		

	}

}
