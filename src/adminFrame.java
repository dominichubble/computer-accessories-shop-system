

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class adminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblProducts;
	private DefaultTableModel dtmProducts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminFrame frame = new adminFrame();
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
	public adminFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			}
		});
		logout.setBounds(324, 11, 89, 23);
		contentPane.add(logout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 47, 403, 203);
		contentPane.add(tabbedPane);
		
		JPanel viewProducts = new JPanel();
		tabbedPane.addTab("View Products", null, viewProducts, null);
		viewProducts.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 276, 153);
		viewProducts.add(scrollPane);
		
		tblProducts = new JTable();
		scrollPane.setViewportView(tblProducts);

		DefaultTableModel dtmProducts = new DefaultTableModel();
		dtmProducts.setColumnIdentifiers(new String[] {"barcode", "product category", "device type", "brand", "colour", "connectivity", "quantity in stock", "original cost", "retail price", "additional information"});
		tblProducts.setModel(dtmProducts);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmProducts.setRowCount(0);
				for (Product product : ) {
					dtmProducts.addRow(new Object[] {product.getBarcode(), product.getCategory(), product.getDeviceType(), product.getBrand(), product.getColor(), product.getConnectivity(), product.getQuantityInStock(), product.getOriginalCost(), product.getRetailPrice(), product.getAdditionalInformation()});
				}
			}
		});
		btnView.setBounds(299, 11, 89, 23);
		viewProducts.add(btnView);

		JPanel addProduct = new JPanel();
		tabbedPane.addTab("Add Product", null, addProduct, null);

		
	}
}
