
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private DefaultTableModel dtmBasketItems;
	private JTextField barcodeInput;
	private JTextField quantityInput;
	private JTable basketTable;
	private JTextField removeItemBarcorde;
	private JTextField emailInput;
	private JTextField creditCardInput;
	private JTextField securityNumberInput;
	private BasketManager basketManager = new BasketManager();
	List<Product> products = StockReader.readStockFile("data/Stock.txt");
	private JTextField removeQuantityInput;
	private JTextField barcodeSearchInput;
	private JTextField miceInput;
	private ProductManager productManager = new ProductManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame(null);
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
	public CustomerFrame(User user) {
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
				productManager.populateTable(tblProducts, products);
			}
		});
		btnView.setBounds(884, 11, 89, 72);
		panelShop.add(btnView);

		JLabel txtAddProduct = new JLabel("Add Product");
		txtAddProduct.setBounds(10, 250, 73, 14);
		panelShop.add(txtAddProduct);

		barcodeInput = new JTextField();
		barcodeInput.setBounds(110, 276, 86, 20);
		panelShop.add(barcodeInput);
		barcodeInput.setColumns(10);

		JLabel txtAddItemBarcodeInput = new JLabel("Barcode");
		txtAddItemBarcodeInput.setBounds(10, 276, 89, 14);
		panelShop.add(txtAddItemBarcodeInput);

		JPanel panelCheckout = new JPanel();
		tabbedPane.addTab("Checkout", null, panelCheckout, null);
		panelCheckout.setLayout(null);

		JLabel txtTotalValue = new JLabel("0.00");
		txtTotalValue.setBounds(348, 231, 46, 14);
		panelCheckout.add(txtTotalValue);

		JButton btnAddToBasket = new JButton("Add To Basket");
		btnAddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (barcodeInput.getText().isEmpty() || quantityInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int barcode = Integer.parseInt(barcodeInput.getText());
				int quantity = Integer.parseInt(quantityInput.getText());

				products = basketManager.addItem(products, barcode, quantity);
				productManager.populateTable(tblProducts, products);

			}
		});
		btnAddToBasket.setBounds(110, 344, 135, 23);
		panelShop.add(btnAddToBasket);

		quantityInput = new JTextField();
		quantityInput.setBounds(110, 298, 86, 20);
		panelShop.add(quantityInput);
		quantityInput.setColumns(10);

		JLabel txtAddItemQuantity = new JLabel("Quantity");
		txtAddItemQuantity.setBounds(10, 301, 89, 14);
		panelShop.add(txtAddItemQuantity);

		JButton btnViewBasket = new JButton("View Basket");
		btnViewBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basketManager.viewBasket();
			}
		});
		btnViewBasket.setBounds(110, 378, 135, 23);
		panelShop.add(btnViewBasket);

		JButton btnClearBasket = new JButton("Clear Basket");
		btnClearBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basketManager.clearBasket();
				productManager.populateTable(tblProducts, products);
			}
		});
		btnClearBasket.setBounds(110, 412, 135, 23);
		panelShop.add(btnClearBasket);

		JLabel txtFindItem = new JLabel("Find Item");
		txtFindItem.setBounds(260, 250, 96, 14);
		panelShop.add(txtFindItem);

		JLabel txtRemoveBarcode_1 = new JLabel("Barcode");
		txtRemoveBarcode_1.setBounds(260, 276, 96, 14);
		panelShop.add(txtRemoveBarcode_1);

		barcodeSearchInput = new JTextField();
		barcodeSearchInput.setColumns(10);
		barcodeSearchInput.setBounds(360, 276, 86, 20);
		panelShop.add(barcodeSearchInput);

		JButton btnFastSearch = new JButton("Search");
		btnFastSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productManager.fastSearch(tblProducts, barcodeSearchInput.getText());
			}
		});
		btnFastSearch.setBounds(360, 344, 89, 23);
		panelShop.add(btnFastSearch);

		JLabel numberOfMiceButtons = new JLabel("Number Of Mice Buttons");
		numberOfMiceButtons.setBounds(510, 250, 186, 14);
		panelShop.add(numberOfMiceButtons);

		JLabel txtNumber = new JLabel("Number");
		txtNumber.setBounds(510, 276, 46, 14);
		panelShop.add(txtNumber);

		miceInput = new JTextField();
		miceInput.setBounds(610, 276, 86, 20);
		panelShop.add(miceInput);
		miceInput.setColumns(10);

		JButton btnMiceSearch = new JButton("Search");
		btnMiceSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productManager.miceSearch(tblProducts, miceInput.getText());
			}
		});
		btnMiceSearch.setBounds(610, 344, 89, 23);
		panelShop.add(btnMiceSearch);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 446, 209);
		panelCheckout.add(scrollPane_1);

		dtmBasketItems = new DefaultTableModel();
		dtmBasketItems.setColumnIdentifiers(new String[] { "Barcode", "Quantity", "Price" });

		basketTable = new JTable();
		scrollPane_1.setViewportView(basketTable);
		basketTable.setModel(dtmBasketItems);

		JLabel txtRemoveItem = new JLabel("Remove Item");
		txtRemoveItem.setBounds(10, 231, 118, 14);
		panelCheckout.add(txtRemoveItem);

		removeItemBarcorde = new JTextField();
		removeItemBarcorde.setBounds(91, 253, 86, 20);
		panelCheckout.add(removeItemBarcorde);
		removeItemBarcorde.setColumns(10);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removeItemBarcorde.getText().isEmpty() || removeQuantityInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int barcode = Integer.parseInt(removeItemBarcorde.getText());
				int quantity = Integer.parseInt(removeQuantityInput.getText());
				basketManager.removeItem(products, barcode, quantity);
				basketManager.populateTable(basketTable);
				txtTotalValue.setText(Double.toString(basketManager.getTotalPrice()));
			}
		});
		btnRemove.setBounds(91, 309, 89, 23);
		panelCheckout.add(btnRemove);

		JLabel txtPayPal = new JLabel("PayPal");
		txtPayPal.setBounds(780, 191, 46, 14);
		panelCheckout.add(txtPayPal);

		JLabel txtTotal = new JLabel("Total:");
		txtTotal.setBounds(309, 231, 74, 14);
		panelCheckout.add(txtTotal);

		emailInput = new JTextField();
		emailInput.setBounds(838, 219, 86, 20);
		panelCheckout.add(emailInput);
		emailInput.setColumns(10);

		JLabel txtEmail = new JLabel("Email: ");
		txtEmail.setBounds(700, 219, 125, 14);
		panelCheckout.add(txtEmail);

		JLabel txtCreditCard = new JLabel("Credit Card");
		txtCreditCard.setBounds(780, 27, 101, 14);
		panelCheckout.add(txtCreditCard);

		JLabel txtCardNumber = new JLabel("Card Number:");
		txtCardNumber.setBounds(700, 52, 142, 14);
		panelCheckout.add(txtCardNumber);

		creditCardInput = new JTextField();
		creditCardInput.setBounds(838, 52, 86, 20);
		panelCheckout.add(creditCardInput);
		creditCardInput.setColumns(10);

		securityNumberInput = new JTextField();
		securityNumberInput.setBounds(838, 83, 86, 20);
		panelCheckout.add(securityNumberInput);
		securityNumberInput.setColumns(10);

		JLabel txtSecurityNumber = new JLabel("Security Number:");
		txtSecurityNumber.setBounds(700, 83, 142, 14);
		panelCheckout.add(txtSecurityNumber);

		JButton btnPayPalPay = new JButton("Buy Now");
		btnPayPalPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (emailInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!emailInput.getText().contains("@")) {
					JOptionPane.showMessageDialog(null, "Invalid email!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				PaymentMethod payPal = new PayPal(emailInput.getText());
				basketManager.buyItems(payPal, user.getAddress());
				productManager.populateTable(tblProducts, products);
			}
		});
		btnPayPalPay.setBounds(838, 271, 89, 23);
		panelCheckout.add(btnPayPalPay);

		JButton btnCreditCardPay = new JButton("Buy Now");
		btnCreditCardPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (creditCardInput.getText().isEmpty() || securityNumberInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!ErrorHandler.checkIfCreditCardIsValid(creditCardInput.getText())
						|| !ErrorHandler.checkIfSecurityNumberIsValid(securityNumberInput.getText())) {
					return;
				}

				PaymentMethod creditCard = new CreditCard(creditCardInput.getText(), securityNumberInput.getText());
				basketManager.buyItems(creditCard, user.getAddress());
				productManager.populateTable(tblProducts, products);
			}
		});
		btnCreditCardPay.setBounds(838, 135, 89, 23);
		panelCheckout.add(btnCreditCardPay);

		JButton btnViewBskt = new JButton("View Basket");
		btnViewBskt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basketManager.populateTable(basketTable);
				txtTotalValue.setText(Double.toString(basketManager.getTotalPrice()));
			}

		});
		btnViewBskt.setBounds(466, 11, 142, 72);
		panelCheckout.add(btnViewBskt);

		JLabel txtRemoveBarcode = new JLabel("Barcode");
		txtRemoveBarcode.setBounds(10, 256, 74, 14);
		panelCheckout.add(txtRemoveBarcode);

		JLabel txtQuantity = new JLabel("Quantity");
		txtQuantity.setBounds(10, 281, 74, 14);
		panelCheckout.add(txtQuantity);

		removeQuantityInput = new JTextField();
		removeQuantityInput.setBounds(91, 278, 86, 20);
		panelCheckout.add(removeQuantityInput);
		removeQuantityInput.setColumns(10);

	}

}
