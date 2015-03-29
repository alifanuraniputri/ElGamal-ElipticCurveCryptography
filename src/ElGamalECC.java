import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class ElGamalECC extends JApplet {

	JButton fileBtn;
	JTextArea inputTextArea;
	JTextArea outputTextArea;
	JTextField kunciTextField;
	JButton encryptButton;
	JButton decryptButton;
	JButton saveCipher;
	byte[] fileData;

	public void init() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
		}

		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					guiInit(); // initialize the GUI
				}
			});
		} catch (Exception exc) {
			System.out.println("Can't create because of " + exc);
		}

	}

	// Setup and initialize the GUI.
	private void guiInit() {

		Frame c = (Frame) this.getParent().getParent();
		c.setTitle("El Gamal Eliptic Curve Cryptography");
		getContentPane().setLayout(null);

		JLabel inputLabel;
		inputLabel = new JLabel("Input File");
		inputLabel.setBounds(20, 10, 100, 30);
		inputLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		fileBtn = new JButton("Select File ");
		fileBtn.setBackground(Color.PINK);
		fileBtn.setBounds(90, 10, 120, 33);
		fileBtn.setOpaque(true);
		fileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO read file
			}
		});

		// Plain Text
		inputTextArea = new JTextArea();
		inputTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		inputTextArea.setVisible(true);
		inputTextArea.setLineWrap(true);
		inputTextArea.setFocusable(true);
		inputTextArea.setEditable(false);

		JScrollPane scroll = new JScrollPane(inputTextArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(20, 60, 560, 130);

		// Kata Kunci
		JLabel kunciLabel = new JLabel("Kata Kunci");
		kunciLabel.setBounds(20, 200, 100, 30);
		kunciLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		kunciTextField = new JTextField();
		kunciTextField.setBounds(20, 230, 560, 30);
		kunciTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));

		encryptButton = new JButton("Enkripsi");
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO encrypt with ElGamal ECC
			}
		});
		encryptButton.setBackground(Color.PINK);
		encryptButton.setBounds(20, 300, 80, 30);
		encryptButton.setOpaque(true);

		decryptButton = new JButton("Dekripsi");
		decryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO decrypt with ElGamal ECC
			}
		});
		decryptButton.setBackground(Color.PINK);
		decryptButton.setBounds(110, 300, 80, 30);
		decryptButton.setOpaque(true);

		JLabel outputLabel = new JLabel("Output Text");
		outputLabel.setBounds(20, 340, 100, 30);
		outputLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		// output text area
		outputTextArea = new JTextArea();
		outputTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		outputTextArea.setLineWrap(true);
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setVisible(true);
		outputTextArea.setLineWrap(true); // at the end of line goes to new line
		outputTextArea.setFocusable(true);

		JScrollPane scroll2 = new JScrollPane(outputTextArea);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setBounds(20, 380, 560, 130);

		saveCipher = new JButton("Save Cipher to File");
		saveCipher.setBounds(20, 550, 200, 30);
		saveCipher.setEnabled(false);
		saveCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO save 
			}
		});

		getContentPane().add(fileBtn);
		getContentPane().add(inputLabel);
		getContentPane().add(scroll);
		getContentPane().add(kunciLabel);
		getContentPane().add(kunciTextField);
		getContentPane().add(encryptButton);
		getContentPane().add(decryptButton);
		getContentPane().add(scroll2);
		getContentPane().add(outputLabel);
		getContentPane().add(saveCipher);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
