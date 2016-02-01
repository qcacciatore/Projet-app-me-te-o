package pack;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class LoginFrame extends JFrame {
	
	private static int mWidth;
	private static int mHeight;
	
	private JButton loginButton = new JButton("log in");
	private JButton cancelButton = new JButton("cancel");
	private JTextField logintextField = new JTextField(20);
	private JTextField passwordTextField = new JTextField(20);
	private JLabel loginExplanationLabel = new JLabel("entrer vos identifiants");
	
	public LoginFrame(int width, int height) // Constructor
	{
		mWidth = width;
		mHeight = height;
		setSize(width, height); // Set frame size
		setTitle("Weather App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// frame layout is a grid layout
		this.setLayout(new GridLayout(3, 2)); // layout will have 3 rows and 2 columns
		
		// Now place elements on this layout.
		this.getContentPane().add(new JLabel("login")); // place a label at position (0,0)
		this.getContentPane().add(logintextField); // place a login text field at position (0,1)
		this.getContentPane().add(new JLabel("password")); // place a label at position (1,0)
		this.getContentPane().add(passwordTextField); // place a password text field at position (1,1)
		this.getContentPane().add(loginButton); // place a login button at position (2,0)
		this.getContentPane().add(cancelButton); // place a cancel button at position (2,1)
		this.setVisible(true);

		loginButton.setEnabled(false); // User can't clicked on login button.

		logintextField.getDocument().addDocumentListener( // Add a listener to detect when login text field 's text changed
				new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent doc) { // Called
																	// when you
																	// add text
						System.out.println("changedUpdate "
								+ logintextField.getText());
						updateLoginInButtonState(); // Update login button state

					}

					@Override
					public void insertUpdate(DocumentEvent doc) {
						System.out.println("insertUpdate "
								+ logintextField.getText());
						updateLoginInButtonState();// Update login button state
					}

					@Override
					public void removeUpdate(DocumentEvent doc) { // Called when
																	// you
																	// delete
																	// text
						System.out.println("removeUpdate "
								+ logintextField.getText());
						updateLoginInButtonState();// Update login button state
					}

				});

		passwordTextField.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent doc) { // Called
																	// when you
																	// add text
						updateLoginInButtonState();// Update login button state

					}

					@Override
					public void insertUpdate(DocumentEvent doc) {
						System.out.println("insertUpdate "
								+ passwordTextField.getText());
						updateLoginInButtonState();// Update login button state
					}

					@Override
					public void removeUpdate(DocumentEvent doc) { // Called when
																	// you
																	// delete
																	// text
						System.out.println("removeUpdate "
								+ passwordTextField.getText());
						updateLoginInButtonState();// Update login button state
					}

				});
	}
	
	public void updateLoginInButtonState() // Update login buton state
	{
		if (passwordTextField.getText().isEmpty() || logintextField.getText().isEmpty()){ // If password text field or
			// login text field is empty, user can't click on login button
			
			loginButton.setEnabled(false);
			return;
		}
		loginButton.setEnabled(true);
	}
	
	public JButton getLoginButton()
	{
		return this.loginButton;
	}
	
	public JButton getCancelButton()
	{
		return this.cancelButton;
	}
	
	public JTextField getPasswordTextField()
	{
		return this.passwordTextField;
	}
	
	public JTextField getLoginTextField()
	{
		return this.logintextField;
	}
	
	public String getLoginText()
	{
		return logintextField.getText();
		
	}
	
	public String getPasswordText()
	{
		return passwordTextField.getText();
	}

}
