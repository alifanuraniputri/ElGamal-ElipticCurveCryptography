import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.security.spec.EllipticCurve;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UI extends JApplet {
	
	ElGamalECC elgamalECC;

	/* GUI */
	JButton fileBtn;
	JTextArea inputTextArea;
	JTextArea outputTextArea;
	JTextField kunciPublicTextField;
	JTextField kunciPrivateTextField;
	JButton browsePrivateKey;
	JButton browsePublicKey;
	JButton saveKey;
	JButton encryptButton;
	JButton decryptButton;
	JButton saveCipher;

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

	private void guiInit() {

		/** Frame **/
		Frame c = (Frame) this.getParent().getParent();
		c.setTitle("El Gamal Eliptic Curve Cryptography");
		getContentPane().setLayout(null);
		/* ! Frame ! */

		/** Label **/
		JLabel inputLabel;
		inputLabel = new JLabel("Input File");
		inputLabel.setBounds(20, 10, 100, 30);
		inputLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		/* ! Label ! */

		/** Select File **/
		fileBtn = new JButton("Select File ");
		fileBtn.setBackground(Color.PINK);
		fileBtn.setBounds(90, 10, 120, 30);
		fileBtn.setOpaque(true);
		fileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readInput();
			}
		});
		/* ! Select File */

		/** Input File **/
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
		scroll.setBounds(20, 60, 460, 150);
		/* ! Input File ! */

		/** Kunci **/
		JLabel kunciPrivateLabel = new JLabel("Kunci Private");
		kunciPrivateLabel.setBounds(20, 220, 80, 30);
		kunciPrivateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel kunciPublicLabel = new JLabel("Kunci Public");
		kunciPublicLabel.setBounds(205, 220, 80, 30);
		kunciPublicLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		browsePrivateKey = new JButton("browse");
		browsePrivateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		browsePrivateKey.setBackground(Color.PINK);
		browsePrivateKey.setBounds(110, 220, 80, 30);
		browsePrivateKey.setOpaque(true);
		
		browsePublicKey = new JButton("browse");
		browsePublicKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		browsePublicKey.setBackground(Color.PINK);
		browsePublicKey.setBounds(295, 220, 80, 30);
		browsePublicKey.setOpaque(true);

		kunciPrivateTextField = new JTextField();
		kunciPrivateTextField.setBounds(20, 255, 170, 30);
		kunciPrivateTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		kunciPublicTextField = new JTextField();
		kunciPublicTextField.setBounds(205, 255, 275, 30);
		kunciPublicTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		saveKey = new JButton("Simpan Kunci");
		saveKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		saveKey.setBackground(Color.PINK);
		saveKey.setBounds(200, 300, 100, 30);
		saveKey.setOpaque(true);
		
		/* ! Kunci ! */

		/** Enkripsi **/
		encryptButton = new JButton("Enkripsi");
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!kunciPrivateTextField.getText().equals("") || !kunciPublicTextField.getText().equals("")) {
					elgamalECC.encrypt();
					saveCipher.setEnabled(true);
				} else 
					JOptionPane.showMessageDialog(getContentPane(), "kunci harus terisi");
			}
		});
		encryptButton.setBackground(Color.PINK);
		encryptButton.setBounds(20, 300, 80, 30);
		encryptButton.setOpaque(true);
		/* ! Enkripsi ! */

		/** Dekripsi **/
		decryptButton = new JButton("Dekripsi");
		decryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!kunciPrivateTextField.getText().equals("") || !kunciPublicTextField.getText().equals("")) {
					elgamalECC.decrypt();
					saveCipher.setEnabled(true);
				} else 
					JOptionPane.showMessageDialog(getContentPane(), "kunci harus terisi");
			}
		});
		decryptButton.setBackground(Color.PINK);
		decryptButton.setBounds(110, 300, 80, 30);
		decryptButton.setOpaque(true);
		/** Dekripsi **/

		/** Output **/
		JLabel outputLabel = new JLabel("Output Text");
		outputLabel.setBounds(20, 340, 100, 30);
		outputLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		outputTextArea = new JTextArea();
		outputTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		outputTextArea.setLineWrap(true);
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setVisible(true);
		outputTextArea.setLineWrap(true); // at the end of line goes to new line
		outputTextArea.setFocusable(true);

		JScrollPane scroll2 = new JScrollPane(outputTextArea);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setBounds(20, 380, 460, 150);
		/* ! Output ! */

		/** Save **/
		saveCipher = new JButton("Save Output to File");
		saveCipher.setBounds(20, 535, 200, 30);
		saveCipher.setEnabled(false);
		saveCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveOutput();
			}
		});
		/* ! Save ! */

		getContentPane().add(fileBtn);
		getContentPane().add(inputLabel);
		getContentPane().add(scroll);
		getContentPane().add(kunciPrivateLabel);
		getContentPane().add(kunciPublicLabel);
		getContentPane().add(kunciPublicTextField);
		getContentPane().add(kunciPrivateTextField);
		getContentPane().add(encryptButton);
		getContentPane().add(decryptButton);
		getContentPane().add(browsePrivateKey);
		getContentPane().add(browsePublicKey);
		getContentPane().add(scroll2);
		getContentPane().add(outputLabel);
		getContentPane().add(saveCipher);
		getContentPane().add(saveKey);
		encryptButton.setEnabled(false);
		decryptButton.setEnabled(false);

		elgamalECC = new ElGamalECC();
	}

	public void readInput() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						BufferedReader br = new BufferedReader(new FileReader(
								file));
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();
						while (line != null) {
							sb.append(line);
							sb.append(System.lineSeparator());
							line = br.readLine();
						}
						String input = sb.toString();
						elgamalECC.setInput(input);
						br.close();
						inputTextArea.setText(input);
						encryptButton.setEnabled(true);
						decryptButton.setEnabled(true);
						saveCipher.setEnabled(false);
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
	}

	public void saveOutput() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						FileOutputStream out = new FileOutputStream(file);
						String output = elgamalECC.getOutput();
						for (int j = 0; j < output.length(); j++) {
							char c = output.charAt(j);
							out.write((int) c);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
	}
}
