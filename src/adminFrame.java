

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
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class adminFrame extends JFrame {

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
		scrollPane.setBounds(10, 11, 279, 153);
		viewProducts.add(scrollPane);
		
		tblProducts = new JTable();
		scrollPane.setViewportView(tblProducts);

		dtmProducts = new DefaultTableModel();
		dtmProducts.setColumnIdentifiers(new String[]{"Barcode", "Category", "Type", "Brand", "Colour", "Connectivity", "Stock", "Cost", "Price", "Info"});
		tblProducts.setModel(dtmProducts);

		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateTable();
			}
		});
		btnView.setBounds(299, 11, 89, 23);
		viewProducts.add(btnView);

		JPanel addProduct = new JPanel();
		tabbedPane.addTab("Add Product", null, addProduct, null);
		addProduct.setLayout(null);
		
		barcodeInput = new JTextField();
		barcodeInput.setBounds(88, 11, 86, 20);
		addProduct.add(barcodeInput);
		barcodeInput.setColumns(10);
		
		JComboBox categoryInput = new JComboBox(ProductCategory.values());
		categoryInput.setBounds(233, 10, 86, 22);
		addProduct.add(categoryInput);
		
		JComboBox typeInput = new JComboBox(DeviceType.values());
		typeInput.setBounds(88, 42, 86, 22);
		addProduct.add(typeInput);
		
		brandInput = new JTextField();
		brandInput.setBounds(233, 43, 86, 20);
		addProduct.add(brandInput);
		brandInput.setColumns(10);
		
		colourInput = new JTextField();
		colourInput.setBounds(88, 75, 86, 20);
		addProduct.add(colourInput);
		colourInput.setColumns(10);
		
		JComboBox connectivityInput = new JComboBox(ConnectivityType.values());
		connectivityInput.setBounds(233, 74, 86, 22);
		addProduct.add(connectivityInput);
		
		stockInput = new JTextField();
		stockInput.setBounds(88, 106, 86, 20);
		addProduct.add(stockInput);
		stockInput.setColumns(10);
		
		costInput = new JTextField();
		costInput.setBounds(233, 107, 86, 20);
		addProduct.add(costInput);
		costInput.setColumns(10);
		
		priceInput = new JTextField();
		priceInput.setBounds(88, 137, 86, 20);
		addProduct.add(priceInput);
		priceInput.setColumns(10);
		
		JButton productEnter = new JButton("Submit");
		productEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int barcode = Integer.parseInt(barcodeInput.getText());
				ProductCategory category = (ProductCategory) categoryInput.getSelectedItem();
				DeviceType type = (DeviceType) typeInput.getSelectedItem();
				String brand = brandInput.getText();
				String colour = colourInput.getText();
				ConnectivityType connectivity = (ConnectivityType) connectivityInput.getSelectedItem();
				int stock = Integer.parseInt(stockInput.getText());
				double cost = Double.parseDouble(costInput.getText());
				double price = Double.parseDouble(priceInput.getText());
				String additionalInfo = infoInput.getText();
				addOrUpdateProduct(barcode, brand, colour, connectivity, stock, cost, price, category, type, additionalInfo);

			}
		});
		productEnter.setBounds(185, 136, 35, 23);
		addProduct.add(productEnter);
		
		JLabel txtBarcode = new JLabel("Barcode");
		txtBarcode.setBounds(10, 14, 46, 14);
		addProduct.add(txtBarcode);
		
		JLabel txtType = new JLabel("Type");
		txtType.setBounds(10, 46, 46, 14);
		addProduct.add(txtType);
		
		JLabel txtColour = new JLabel("Colour");
		txtColour.setBounds(10, 78, 46, 14);
		addProduct.add(txtColour);
		
		JLabel txtStock = new JLabel("Stock");
		txtStock.setBounds(10, 109, 46, 14);
		addProduct.add(txtStock);
		
		JLabel txtRetailPrice = new JLabel("Price");
		txtRetailPrice.setBounds(10, 140, 46, 14);
		addProduct.add(txtRetailPrice);
		
		JLabel txtCategory = new JLabel("Category");
		txtCategory.setBounds(342, 14, 46, 14);
		addProduct.add(txtCategory);
		
		JLabel txtBrand = new JLabel("Brand");
		txtBrand.setBounds(342, 46, 46, 14);
		addProduct.add(txtBrand);
		
		JLabel txtConnectivity = new JLabel("Connectivity");
		txtConnectivity.setBounds(342, 78, 46, 14);
		addProduct.add(txtConnectivity);
		
		JLabel txrOriginalCost = new JLabel("Cost");
		txrOriginalCost.setBounds(342, 109, 46, 14);
		addProduct.add(txrOriginalCost);
		
		infoInput = new JTextField();
		infoInput.setBounds(233, 137, 86, 20);
		addProduct.add(infoInput);
		infoInput.setColumns(10);
		
		JLabel txtInfo = new JLabel("Info");
		txtInfo.setBounds(342, 140, 46, 14);
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



	        model.addRow(new Object[]{
	            product.getBarcode(),
				product.getCategory(),
	            deviceType,
				product.getBrand(),
	            product.getColor(),
	            product.getConnectivity(),
	            product.getQuantityInStock(),
	            product.getOriginalCost(),
	            product.getRetailPrice(),
	            additionalInfo
	        });


	    }
	}
	public void addOrUpdateProduct(int barcode, String brand, String color, ConnectivityType connectivity,
                               int quantity, double cost, double price, ProductCategory category,
                               DeviceType type, String additionalInfo) {
    Product product;
    if (category == ProductCategory.KEYBOARD) {
        Layout layout = Layout.valueOf(additionalInfo.toUpperCase());
        product = new Keyboard(barcode, brand, color, connectivity, quantity, cost, price,
                               category, type, layout);
    } else if (category == ProductCategory.MOUSE) {
        int buttons = Integer.parseInt(additionalInfo);
        product = new Mouse(barcode, brand, color, connectivity, quantity, cost, price,
                            category, type, buttons);
    } else {
        return; // Optionally handle error or unsupported product types
    }
    StockManager stockManager = new StockManager("data/Stock.txt");
    stockManager.addOrUpdateProduct(product);
}

}
