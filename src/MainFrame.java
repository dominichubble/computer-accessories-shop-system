import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idInput;
    private JTextField usernameInput;
    private JLabel txtRole;
    private JComboBox<Role> roles;  // Ensure generic type Role is used
    private JLabel loginAttempt;    // Label to display login attempt messages

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
                    int id = Integer.parseInt(idInput.getText().trim());
                    String username = usernameInput.getText().trim();
                    if (username.isEmpty()) {
                        loginAttempt.setText("Username cannot be empty.");
                        return;
                    }
                    Role role = (Role) roles.getSelectedItem();
                    Boolean isUser = User.checkUser(id, username, role);
                    if (!isUser) {
                        loginAttempt.setText("Login failed. Please check your credentials.");
                        return;
                    }
                    // Assuming checkUser() only returns true if valid
                    if (role == Role.customer) {
                        customerFrame frame = new customerFrame();
                        frame.setVisible(true);
                        dispose();
                    } else if (role == Role.admin) {
                        adminFrame frame = new adminFrame();
                        frame.setVisible(true);
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    loginAttempt.setText("Please enter a valid ID.");
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
