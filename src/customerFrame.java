
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblProducts;
	private DefaultTableModel dtmProducts;
	private JTextField barcodeInput;
	private JTextField quantityInput;
	private List<BasketItem> basket = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 576);
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
		logout.setBounds(894, 23, 89, 23);
		contentPane.add(logout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 988, 479);
		contentPane.add(tabbedPane);

		dtmProducts = new DefaultTableModel();
		dtmProducts.setColumnIdentifiers(new String[] { "Barcode", "Category", "Type", "Brand", "Colour",
				"Connectivity", "Stock", "Price", "Info" });

		JPanel panelShop = new JPanel();
		tabbedPane.addTab("Shop", null, panelShop, null);
		panelShop.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 864, 229);
		panelShop.add(scrollPane);

		tblProducts = new JTable();
		scrollPane.setViewportView(tblProducts);
		tblProducts.setModel(dtmProducts);

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateTable();
			}
		});
		btnView.setBounds(884, 11, 89, 72);
		panelShop.add(btnView);

		JLabel txtAddProduct = new JLabel("Add Product");
		txtAddProduct.setBounds(20, 251, 73, 14);
		panelShop.add(txtAddProduct);

		barcodeInput = new JTextField();
		barcodeInput.setBounds(10, 276, 86, 20);
		panelShop.add(barcodeInput);
		barcodeInput.setColumns(10);

		JLabel txtInsertBarcode = new JLabel("(insert barcode)");
		txtInsertBarcode.setBounds(106, 279, 89, 14);
		panelShop.add(txtInsertBarcode);

		JButton btnAddToBasket = new JButton("Add To Basket");
		btnAddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (barcodeInput.getText().isEmpty() || quantityInput.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					int barcode = Integer.parseInt(barcodeInput.getText());
					int quantity = Integer.parseInt(quantityInput.getText());
					double price = StockReader.getPrice(barcode);
					if (!StockReader.checkIfBarcodeExists(barcode)) {
						JOptionPane.showMessageDialog(null, "Barcode does not exist!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (BasketItem.checkIfInStock(barcode, quantity)) {
						BasketItem basketItem = new BasketItem(barcode, quantity, price);
						basket.add(basketItem);
						JOptionPane.showMessageDialog(null, "Added to basket!", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Not enough in stock!", "Error", JOptionPane.ERROR_MESSAGE);
					}

					// check if barcode is in basket
					for (BasketItem item : basket) {
						if (item.getBarcode() == barcode) {
							item.setQuantity(item.getQuantity() + quantity);
							JOptionPane.showMessageDialog(null, "Added to basket!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
					if (quantity <= 0) {
						JOptionPane.showMessageDialog(null, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddToBasket.setBounds(10, 344, 107, 23);
		panelShop.add(btnAddToBasket);

		quantityInput = new JTextField();
		quantityInput.setBounds(10, 307, 86, 20);
		panelShop.add(quantityInput);
		quantityInput.setColumns(10);

		JLabel txtInsertQuantity = new JLabel("(insert quantity)");
		txtInsertQuantity.setBounds(106, 310, 89, 14);
		panelShop.add(txtInsertQuantity);

		JButton btnViewBasket = new JButton("View Basket");
		btnViewBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (basket.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Basket is empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					String basketString = "";
					for (BasketItem item : basket) {
						basketString += item.toString() + "\n";
					}
					;
					JOptionPane.showMessageDialog(null, basketString, "Basket", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnViewBasket.setBounds(10, 378, 107, 23);
		panelShop.add(btnViewBasket);

		JButton btnClearBasket = new JButton("Clear Basket");
		btnClearBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check if basket is empty
				if (basket.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Basket is already empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				basket.clear();
				JOptionPane.showMessageDialog(null, "Basket cleared!", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnClearBasket.setBounds(10, 412, 107, 23);
		panelShop.add(btnClearBasket);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
	}

	public void populateTable() {
		List<Product> products = StockReader.readStockFile("data/Stock.txt");
		DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
		model.setRowCount(0); // Clear existing data

		for (Product product : products) {

			String deviceType = "";
			String additionalInfo = "";

			if (product instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) product;
				deviceType = keyboard.getDeviceType().toString();
				additionalInfo = keyboard.getAdditionalInfo().toString();
			} else if (product instanceof Mouse) {
				Mouse mouse = (Mouse) product;
				deviceType = mouse.getDeviceType().toString();
				additionalInfo = Integer.toString(mouse.getAdditionalInfo());
			}

			model.addRow(new Object[] { product.getBarcode(), product.getCategory(), deviceType, product.getBrand(),
					product.getColor(), product.getConnectivity(), product.getQuantityInStock(),
					product.getRetailPrice(), additionalInfo });
		}

	}
}
