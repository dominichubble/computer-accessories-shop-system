
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblProducts;
	private DefaultTableModel dtmProducts;
	private JTextField barcodeInput;
	private JTextField brandInput;
	private JTextField colourInput;
	private JTextField stockInput;
	private JTextField costInput;
	private JTextField priceInput;
	private JTextField infoInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {
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
				"Connectivity", "Stock", "Cost", "Price", "Info" });

		JPanel viewProducts = new JPanel();
		tabbedPane.addTab("View Products", null, viewProducts, null);
		viewProducts.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 864, 429);
		viewProducts.add(scrollPane);

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
		viewProducts.add(btnView);

		JPanel addProduct = new JPanel();
		tabbedPane.addTab("Add Product", null, addProduct, null);
		addProduct.setLayout(null);

		barcodeInput = new JTextField();
		barcodeInput.setBounds(77, 11, 120, 50);
		addProduct.add(barcodeInput);
		barcodeInput.setColumns(10);

		JComboBox categoryInput = new JComboBox(ProductCategory.values());
		categoryInput.setBounds(311, 11, 120, 50);
		addProduct.add(categoryInput);

		JComboBox typeInput = new JComboBox(DeviceType.values());
		typeInput.setBounds(538, 11, 120, 50);
		addProduct.add(typeInput);

		brandInput = new JTextField();
		brandInput.setBounds(77, 113, 120, 50);
		addProduct.add(brandInput);
		brandInput.setColumns(10);

		colourInput = new JTextField();
		colourInput.setBounds(757, 11, 120, 50);
		addProduct.add(colourInput);
		colourInput.setColumns(10);

		JComboBox connectivityInput = new JComboBox(ConnectivityType.values());
		connectivityInput.setBounds(311, 113, 120, 50);
		addProduct.add(connectivityInput);

		stockInput = new JTextField();
		stockInput.setBounds(538, 113, 120, 50);
		addProduct.add(stockInput);
		stockInput.setColumns(10);

		costInput = new JTextField();
		costInput.setBounds(757, 113, 120, 50);
		addProduct.add(costInput);
		costInput.setColumns(10);

		priceInput = new JTextField();
		priceInput.setBounds(77, 212, 120, 50);
		addProduct.add(priceInput);
		priceInput.setColumns(10);

		JButton productEnter = new JButton("Submit");
		productEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (barcodeInput.getText().trim().isEmpty() || stockInput.getText().trim().isEmpty()
							|| costInput.getText().trim().isEmpty() || priceInput.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Please fill in all required fields correctly. Numeric fields must contain numbers.",
								"Input Error", JOptionPane.ERROR_MESSAGE);
						return; // Exit the method if any field is empty or incorrectly formatted
					}

					String barcodeStr = barcodeInput.getText().trim();
					if (!barcodeStr.matches("\\d{6}")) {
						JOptionPane.showMessageDialog(null, "Barcode must be exactly 6 digits.", "Input Error",
								JOptionPane.ERROR_MESSAGE);
						return; // Exit if the barcode is not exactly six digits
					}
					int barcode = Integer.parseInt(barcodeStr);

					int stock = Integer.parseInt(stockInput.getText().trim());
					double cost = Double.parseDouble(costInput.getText().trim());
					double price = Double.parseDouble(priceInput.getText().trim());

					String brand = brandInput.getText().trim();
					String colour = colourInput.getText().trim();
					ProductCategory category = (ProductCategory) categoryInput.getSelectedItem();
					DeviceType type = (DeviceType) typeInput.getSelectedItem();
					ConnectivityType connectivity = (ConnectivityType) connectivityInput.getSelectedItem();
					String additionalInfo = infoInput.getText().trim();

					addOrUpdateProduct(barcode, brand, colour, connectivity, stock, cost, price, category, type,
							additionalInfo);

					// Display success message
					JOptionPane.showMessageDialog(null, "Product list successfully updated!", "Success",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter valid numeric values.", "Number Format Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		productEnter.setBounds(539, 208, 338, 58);
		addProduct.add(productEnter);

		JLabel txtBarcode = new JLabel("Barcode");
		txtBarcode.setBounds(21, 26, 46, 14);
		addProduct.add(txtBarcode);

		JLabel txtType = new JLabel("Type");
		txtType.setBounds(494, 26, 46, 14);
		addProduct.add(txtType);

		JLabel txtColour = new JLabel("Colour");
		txtColour.setBounds(701, 29, 46, 14);
		addProduct.add(txtColour);

		JLabel txtStock = new JLabel("Stock");
		txtStock.setBounds(494, 131, 46, 14);
		addProduct.add(txtStock);

		JLabel txtRetailPrice = new JLabel("Price");
		txtRetailPrice.setBounds(21, 230, 46, 14);
		addProduct.add(txtRetailPrice);

		JLabel txtCategory = new JLabel("Category");
		txtCategory.setBounds(233, 29, 46, 14);
		addProduct.add(txtCategory);

		JLabel txtBrand = new JLabel("Brand");
		txtBrand.setBounds(21, 131, 46, 14);
		addProduct.add(txtBrand);

		JLabel txtConnectivity = new JLabel("Connectivity");
		txtConnectivity.setBounds(237, 131, 64, 14);
		addProduct.add(txtConnectivity);

		JLabel txrOriginalCost = new JLabel("Cost");
		txrOriginalCost.setBounds(701, 131, 46, 14);
		addProduct.add(txrOriginalCost);

		infoInput = new JTextField();
		infoInput.setBounds(311, 212, 120, 50);
		addProduct.add(infoInput);
		infoInput.setColumns(10);

		JLabel txtInfo = new JLabel("Info");
		txtInfo.setBounds(268, 230, 46, 14);
		addProduct.add(txtInfo);

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
					product.getOriginalCost(), product.getRetailPrice(), additionalInfo });
		}

	}

	public void addOrUpdateProduct(int barcode, String brand, String color, ConnectivityType connectivity, int quantity,
			double cost, double price, ProductCategory category, DeviceType type, String additionalInfo) {
		Product product;
		if (category == ProductCategory.KEYBOARD) {
			Layout layout = Layout.valueOf(additionalInfo.toUpperCase());
			product = new Keyboard(barcode, brand, color, connectivity, quantity, cost, price, category, type, layout);
		} else if (category == ProductCategory.MOUSE) {
			int buttons = Integer.parseInt(additionalInfo);
			product = new Mouse(barcode, brand, color, connectivity, quantity, cost, price, category, type, buttons);
		} else {
			return; // Optionally handle error or unsupported product types
		}

		StockManager stockManager = new StockManager("data/Stock.txt");
		stockManager.addOrUpdateProduct(product);
	}

}
