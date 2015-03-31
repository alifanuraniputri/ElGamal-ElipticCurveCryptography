import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UI extends JApplet {

	ElGamalECC elgamalECC;

	/* GUI */
	JButton fileBtn;
	JButton fileBtn2;
	JTextArea inputTextArea;
	JTextArea outputTextArea;
	JTextField kunciPrivateTextField;
	JTextField kunciPublicTextField;
	JTextField param_a;
	JTextField param_b;
	JTextField param_p;
	JTextField param_Bx;
	JTextField param_By;
	JButton browsePrivateKey;
	JButton browsePublicKey;
	JButton saveKeyPrivate;
	JButton saveKeyPublic;
	JButton encryptButton;
	JButton decryptButton;
	JButton saveCipher;
	JButton saveCipher2;
	JTextField txt = new JTextField(20);
	JPanel generateKey;
	JPanel encryption ;
	JPanel decryption;

	private String[] title = { "Generate Key", "Encrypt", "Decrypt" };

	private JTabbedPane tabs = new JTabbedPane();

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

	public static void main(String[] args) {
		run(new UI(), 600, 325);
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(applet);
		frame.setSize(width, height);
		frame.setTitle("El Gamal Eliptic Curve Cryptography");
		applet.init();
		applet.start();
		frame.setVisible(true);
	}

	private void guiInit() {

		/** Label **/
		JLabel inputLabel;
		inputLabel = new JLabel("Input File");
		inputLabel.setBounds(20, 10, 100, 30);
		inputLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel inputLabel2;
		inputLabel2 = new JLabel("Input File");
		inputLabel2.setBounds(20, 10, 100, 30);
		inputLabel2.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		
		fileBtn2 = new JButton("Select File ");
		fileBtn2.setBackground(Color.PINK);
		fileBtn2.setBounds(90, 10, 120, 30);
		fileBtn2.setOpaque(true);
		fileBtn2.addActionListener(new ActionListener() {
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
		scroll.setBounds(20, 60, 560, 150);
		/* ! Input File ! */

		/** Kunci **/
		JLabel kunciPrivateLabel = new JLabel("Kunci Private");
		kunciPrivateLabel.setBounds(20, 20, 80, 30);
		kunciPrivateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		browsePrivateKey = new JButton("browse kunci private");
		browsePrivateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		browsePrivateKey.setBackground(Color.PINK);
		browsePrivateKey.setBounds(110, 220, 130, 30);
		browsePrivateKey.setOpaque(true);

		browsePublicKey = new JButton("browse kunci publik");
		browsePublicKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		browsePublicKey.setBackground(Color.PINK);
		browsePublicKey.setBounds(210, 220, 130, 30);
		browsePublicKey.setOpaque(true);

		kunciPrivateTextField = new JTextField();
		kunciPrivateTextField.setBounds(20, 55, 170, 30);
		kunciPrivateTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		kunciPublicTextField = new JTextField();
		kunciPublicTextField.setBounds(20, 220, 170, 30);
		kunciPublicTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));


		saveKeyPrivate = new JButton("Simpan Kunci Private");
		saveKeyPrivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		saveKeyPrivate.setBackground(Color.PINK);
		saveKeyPrivate.setBounds(20, 190, 150, 30);
		saveKeyPrivate.setOpaque(true);
		
		saveKeyPublic = new JButton("Simpan Kunci Public");
		saveKeyPublic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		saveKeyPublic.setBackground(Color.PINK);
		saveKeyPublic.setBounds(180, 190, 150, 30);
		saveKeyPublic.setOpaque(true);
		/* ! Kunci ! */

		/** Parameter **/
		JLabel parameter = new JLabel("Parameter ");
		parameter.setBounds(20, 100, 80, 20);
		parameter.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_a = new JLabel("a : ");
		label_a.setBounds(20, 140, 30, 30);
		label_a.setFont(new Font("Tahoma", Font.BOLD, 11));

		param_a = new JTextField();
		param_a.setBounds(50, 140, 50, 30);
		param_a.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel label_b = new JLabel("b : ");
		label_b.setBounds(140, 140, 30, 30);
		label_b.setFont(new Font("Tahoma", Font.BOLD, 11));

		param_b = new JTextField();
		param_b.setBounds(160, 140, 50, 30);
		param_b.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel label_p = new JLabel("p : ");
		label_p.setBounds(250, 140, 30, 30);
		label_p.setFont(new Font("Tahoma", Font.BOLD, 11));

		param_p = new JTextField();
		param_p.setBounds(270, 140, 50, 30);
		param_p.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel label_Bx = new JLabel("xB : ");
		label_Bx.setBounds(360, 140, 30, 30);
		label_Bx.setFont(new Font("Tahoma", Font.BOLD, 11));

		param_Bx = new JTextField();
		param_Bx.setBounds(400, 140, 50, 30);
		param_Bx.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel label_By = new JLabel("yB : ");
		label_By.setBounds(460, 140, 30, 30);
		label_By.setFont(new Font("Tahoma", Font.BOLD, 11));

		param_By = new JTextField();
		param_By.setBounds(500, 140, 50, 30);
		param_By.setFont(new Font("Tahoma", Font.PLAIN, 11));
		/* ! Parameter ! */

		/** Enkripsi **/
		encryptButton = new JButton("Enkripsi");
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					elgamalECC.encrypt();
					saveCipher.setEnabled(true);
				
			}
		});
		encryptButton.setBackground(Color.PINK);
		encryptButton.setBounds(20, 350, 80, 30);
		encryptButton.setOpaque(true);
		/* ! Enkripsi ! */

		/** Dekripsi **/
		decryptButton = new JButton("Dekripsi");
		decryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					elgamalECC.decrypt();
					saveCipher.setEnabled(true);
				
			}
		});
		decryptButton.setBackground(Color.PINK);
		decryptButton.setBounds(110, 350, 80, 30);
		decryptButton.setOpaque(true);
		/** Dekripsi **/

		/** Output **/
		JLabel outputLabel = new JLabel("Output Text");
		outputLabel.setBounds(20, 390, 100, 30);
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
		scroll2.setBounds(20, 430, 560, 150);
		/* ! Output ! */

		/** Save **/
		saveCipher = new JButton("Save Output to File");
		saveCipher.setBounds(20, 230, 200, 30);
		saveCipher.setEnabled(false);
		saveCipher.setBackground(Color.PINK);
		saveCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveOutput();
			}
		});
		
		saveCipher2 = new JButton("Save Output to File");
		saveCipher2.setBounds(20, 230, 200, 30);
		saveCipher2.setEnabled(false);
		saveCipher2.setBackground(Color.PINK);
		saveCipher2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveOutput();
			}
		});
		/* ! Save ! */
		
		/** Panel **/
		generateKey = new JPanel();
		generateKey.setBackground(Color.WHITE);
		generateKey.setLayout(null);
		generateKey.add(label_a);
		generateKey.add(label_b);
		generateKey.add(label_p);
		generateKey.add(label_Bx); 
		generateKey.add(label_By);
		generateKey.add(param_a); 
		generateKey.add(param_b);
		generateKey.add(param_p); 
		generateKey.add(param_Bx);
		generateKey.add(param_By); 
		generateKey.add(parameter);
		generateKey.add(kunciPrivateLabel);
		generateKey.add(kunciPrivateTextField);
		generateKey.add(saveKeyPrivate);
		generateKey.add(saveKeyPublic);
		
		encryption = new JPanel();
		encryption.setLayout(null);
		encryption.setBackground(Color.WHITE);
		encryption.add(fileBtn); 
		encryption.add(inputLabel);
		encryption.add(scroll);
		encryption.add(browsePublicKey);
		encryption.add(kunciPublicTextField);
		encryption.add(saveCipher);
		
		
		decryption = new JPanel();
		decryption.setLayout(null);
		decryption.setBackground(Color.WHITE);
		decryption.add(inputLabel2);
		decryption.add(fileBtn2); 
		decryption.add(saveCipher2);
		/* ! Frame ! */
		
		/** TabbedPane **/
		getContentPane().setBackground(Color.WHITE);
		tabs.addTab(title[0],generateKey);
		tabs.addTab(title[1], encryption);
		tabs.addTab(title[2], decryption);
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected: " + tabs.getSelectedIndex());
			}
		});
		Container cp = getContentPane();
		cp.add(BorderLayout.SOUTH, txt);
		cp.add(tabs);
		/*! TabbedPane !*/
		
		/*
		 * getContentPane().add(fileBtn); getContentPane().add(inputLabel);
		 * getContentPane().add(scroll);
		 * getContentPane().add(kunciPrivateLabel);
		 * getContentPane().add(kunciPublicLabel);
		 * getContentPane().add(kunciPublicTextField);
		 * getContentPane().add(kunciPrivateTextField);
		 * getContentPane().add(encryptButton);
		 * getContentPane().add(decryptButton);
		 * getContentPane().add(browsePrivateKey);
		 * getContentPane().add(browsePublicKey); getContentPane().add(scroll2);
		 * getContentPane().add(outputLabel); getContentPane().add(saveCipher);
		 * getContentPane().add(saveKey); getContentPane().add(label_a);
		 * getContentPane().add(label_b); getContentPane().add(label_p);
		 * getContentPane().add(label_Bx); getContentPane().add(label_By);
		 * getContentPane().add(param_a); getContentPane().add(param_b);
		 * getContentPane().add(param_p); getContentPane().add(param_Bx);
		 * getContentPane().add(param_By); getContentPane().add(parameter);
		 * encryptButton.setEnabled(false); decryptButton.setEnabled(false);
		 */

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
