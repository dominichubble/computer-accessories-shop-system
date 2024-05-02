import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idInput;
	private JTextField usernameInput;
	private JLabel txtRole;
	private JComboBox<Role> roles; // Ensure generic type Role is used
	private JLabel loginAttempt; // Label to display login attempt messages

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		idInput = new JTextField();
		idInput.setBounds(172, 24, 86, 20);
		contentPane.add(idInput);
		idInput.setColumns(10);

		usernameInput = new JTextField();
		usernameInput.setBounds(172, 55, 86, 20);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idStr = idInput.getText().trim();
					String username = usernameInput.getText().trim();

					// Check for empty inputs
					if (idStr.isEmpty() || username.isEmpty()) {
						JOptionPane.showMessageDialog(MainFrame.this, "ID and Username cannot be empty.", "Login Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					int id = Integer.parseInt(idStr); // This might throw NumberFormatException if not a valid integer
					Role role = (Role) roles.getSelectedItem();

					Boolean isUser = User.checkUser(id, username, role);
					if (!isUser) {
						JOptionPane.showMessageDialog(MainFrame.this, "Login failed. Please check your credentials.",
								"Login Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Successful login, navigate based on role
					if (role == Role.customer) {
						CustomerFrame frame = new CustomerFrame();
						frame.setVisible(true);
						dispose();
					} else if (role == Role.admin) {
						AdminFrame frame = new AdminFrame();
						frame.setVisible(true);
						dispose();
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please enter a valid ID.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(172, 146, 89, 23);
		contentPane.add(btnLogin);

		JLabel txtID = new JLabel("ID");
		txtID.setBounds(88, 27, 74, 14);
		contentPane.add(txtID);

		JLabel txtUsername = new JLabel("Username");
		txtUsername.setBounds(88, 58, 74, 14);
		contentPane.add(txtUsername);

		txtRole = new JLabel("Role");
		txtRole.setBounds(88, 89, 74, 14);
		contentPane.add(txtRole);

		roles = new JComboBox<>(Role.values());
		roles.setBounds(172, 86, 86, 22);
		contentPane.add(roles);

		loginAttempt = new JLabel(""); // Initialize the label for error messages
		loginAttempt.setBounds(88, 180, 300, 14);
		contentPane.add(loginAttempt);
	}
}
