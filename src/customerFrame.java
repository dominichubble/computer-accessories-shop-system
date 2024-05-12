
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
	private Basket basket = new Basket();
	List<Product> products = StockReader.readStockFile("data/Stock.txt");
	private JTextField removeQuantityInput;

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

					products = basket.addItem(products, barcode, quantity);
					populateTable();

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
				if (basket.getItems().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Basket is empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String basketItems = "";
				for (List<Product> productList : basket.getItems()) {
					if (!productList.isEmpty()) {
						Product product = productList.get(0);
						basketItems += product.getBarcode() + " - " + product.getBrand() + " - "
								+ product.getRetailPrice() + " - " + productList.size() + " in basket\n";
					}
				}
				JOptionPane.showMessageDialog(null, basketItems, "Basket", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnViewBasket.setBounds(10, 378, 107, 23);
		panelShop.add(btnViewBasket);

		JButton btnClearBasket = new JButton("Clear Basket");
		btnClearBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basket.clearBasket();
				populateTable();
			}
		});
		btnClearBasket.setBounds(10, 412, 107, 23);
		panelShop.add(btnClearBasket);

		JPanel panelCheckout = new JPanel();
		tabbedPane.addTab("Checkout", null, panelCheckout, null);
		panelCheckout.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 446, 209);
		panelCheckout.add(scrollPane_1);

		dtmBasketItems = new DefaultTableModel();
		dtmBasketItems.setColumnIdentifiers(new String[] { "Barcode", "Quantity", "Price" });

		basketTable = new JTable();
		scrollPane_1.setViewportView(basketTable);
		basketTable.setModel(dtmBasketItems);

		JLabel txtRemoveItem = new JLabel("Remove Item");
		txtRemoveItem.setBounds(53, 306, 71, 14);
		panelCheckout.add(txtRemoveItem);

		removeItemBarcorde = new JTextField();
		removeItemBarcorde.setBounds(94, 331, 86, 20);
		panelCheckout.add(removeItemBarcorde);
		removeItemBarcorde.setColumns(10);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Remove item from basket
				try {
					if (removeItemBarcorde.getText().isEmpty() || removeQuantityInput.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					int barcode = Integer.parseInt(removeItemBarcorde.getText());
					int quantity = Integer.parseInt(removeQuantityInput.getText());

					// check if barcode is 6 digits
					if (barcode < 100000 || barcode > 999999) {
						JOptionPane.showMessageDialog(null, "Barcode must be 6 digits!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					// Remove item from basket
					for (Product product : products) {
						if (product.getBarcode() == barcode) {
							List<Product> productList = basket.getItems().stream()
									.filter(itemList -> !itemList.isEmpty() && itemList.get(0).equals(product))
									.findFirst().orElse(null);
							if (productList != null) {
								for (int i = 0; i < quantity; i++) {
									productList.remove(product);
									product.setQuantityInStock(product.getQuantityInStock() + 1);
								}
							}
						}
					}
					javax.swing.JOptionPane.showMessageDialog(null, "Item removed from basket", "Success",
							javax.swing.JOptionPane.INFORMATION_MESSAGE);

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRemove.setBounds(91, 403, 89, 23);
		panelCheckout.add(btnRemove);

		JLabel txtPayPal = new JLabel("PayPal");
		txtPayPal.setBounds(550, 27, 46, 14);
		panelCheckout.add(txtPayPal);

		JLabel txtTotal = new JLabel("Total: ");
		txtTotal.setBounds(309, 231, 46, 14);
		panelCheckout.add(txtTotal);

		emailInput = new JTextField();
		emailInput.setBounds(560, 52, 86, 20);
		panelCheckout.add(emailInput);
		emailInput.setColumns(10);

		JLabel txtEmail = new JLabel("Email: ");
		txtEmail.setBounds(504, 55, 46, 14);
		panelCheckout.add(txtEmail);

		JLabel txtCreditCard = new JLabel("Credit Card");
		txtCreditCard.setBounds(796, 27, 62, 14);
		panelCheckout.add(txtCreditCard);

		JLabel txtCardNumber = new JLabel("Card Number:");
		txtCardNumber.setBounds(757, 55, 71, 14);
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
		txtSecurityNumber.setBounds(742, 86, 86, 14);
		panelCheckout.add(txtSecurityNumber);

		JButton btnPayPalPay = new JButton("Buy Now");
		btnPayPalPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (basket.getItems().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Basket is empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (emailInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				PaymentMethod payPal = new PayPal(emailInput.getText());
				if (!emailInput.getText().contains("@")) {
					JOptionPane.showMessageDialog(null, "Invalid email!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Receipt receipt = payPal.processPayment(basket.getTotalPrice(), user.getAddress());
				JOptionPane.showMessageDialog(null, receipt.getReceiptTxt(), "Receipt",
						JOptionPane.INFORMATION_MESSAGE);
				basket.soldItems();
				populateTable();
			}
		});
		btnPayPalPay.setBounds(560, 135, 89, 23);
		panelCheckout.add(btnPayPalPay);

		JButton btnCreditCardPay = new JButton("Buy Now");
		btnCreditCardPay.setBounds(835, 135, 89, 23);
		panelCheckout.add(btnCreditCardPay);

		JLabel txtTotalValue = new JLabel("");
		txtTotalValue.setBounds(348, 231, 46, 14);
		panelCheckout.add(txtTotalValue);

		JButton btnViewBskt = new JButton("View Basket");
		btnViewBskt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) basketTable.getModel();
				model.setRowCount(0); // Clear existing data

				for (List<Product> productList : basket.getItems()) {
					if (!productList.isEmpty()) {
						Product product = productList.get(0);
						model.addRow(
								new Object[] { product.getBarcode(), productList.size(), product.getRetailPrice() });
					} else {
						JOptionPane.showMessageDialog(null, "Basket is empty!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
// change txtTotalValue to the total price of the basket
				txtTotalValue.setText(Double.toString(basket.getTotalPrice()));
			}

		});
		btnViewBskt.setBounds(10, 227, 89, 23);
		panelCheckout.add(btnViewBskt);

		JLabel txtRemoveBarcode = new JLabel("Barcode");
		txtRemoveBarcode.setBounds(23, 334, 46, 14);
		panelCheckout.add(txtRemoveBarcode);

		JLabel txtQuantity = new JLabel("Quantity");
		txtQuantity.setBounds(23, 375, 46, 14);
		panelCheckout.add(txtQuantity);

		removeQuantityInput = new JTextField();
		removeQuantityInput.setBounds(94, 372, 86, 20);
		panelCheckout.add(removeQuantityInput);
		removeQuantityInput.setColumns(10);

	}

	public void populateTable() {
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
