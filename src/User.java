import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class User {

	private static String idStr;
	private static String username;
	private static String name;
	private static Role role;
	private static Address address;

	/**
	 * Constructs a new User with the specified attributes.
	 *
	 * @param id       Unique 6-digit number that identifies a user.
	 * @param username Username of the user.
	 * @param role     Role of the user (e.g., ADMIN, CUSTOMER).
	 */

	public User(String idStr, String username, Role role) {
		User.idStr = idStr;
		User.username = username;
		User.role = role;
		User.name = getName();
		User.address = getAddress();
	}

	public static Boolean findUser(String idStr, String username, Role role) {
		try {
			File file = new File("data/UserAccounts.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				String[] attributes = line.split(",");
				for (int i = 0; i < attributes.length; i++) {
					attributes[i] = attributes[i].trim();
				}

				if (attributes[0].equals(idStr) && attributes[1].equalsIgnoreCase(username)
						&& attributes[6].equalsIgnoreCase(role.toString())) {
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

		return false;
	}

	public static Boolean login(User user) {
		// Check for empty inputs
		if (idStr.isEmpty() || username.isEmpty()) {
			JOptionPane.showMessageDialog(null, "ID and Username cannot be empty.", "Login Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		Boolean isUser = User.findUser(idStr, username, role);
		if (!isUser) {
			JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.", "Login Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JOptionPane.showMessageDialog(null, "Login successful.", "Login Success", JOptionPane.INFORMATION_MESSAGE);
		if (role == Role.customer) {
			CustomerFrame frame = new CustomerFrame(user);
			frame.setVisible(true);
		} else if (role == Role.admin) {
			AdminFrame frame = new AdminFrame();
			frame.setVisible(true);
		}
		return true;
	}

	public static String getName() {
		try {
			File file = new File("data/UserAccounts.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				String[] attributes = line.split(",");
				for (int i = 0; i < attributes.length; i++) {
					attributes[i] = attributes[i].trim();
				}

				if (attributes[0].equals(idStr) && attributes[1].equalsIgnoreCase(username)
						&& attributes[6].equalsIgnoreCase(role.toString())) {
					scanner.close();
					return attributes[2];
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
		return null;
	}

	public Address getAddress() {
		try {
			File file = new File("data/UserAccounts.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				String[] attributes = line.split(",");
				for (int i = 0; i < attributes.length; i++) {
					attributes[i] = attributes[i].trim();
				}

				if (attributes[0].equals(idStr) && attributes[1].equalsIgnoreCase(username)
						&& attributes[6].equalsIgnoreCase(role.toString())) {
					scanner.close();
					return new Address(Integer.parseInt(attributes[3]), attributes[4], attributes[5]);
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
		return null;
	}

}
