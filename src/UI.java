import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UI extends JApplet {

	ElGamalECC elgamalECC;
	EllipticCurveGF curveForKey;
	boolean isEncrypt;
	int privateK;
	Point publicK;
	Point titikBasis;

	/* GUI */
	JButton fileBtn;
	JTextArea inputTextArea;
	JTextArea outputTextArea;
	JTextField kunciTextField;
	JTextField privateTextField;
	JTextField publicTextField;
	JTextField param_a;
	JTextField param_b;
	JTextField param_p;
	JTextField param_Bx;
	JTextField param_By;
	JButton browseKey;
	JButton saveKeyPrivate;
	JButton saveKeyPublic;
	JButton encryptdecryptButton;
	JButton saveCipher;
	JButton generateKeyButton;
	JTextField txt = new JTextField(20);
	JPanel generateKey;
	JPanel encryption;
	JPanel decryption;
	JRadioButton encryptRadio = new JRadioButton("Encrypt", true);
	JRadioButton decryptRadio = new JRadioButton("Decrypt");
	JLabel notifLabel;

	private String[] title = { "Generate Key", "Encrypt & Decrypt" };

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
		
		isEncrypt=true;

		elgamalECC = new ElGamalECC();
		curveForKey = new EllipticCurveGF();

		/** Label **/
		JLabel inputLabel;
		inputLabel = new JLabel("Input File");
		inputLabel.setBounds(20, 10, 100, 30);
		inputLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

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
		scroll.setBounds(20, 60, 560, 150);
		/* ! Input File ! */

		/** Key **/
		browseKey = new JButton("Browse kunci");
		browseKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readKey();
			}
		});
		browseKey.setBackground(Color.PINK);
		browseKey.setBounds(210, 220, 100, 30);
		browseKey.setOpaque(true);

		notifLabel = new JLabel("Kunci Publik untuk Enkripsi");
		notifLabel.setBounds(320, 220, 180, 30);
		notifLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		kunciTextField = new JTextField();
		kunciTextField.setBounds(20, 220, 170, 30);
		kunciTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		kunciTextField.setEditable(false);
		/* ! Key ! */

		/** Encrypt Decrypt **/
		encryptRadio.setBounds(20, 260, 80, 30);
		decryptRadio.setBounds(110, 260, 80, 30);
		encryptRadio.setBackground(Color.WHITE);
		decryptRadio.setBackground(Color.WHITE);
		encryptRadio.setMnemonic(KeyEvent.VK_C);
		decryptRadio.setMnemonic(KeyEvent.VK_M);

		encryptRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isEncrypt = true;
				notifLabel.setText("Kunci Publik untuk Enkripsi");
			}
		});

		decryptRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isEncrypt = false;
				notifLabel.setText("Kunci Privat untuk Dekripsi");
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(encryptRadio);
		group.add(decryptRadio);

		encryptdecryptButton = new JButton("Execute");
		encryptdecryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isEncrypt) {
					elgamalECC.encrypt();
					String encypted="";
					for (int i=0; i< elgamalECC.getTupleOutput().length; i++) {
						encypted = encypted + "\n" + elgamalECC.getTupleOutput()[i].toString();
					}
					outputTextArea.setText(encypted);
				} else {
					;
					elgamalECC.decrypt();
				}
				saveCipher.setEnabled(true);

			}
		});
		encryptdecryptButton.setBackground(Color.PINK);
		encryptdecryptButton.setBounds(200, 260, 80, 30);
		encryptdecryptButton.setOpaque(true);
		/* ! Encrypt Dcrypt ! */

		/** Output **/
		JLabel outputLabel = new JLabel("Output Text");
		outputLabel.setBounds(20, 300, 100, 30);
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
		scroll2.setBounds(20, 350, 560, 150);
		/* ! Output ! */

		/** Save **/
		saveCipher = new JButton("Save Output to File");
		saveCipher.setBounds(20, 510, 200, 30);
		saveCipher.setEnabled(false);
		saveCipher.setBackground(Color.PINK);
		saveCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveOutput();
			}
		});

		/* ! Save ! */

		/** Kunci **/
		JLabel kunciPrivateLabel = new JLabel("Kunci Private");
		kunciPrivateLabel.setBounds(20, 20, 80, 30);
		kunciPrivateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		privateTextField = new JTextField();
		privateTextField.setBounds(20, 60, 400, 30);
		privateTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));

		generateKeyButton = new JButton("Generate Key");
		generateKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				curveForKey.setA(Integer.parseInt(param_a.getText()));
				curveForKey.setB(Integer.parseInt(param_b.getText()));
				curveForKey.setP(Integer.parseInt(param_p.getText()));
				int xb = Integer.parseInt(param_Bx.getText());
				int yb = Integer.parseInt(param_By.getText());
				if (curveForKey.isXYinCurve(xb, yb)) {
					titikBasis = new Point(xb, yb);
					Point kunciPublik = curveForKey.perkalianPoin(
							Integer.parseInt(privateTextField.getText()),
							titikBasis);
					publicTextField.setText(kunciPublik.toString());
					privateK = Integer.parseInt(privateTextField.getText());
					publicK = kunciPublik;
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"titik basis B tidak terdapat dalam kurva");
				}

			}
		});
		generateKeyButton.setBackground(Color.PINK);
		generateKeyButton.setBounds(20, 190, 150, 30);
		generateKeyButton.setOpaque(true);

		JLabel kunciPublicLabel = new JLabel("Kunci Publik");
		kunciPublicLabel.setBounds(20, 230, 80, 30);
		kunciPublicLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		publicTextField = new JTextField();
		publicTextField.setBounds(20, 270, 400, 30);
		publicTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		publicTextField.setEditable(false);

		saveKeyPrivate = new JButton("Simpan Kunci Private");
		saveKeyPrivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (publicTextField.getText() != null) {
					savePrivate();
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"generate key dahulu ");
				}
			}
		});
		saveKeyPrivate.setBackground(Color.PINK);
		saveKeyPrivate.setBounds(20, 320, 150, 30);
		saveKeyPrivate.setOpaque(true);

		saveKeyPublic = new JButton("Simpan Kunci Public");
		saveKeyPublic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (publicTextField.getText() != null) {
					savePublic();
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"generate key dahulu ");
				}
			}
		});
		saveKeyPublic.setBackground(Color.PINK);
		saveKeyPublic.setBounds(180, 320, 150, 30);
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
		generateKey.add(saveKeyPrivate);
		generateKey.add(saveKeyPublic);
		generateKey.add(privateTextField);
		generateKey.add(generateKeyButton);
		generateKey.add(kunciPublicLabel);
		generateKey.add(publicTextField);

		encryption = new JPanel();
		encryption.setLayout(null);
		encryption.setBackground(Color.WHITE);
		encryption.add(fileBtn);
		encryption.add(inputLabel);
		encryption.add(scroll);
		encryption.add(scroll2);
		encryption.add(browseKey);
		encryption.add(kunciTextField);
		encryption.add(saveCipher);
		encryption.add(encryptdecryptButton);
		encryption.add(outputLabel);
		encryption.add(encryptRadio);
		encryption.add(decryptRadio);
		encryption.add(notifLabel);

		/* ! Frame ! */

		/** TabbedPane **/
		getContentPane().setBackground(Color.WHITE);
		tabs.addTab(title[0], generateKey);
		tabs.addTab(title[1], encryption);
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected: " + tabs.getSelectedIndex());
			}
		});
		Container cp = getContentPane();
		cp.add(BorderLayout.SOUTH, txt);
		cp.add(tabs);
		/* ! TabbedPane ! */

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
						br.close();
						inputTextArea.setText(input);
						encryptdecryptButton.setEnabled(true);
						saveCipher.setEnabled(false);
						// TODO read as byte array set to elGamalECC input
						byte[] plaintxtbyte = elgamalECC.getEllipticCurve().get_byte_file(file);
						elgamalECC.setPoinInput(elgamalECC.getEllipticCurve().arrayByteToPoint(plaintxtbyte));
						
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
						FileWriter out;
						out = new FileWriter(fileChooser.getSelectedFile()
								.getAbsolutePath());
						BufferedWriter bw = new BufferedWriter(out);
						for (int i=0; i< elgamalECC.getTupleOutput().length; i++) {
							bw.write(elgamalECC.getTupleOutput()[i].toString());
							bw.write("\n");
						}
						
						JOptionPane.showMessageDialog(getContentPane(),
								"Cipher Tersimpan");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
	}

	public void savePrivate() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final JFileChooser fileChooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("PRI (.pri)",
						"pri");
				fileChooser.setFileFilter(filter);
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try {
						FileWriter out;
						if (fileChooser.getSelectedFile().getAbsolutePath()
								.contains(".pri"))
							out = new FileWriter(fileChooser.getSelectedFile()
									.getAbsolutePath());
						else
							out = new FileWriter(fileChooser.getSelectedFile()
									.getAbsolutePath() + ".pri");
						String sPrivate = Integer.toString(privateK);
						BufferedWriter bw = new BufferedWriter(out);
						String sPublikX = Integer.toString(publicK.x);
						bw.write(sPrivate);
						bw.write('\n');
						bw.write(Integer.toString(curveForKey.getA()));
						bw.write('\n');
						bw.write(Integer.toString(curveForKey.getB()));
						bw.write('\n');
						bw.write(Integer.toString(curveForKey.getP()));
						bw.write('\n');
						bw.write(Integer.toString(titikBasis.x));
						bw.write('\n');
						bw.write(Integer.toString(titikBasis.y));
						bw.close();
						JOptionPane.showMessageDialog(getContentPane(),
								"Kunci Privat Tersimpan");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
	}

	public void savePublic() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final JFileChooser fileChooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("PUB (.pub)",
						"pub");
				fileChooser.setFileFilter(filter);
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try {
						FileWriter out;
						if (fileChooser.getSelectedFile().getAbsolutePath()
								.contains(".pub"))
							out = new FileWriter(fileChooser.getSelectedFile()
									.getAbsolutePath());
						else
							out = new FileWriter(fileChooser.getSelectedFile()
									.getAbsolutePath() + ".pub");
						BufferedWriter bw = new BufferedWriter(out);
						String sPublikX = Integer.toString(publicK.x);
						bw.write(sPublikX);
						bw.write('\n');
						String sPublikY = Integer.toString(publicK.y);
						bw.write(sPublikY);
						bw.write('\n');
						bw.write(Integer.toString(curveForKey.getA()));
						bw.write('\n');
						bw.write(Integer.toString(curveForKey.getB()));
						bw.write('\n');
						bw.write(Integer.toString(curveForKey.getP()));
						bw.write('\n');
						bw.write(Integer.toString(titikBasis.x));
						bw.write('\n');
						bw.write(Integer.toString(titikBasis.y));
						bw.close();
						JOptionPane.showMessageDialog(getContentPane(),
								"Kunci Privat Tersimpan");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
	}

	public void readKey() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						BufferedReader br = null;
						String s;
						br = new BufferedReader(new FileReader(file));
						EllipticCurveGF elCurve = new EllipticCurveGF();
						if (isEncrypt && fileChooser.getSelectedFile().getAbsolutePath()
								.contains(".pub")) {
							Point p = new Point();
							p.x = Integer.parseInt(br.readLine());
							p.y = Integer.parseInt(br.readLine());
							kunciTextField.setText(p.toString());
							elgamalECC.setPublicKey(p);
							elCurve.setA(Integer.parseInt(br.readLine()));
							elCurve.setB(Integer.parseInt(br.readLine()));
							elCurve.setP(Integer.parseInt(br.readLine()));
							p = new Point();
							p.x = Integer.parseInt(br.readLine());
							p.y = Integer.parseInt(br.readLine());
							elgamalECC.setTitikBasis(p);
							elgamalECC.setEllipticCurve(elCurve);
						} else if (!isEncrypt && fileChooser.getSelectedFile().getAbsolutePath()
								.contains(".pri")){
							elgamalECC.setPrivateKey(Integer.parseInt(br.readLine()));
							kunciTextField.setText(Integer.toString(elgamalECC.getPrivateKey()));
							elCurve.setA(Integer.parseInt(br.readLine()));
							elCurve.setB(Integer.parseInt(br.readLine()));
							elCurve.setP(Integer.parseInt(br.readLine()));
							Point p = new Point();
							p.x = Integer.parseInt(br.readLine());
							p.y = Integer.parseInt(br.readLine());
							elgamalECC.setTitikBasis(p);
							elgamalECC.setEllipticCurve(elCurve);
						} else {
							JOptionPane.showMessageDialog(getContentPane(),
									".pub for public .pri for private");
						}
						br.close();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}
			}
		});

	}
}
