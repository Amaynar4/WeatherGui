package weathergui;

import java.io.*;

public class UserInformation {
	private String userLocation;
	private String userUnit;

	final String FILENAME = "config.txt";

	public UserInformation() {

	}

	public void setUnit( String unit ) {
		userUnit = unit;
	}

	public String getUnit() {
		return userUnit;
	}

	public void setLocation( String location ) {
		userLocation = location;
	}

	public String getLocation() {
		return userLocation;
	}

	public void saveState() {
		File existing = new File( FILENAME ); // Delete the file if it exists already
		existing.delete();

		try {
			PrintWriter out = new PrintWriter( FILENAME ); // Create the PrintWriter
			out.println( getUnit() ); // Write unit and location
			out.println( getLocation() );
			out.close(); // Close the file stream
		} catch( FileNotFoundException e ) {
			// Do nothing
		} catch( SecurityException e ) {
			System.out.println( "Error: File access denied" );
		}
	}

	public void loadState() {
		try {
			BufferedReader in = new BufferedReader( new FileReader( FILENAME ) ); // Open the file

			String inLocation = in.readLine(); // Read the user data from the file
			String inUnit = in.readLine();

			setUnit( inUnit ); // Set the class' data
			setLocation( inLocation );

			in.close(); // Close the file stream
		} catch( FileNotFoundException e ) {
			// Do nothing
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}