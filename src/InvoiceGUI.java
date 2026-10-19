import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class InvoiceGUI extends JFrame {
    private JTextField nameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;

    private JTextField productField;
    private JTextField productPriceField;
    private JTextField productQtyField;

    private JTextArea invoiceArea;
    private Invoice invoice;

    public InvoiceGUI() {
        setTitle("Invoice");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        JLabel title = new JLabel("INVOICE",  SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        nameField =  new JTextField();
        streetField =  new JTextField();
        cityField =  new JTextField();
        stateField =  new JTextField();
        zipCodeField =  new JTextField();

        productField =  new JTextField();
        productPriceField =  new JTextField();
        productQtyField =  new JTextField();

        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Street Address:"));
        inputPanel.add(streetField);

        inputPanel.add(new JLabel("City:"));
        inputPanel.add(cityField);

        inputPanel.add(new JLabel("State:"));
        inputPanel.add(stateField);

        inputPanel.add(new JLabel("Zip Code:"));
        inputPanel.add(zipCodeField);

        inputPanel.add(new JLabel("Product:"));
        inputPanel.add(productField);

        inputPanel.add(new JLabel("Product Price:"));
        inputPanel.add(productPriceField);

        inputPanel.add(new JLabel("Product Quantity:"));
        inputPanel.add(productQtyField);

        JButton addButton = new JButton("Add Item");
        inputPanel.add(addButton);

        JButton clearButton = new JButton("New Invoice");
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.CENTER);

        invoiceArea = new JTextArea();
        invoiceArea.setEditable(false);
        invoiceArea.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(invoiceArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Invoice Details"));

        add(scrollPane, BorderLayout.SOUTH);
        addButton.addActionListener(e -> addItem());
        clearButton.addActionListener(e -> clearInvoice());
    }

    private void addItem() {
        try{
            String customerName = nameField.getText();
            String street = streetField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip = zipCodeField.getText();

            String productName = productField.getText();

            if (customerName.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty() || productName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
                return;
            }

            double price = Double.parseDouble(productPriceField.getText());
            int quantity = Integer.parseInt(productQtyField.getText());

            if (!Double.isFinite(price) || price < 0 || quantity < 0) {
                JOptionPane.showMessageDialog(null, "Please enter a valid price and quantity");
                return;
            }

            if (invoice == null){
                Address address = new Address(customerName, street, city, state, zip);
                invoice = new Invoice(address);
            }

            Product product = new Product(productName, price);
            LineItem item = new LineItem(product, quantity);

            invoice.addLineItem(item);

            displayInvoice();

            productField.setText("");
            productQtyField.setText("");
            productPriceField.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void displayInvoice(){
        StringBuilder output = new  StringBuilder();

        output.append("                         INVOICE\n");
        output.append("--------------------------------------------------\n");
        output.append("BILL TO:\n");
        output.append(invoice.getAddress().toString());
        output.append("\n\n");

        output.append(String.format(Locale.US,
                "%-20s %8s %8s %10s%n",
                "Product", "Price", "Qty", "Total"));

        output.append("--------------------------------------------------\n");

        for (LineItem item : invoice.getLineItems()) {
            output.append(String.format(Locale.US,
                    "%-20s %8.2f %8d %10.2f%n",
                    item.getProduct().getItemName(),
                    item.getProduct().getItemPrice(),
                    item.getQuantity(),
                    item.getLineTotal()));
        }

        output.append("--------------------------------------------------\n");
        output.append(String.format(Locale.US,
                "TOTAL AMOUNT DUE: $%.2f%n", invoice.getTotal()));

        invoiceArea.setText(output.toString());
    }

    private void clearInvoice(){
        invoice = null;
        nameField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipCodeField.setText("");

        productField.setText("");
        productQtyField.setText("");
        productPriceField.setText("");

        invoiceArea.setText("");
    }

    public static void main ( String[] args){
        SwingUtilities.invokeLater(() -> {InvoiceGUI gui = new InvoiceGUI();
            gui.setVisible(true);
        });
    }
}
