import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {

	private int id;
	private String username;
	private String name;
	private int houseNumber;
	private String postcode;
	private String city;
	private Role role;

	/**
	 * Constructs a new User with the specified attributes.
	 *
	 * @param id          Unique 6-digit number that identifies a user.
	 * @param username    Username of the user.
	 * @param name        Full name of the user.
	 * @param houseNumber House number of the user's address.
	 * @param postcode    Postcode of the user's address.
	 * @param city        City of the user's address.
	 * @param role        Role of the user (e.g., ADMIN, CUSTOMER).
	 */

	public User(int id, String username, String name, int houseNumber, String postcode, String city, Role role) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
		this.role = role;
	}

	public static Boolean checkUser(int id, String username, Role role) {
		try {
			File file = new File("data/UserAccounts.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				System.out.println("Reading line: " + line); // Debug: Output the line being read

				String[] attributes = line.split(",");
				for (int i = 0; i < attributes.length; i++) {
					attributes[i] = attributes[i].trim();
				}

				System.out.println("Parsed ID: " + attributes[0] + " Expected ID: " + id); // Debug: Check parsed vs
																							// expected ID
				System.out.println("Parsed Username: " + attributes[1] + " Expected Username: " + username); // Debug:
																												// Username
				System.out.println("Parsed Role: " + attributes[6] + " Expected Role: " + role); // Debug: Role

				if (attributes[0].equals(String.valueOf(id)) && attributes[1].equalsIgnoreCase(username)
						&& attributes[6].equalsIgnoreCase(role.toString())) {
					System.out.println("Match found."); // Debug: Successful match
					return true;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		System.out.println("No matching user found."); // Debug: No match found
		return false;
	}

}
