import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class ConcertTicketBookingGUI {
    private JFrame frame;
    private JTextField nameInput, nikInput, ticketInput;
    private JLabel totalPriceLabel;
    private JTable concertTable;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;
    private int selectedRow;

    // HashMap untuk menyimpan username dan password
    private HashMap<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConcertTicketBookingGUI::new);
    }

    public ConcertTicketBookingGUI() {
        showLoginScreen();
    }

    private void showLoginScreen() {
        frame = new JFrame("Login - UMMFest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        // Set Background Gradient
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(72, 61, 139), 0, getHeight(), new Color(123, 104, 238));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        loginPanel.setLayout(new BorderLayout());
        loginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        JLabel headerLabel = new JLabel("UMMFest Ticket Booking", JLabel.CENTER);
        headerLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        headerLabel.setForeground(Color.BLUE);
        headerPanel.add(headerLabel);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        usernameLabel.setForeground(Color.BLACK);
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.BLACK);
        JPasswordField passwordField = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Roboto", Font.BOLD, 16));
        loginButton.setBackground(new Color(50, 205, 50));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                frame.dispose();  // Close the login frame
                createMainPanel();  // Show the main booking panel
            } else {
                JOptionPane.showMessageDialog(frame, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Roboto", Font.BOLD, 16));
        registerButton.setBackground(new Color(70, 130, 180));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(e -> showRegisterScreen());

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        loginPanel.add(headerPanel, BorderLayout.NORTH);
        loginPanel.add(formPanel, BorderLayout.CENTER);

        frame.add(loginPanel);
        frame.setVisible(true);
    }

    private void showRegisterScreen() {
        JFrame registerFrame = new JFrame("Register - UMMFest");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(400, 300);
        registerFrame.setLocationRelativeTo(null);

        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        registerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        registerPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        registerPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        registerPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        registerPanel.add(passwordField, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        registerPanel.add(confirmPasswordLabel, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        registerPanel.add(confirmPasswordField, gbc);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(registerFrame, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(registerFrame, "Password tidak cocok!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(registerFrame, "Username sudah digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                userDatabase.put(username, password);
                JOptionPane.showMessageDialog(registerFrame, "Registrasi berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                registerFrame.dispose();
            }
        });
        registerPanel.add(registerButton, gbc);

        registerFrame.add(registerPanel);
        registerFrame.setVisible(true);
    }

    private void createMainPanel() {
        frame = new JFrame("UMMFest - Pemesanan Tiket Konser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel("Selamat Datang di Pemesanan Tiket Konser UMMFest!", JLabel.CENTER);
        headerLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        headerLabel.setForeground(new Color(72, 61, 139));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        String[] columns = {"Konser", "Harga (Rp)", "Tiket Tersedia", "Detail"};
        Object[][] data = {
                {"Single Ticket", 500000, 80, "Paket satu tiket konser."},
                {"Date Packet", 850000, 40, "Paket untuk dua tiket."},
                {"Double Date Packet", 150000, 20, "Paket empat tiket."}
        };

        tableModel = new DefaultTableModel(data, columns);
        concertTable = new JTable(tableModel);

        // Panggil metode untuk styling tabel
        styleConcertTable();

        JScrollPane scrollPane = new JScrollPane(concertTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));  // Change GridLayout rows to 6 for extra row
        JLabel nameLabel = new JLabel("Nama:");
        nameInput = new JTextField(20);

        JLabel emailLabel = new JLabel("NIK:");
        nikInput = new JTextField(18);

        JLabel ticketLabel = new JLabel("Jumlah Tiket:");
        ticketInput = new JTextField(5);

        totalPriceLabel = new JLabel("Total Harga: Rp0");
        totalPriceLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        JButton submitButton = new JButton("Submit Pemesanan");
        submitButton.setBackground(new Color(50, 205, 50));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> submitOrder());

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> handleLogout());

        ticketInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameInput);
        inputPanel.add(emailLabel);
        inputPanel.add(nikInput);
        inputPanel.add(ticketLabel);
        inputPanel.add(ticketInput);

        inputPanel.add(new JLabel(""));  // Empty label to occupy space
        inputPanel.add(totalPriceLabel);  // Show total above logout

        inputPanel.add(submitButton);
        inputPanel.add(logoutButton);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);  // Make the frame visible after creating the main panel
    }


    private void styleConcertTable() {
        // Mengubah warna latar belakang untuk header
        concertTable.getTableHeader().setBackground(new Color(72, 61, 139));
        concertTable.getTableHeader().setForeground(Color.WHITE);

        // Mengubah warna latar belakang untuk kolom tertentu (misalnya Konser, Harga, Tiket Tersedia, Detail)
        for (int i = 0; i < concertTable.getColumnCount(); i++) {
            concertTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    // Panggil render default
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Ganti warna latar belakang atau teks untuk setiap kolom
                    if (column == 0) { // Kolom "Konser"
                        comp.setBackground(new Color(230, 230, 250)); // Lavender Light
                        comp.setForeground(Color.BLACK);
                    } else if (column == 1) { // Kolom "Harga"
                        comp.setBackground(new Color(255, 239, 184)); // Yellowish
                        comp.setForeground(Color.BLACK);
                    } else if (column == 2) { // Kolom "Tiket Tersedia"
                        comp.setBackground(new Color(255, 240, 245)); // Light Pink
                        comp.setForeground(Color.BLACK);
                    } else if (column == 3) { // Kolom "Detail"
                        comp.setBackground(new Color(255, 250, 205)); // Lemon Chiffon
                        comp.setForeground(Color.BLACK);
                    }

                    return comp;
                }
            });
        }
    }

    private void calculateTotalPrice() {
        int selectedRow = concertTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Pilih konser terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedTicketType = (String) concertTable.getValueAt(selectedRow, 0);
        int ticketPrice = (int) concertTable.getValueAt(selectedRow, 1);
        int ticketQuantity;
        try {
            ticketQuantity = Integer.parseInt(ticketInput.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Jumlah tiket tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int totalPrice = ticketPrice * ticketQuantity;
        totalPriceLabel.setText("Total Harga: Rp" + totalPrice);
    }
    private void submitOrder() {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Silakan pilih konser yang ingin dipesan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = nameInput.getText().trim();
        String nik = nikInput.getText().trim();
        String ticketCountText = ticketInput.getText().trim();

        if (name.isEmpty() || nik.isEmpty() || ticketCountText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int ticketCount = Integer.parseInt(ticketCountText);
            if (ticketCount <= 0) {
                JOptionPane.showMessageDialog(frame, "Jumlah tiket harus lebih dari 0!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int availableTickets = (int) tableModel.getValueAt(selectedRow, 2);
            if (ticketCount > availableTickets) {
                JOptionPane.showMessageDialog(frame, "Jumlah tiket melebihi tiket yang tersedia!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int pricePerTicket = (int) tableModel.getValueAt(selectedRow, 1);
            int totalPrice = pricePerTicket * ticketCount;

            tableModel.setValueAt(availableTickets - ticketCount, selectedRow, 2);

            JOptionPane.showMessageDialog(frame, "Pemesanan diproses ! Total harga: Rp" + totalPrice, "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // After successful order, show payment options
            showPaymentOptions();  // Add this line to show the payment options

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Jumlah tiket harus berupa angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void showPaymentOptions() {
        String[] paymentMethods = {"Transfer Bank", "E-Wallet", "Kartu Kredit"};
        String method = (String) JOptionPane.showInputDialog(
                frame,
                "Pilih metode pembayaran:",
                "Metode Pembayaran",
                JOptionPane.QUESTION_MESSAGE,
                null,
                paymentMethods,
                paymentMethods[0]
        );

        if (method == null) {
            return;
        }

        String paymentInfo = "";
        if (method.equals("Transfer Bank")) {
            paymentInfo = "Nomor Rekening: 123-456-789 (Bank A)";
        } else if (method.equals("E-Wallet")) {
            paymentInfo = "E-Wallet: 0812-3456-7890 (DANA)";
        } else if (method.equals("Kartu Kredit")) {
            paymentInfo = "Kartu Kredit: Gunakan kartu Visa atau MasterCard.";
        }

        int response = JOptionPane.showConfirmDialog(
                frame,
                paymentInfo + "\n\nKlik OK jika sudah melakukan pembayaran dan ingin mengunggah bukti.",
                "Pembayaran",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (response == JOptionPane.OK_OPTION) {
            uploadPaymentReceipt();
        }
    }

    private void uploadPaymentReceipt() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Bukti Pembayaran");

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                JOptionPane.showMessageDialog(frame,
                        "Bukti pembayaran Anda berhasil diunggah!\nFile: " + file.getName(),
                        "Berhasil Mengunggah Bukti Pembayaran",
                        JOptionPane.INFORMATION_MESSAGE);
                showReceipt(file);
            }
        }
    }

    private void showReceipt(File paymentFile) {
        String name = nameInput.getText().trim();
        String email = nikInput.getText().trim();
        int ticketsToBuy = Integer.parseInt(ticketInput.getText().trim());
        int ticketPrice = (int) tableModel.getValueAt(selectedRow, 1);
        int totalPrice = ticketPrice * ticketsToBuy;

        String message = String.format(
                "\uD83C\uDFAC Struk Pemesanan \uD83C\uDFAC\n" +
                        "Selamat Datang di UMMFest\n" +
                        "Nama: %s\n" +
                        "NIK : %s\n" +
                        "Konser: %s\n" +
                        "Jumlah Tiket: %d\n" +
                        "Total Harga: Rp%d\n" +
                        "Harap Simpan Bukti Pemesanan\n" +
                        "Terima kasih telah memesan!\n\n" +
                        "Bukti Pembayaran: %s",
                name, email, tableModel.getValueAt(selectedRow, 0), ticketsToBuy, totalPrice, paymentFile.getName()
        );

        JOptionPane.showMessageDialog(frame, message, "Struk Pemesanan", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleLogout() {
        int response = JOptionPane.showConfirmDialog(
                frame,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            frame.dispose();
            showLoginScreen();
        }
    }
}