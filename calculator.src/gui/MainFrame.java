/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */
package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import attacks.FullRound;
import calculations.Calculation;
import characters.PfCharacter;
import main.Filesystem;
import weapons.Weapon;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import java.awt.TextArea;
import java.awt.Label;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import javax.swing.DropMode;
import java.awt.FlowLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class MainFrame.
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6261812111921847254L;

	/** The content pane. */
	private JPanel contentPane;

	/** The text field. */
	private JTextField acTextField;

	private static PfCharacter character = new PfCharacter();

	/**
	 * Launch the application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setFont(new Font("Razer Header Regular Oblique", Font.PLAIN, 20));
		setBackground(Color.DARK_GRAY);
		setForeground(Color.DARK_GRAY);
		setResizable(false);
		setTitle("Pathfinder Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		// setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFullRound = new JLabel("Fullround:");
		lblFullRound.setForeground(Color.WHITE);
		lblFullRound.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 35));
		lblFullRound.setBounds(372, 12, 252, 43);
		contentPane.add(lblFullRound);

		JLabel lblCharacterName = new JLabel("Character:");
		lblCharacterName.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 35));
		lblCharacterName.setBounds(12, 12, 234, 43);
		contentPane.add(lblCharacterName);
		lblCharacterName.setForeground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel.setBounds(12, 67, 348, 466);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.DARK_GRAY);
		panel_5.setBounds(12, 12, 96, 444);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(0, 0, 59, 19);
		panel_5.add(lblName);

		JLabel lblBab = new JLabel("Bab");
		lblBab.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblBab.setForeground(Color.WHITE);
		lblBab.setBounds(0, 31, 38, 19);
		panel_5.add(lblBab);

		JLabel lblDex = new JLabel("Dex");
		lblDex.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblDex.setForeground(Color.WHITE);
		lblDex.setBounds(0, 62, 37, 19);
		panel_5.add(lblDex);

		JLabel lblStr = new JLabel("Str");
		lblStr.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblStr.setForeground(Color.WHITE);
		lblStr.setBounds(0, 93, 30, 19);
		panel_5.add(lblStr);

		JLabel lblFeats = new JLabel("Feats");
		lblFeats.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblFeats.setForeground(Color.WHITE);
		lblFeats.setBounds(0, 124, 56, 19);
		panel_5.add(lblFeats);

		JLabel lblWeapons = new JLabel("Weapons");
		lblWeapons.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblWeapons.setForeground(Color.WHITE);
		lblWeapons.setBounds(0, 280, 89, 19);
		panel_5.add(lblWeapons);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.DARK_GRAY);
		panel_6.setBounds(119, 12, 217, 444);
		panel.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblChaBab = new JLabel(String.valueOf(character.getBab()));
		lblChaBab.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblChaBab.setForeground(Color.WHITE);
		lblChaBab.setBounds(12, 31, 30, 19);
		panel_6.add(lblChaBab);

		JLabel lblChaDex = new JLabel(String.valueOf(character.getDex()));
		lblChaDex.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblChaDex.setForeground(Color.WHITE);
		lblChaDex.setBounds(12, 62, 30, 19);
		panel_6.add(lblChaDex);

		JLabel lblChaStr = new JLabel(String.valueOf(character.getStr()));
		lblChaStr.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblChaStr.setForeground(Color.WHITE);
		lblChaStr.setBounds(12, 93, 30, 19);
		panel_6.add(lblChaStr);

		TextArea textAreaFeats = new TextArea();
		textAreaFeats.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		textAreaFeats.setForeground(Color.WHITE);
		textAreaFeats.setBackground(Color.DARK_GRAY);
		textAreaFeats.setEditable(false);
		textAreaFeats.setBounds(10, 124, 200, 150);
		panel_6.add(textAreaFeats);

		TextArea textAreaWeapons = new TextArea();
		textAreaWeapons.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		textAreaWeapons.setForeground(Color.WHITE);
		textAreaWeapons.setBackground(Color.DARK_GRAY);
		textAreaWeapons.setEditable(false);
		textAreaWeapons.setBounds(10, 280, 200, 150);
		panel_6.add(textAreaWeapons);

		JPanel panel_7 = new JPanel();
		panel_7.setForeground(Color.WHITE);
		panel_7.setBackground(Color.DARK_GRAY);
		panel_7.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel_7.setBounds(372, 67, 509, 466);
		contentPane.add(panel_7);
		panel_7.setLayout(null);

		Choice fullRoundChoice = new Choice();
		fullRoundChoice.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		fullRoundChoice.setForeground(Color.DARK_GRAY);
		fullRoundChoice.setBackground(Color.WHITE);
		fullRoundChoice.setBounds(10, 10, 487, 25);
		panel_7.add(fullRoundChoice);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(10, 41, 487, 152);
		panel_7.add(panel_2);

		JRadioButton rdbtnBuff = new JRadioButton("Buff 1");
		rdbtnBuff.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rdbtnBuff.setForeground(Color.WHITE);
		rdbtnBuff.setBackground(Color.DARK_GRAY);
		panel_2.add(rdbtnBuff);

		JRadioButton rdbtnBuff_1 = new JRadioButton("Buff 2");
		rdbtnBuff_1.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rdbtnBuff_1.setForeground(Color.WHITE);
		rdbtnBuff_1.setBackground(Color.DARK_GRAY);
		panel_2.add(rdbtnBuff_1);

		JRadioButton rdbtnBuff_2 = new JRadioButton("Buff 3");
		rdbtnBuff_2.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rdbtnBuff_2.setForeground(Color.WHITE);
		rdbtnBuff_2.setBackground(Color.DARK_GRAY);
		panel_2.add(rdbtnBuff_2);

		JRadioButton rdbtnBuff_3 = new JRadioButton("Buff 4");
		rdbtnBuff_3.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rdbtnBuff_3.setForeground(Color.WHITE);
		rdbtnBuff_3.setBackground(Color.DARK_GRAY);
		panel_2.add(rdbtnBuff_3);

		JRadioButton rdbtnBuff_4 = new JRadioButton("Buff 5");
		rdbtnBuff_4.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rdbtnBuff_4.setForeground(Color.WHITE);
		rdbtnBuff_4.setBackground(Color.DARK_GRAY);
		panel_2.add(rdbtnBuff_4);

		JRadioButton rdbtnBuff_5 = new JRadioButton("Buff 6");
		rdbtnBuff_5.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rdbtnBuff_5.setForeground(Color.WHITE);
		rdbtnBuff_5.setBackground(Color.DARK_GRAY);
		panel_2.add(rdbtnBuff_5);

		JRadioButton rdbtnBuff_6 = new JRadioButton("Buff 7");
		rdbtnBuff_6.setForeground(Color.WHITE);
		rdbtnBuff_6.setBackground(Color.DARK_GRAY);
		rdbtnBuff_6.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		panel_2.add(rdbtnBuff_6);

		JLabel lblEnemyAc = new JLabel("Enemy AC");
		lblEnemyAc.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblEnemyAc.setForeground(Color.WHITE);
		lblEnemyAc.setBounds(10, 207, 99, 19);
		panel_7.add(lblEnemyAc);

		acTextField = new JTextField();
		acTextField.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		acTextField.setBounds(127, 205, 33, 23);
		panel_7.add(acTextField);
		acTextField.setText("0");
		acTextField.setColumns(10);

		JTextArea outputTextArea = new JTextArea();
		outputTextArea.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		outputTextArea.setBounds(10, 238, 487, 216);
		panel_7.add(outputTextArea);
		outputTextArea.setForeground(new Color(51, 51, 51));
		outputTextArea.setBackground(new Color(255, 255, 255));
		outputTextArea.setEditable(false);

		JLabel lblChaName = new JLabel(character.getName());
		lblChaName.setBounds(12, 0, 198, 19);
		panel_6.add(lblChaName);
		lblChaName.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblChaName.setForeground(Color.WHITE);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 561, 1240, 122);
		contentPane.add(panel_3);
		panel_3.setBackground(Color.DARK_GRAY);

		JButton btnCalculateDpr = new JButton("Calculate DPR");
		btnCalculateDpr.setBackground(Color.LIGHT_GRAY);
		btnCalculateDpr.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 54));
		btnCalculateDpr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					outputTextArea.append("Dpr von " + getFullRoundByName(character, fullRoundChoice).getDescription()
							+ " = " + Calculation.calcDPR(getFullRoundByName(character, fullRoundChoice),
									Integer.parseInt(acTextField.getText()))
							+ "\n");
				} catch (Exception x) {

				}
			}
		});
		panel_3.add(btnCalculateDpr);

		JButton btnSimulateFullRound = new JButton("Simulate Full Round");
		btnSimulateFullRound.setBackground(Color.LIGHT_GRAY);
		btnSimulateFullRound.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 54));
		panel_3.add(btnSimulateFullRound);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(893, 67, 359, 466);
		contentPane.add(panel_1);

		JButton btnLoadCharacter = new JButton("Load character");
		btnLoadCharacter.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 30));
		btnLoadCharacter.setBackground(Color.LIGHT_GRAY);
		btnLoadCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
				fileChooser.showOpenDialog(null);
				character = Filesystem.load(fileChooser.getSelectedFile().getName());
				refreshFrame(lblChaName, lblChaBab, lblChaDex, lblChaStr, textAreaFeats, textAreaWeapons,
						fullRoundChoice);
			}
		});
		panel_1.add(btnLoadCharacter);

		JButton btnSaveCharacter = new JButton("Save character");
		btnSaveCharacter.setBackground(Color.LIGHT_GRAY);
		btnSaveCharacter.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 30));
		btnSaveCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filesystem.save(character);
			}
		});
		panel_1.add(btnSaveCharacter);
		
				JButton btnEditCharacter = new JButton("Edit Character");
				btnEditCharacter.setBackground(Color.LIGHT_GRAY);
				panel_1.add(btnEditCharacter);
				btnEditCharacter.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 30));
				btnEditCharacter.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						character = CharacterEditingDialog.main(character);
						refreshFrame(lblChaName, lblChaBab, lblChaDex, lblChaStr, textAreaFeats, textAreaWeapons,
								fullRoundChoice);
					}
				});
		
		JButton btnEditFullRounds = new JButton("Edit Fullrounds");
		btnEditFullRounds.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 30));
		btnEditFullRounds.setBackground(Color.LIGHT_GRAY);
		panel_1.add(btnEditFullRounds);

		JButton btnAddFullRound = new JButton("Add Fullround");
		btnAddFullRound.setBackground(Color.LIGHT_GRAY);
		panel_1.add(btnAddFullRound);
		btnAddFullRound.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 30));
		btnAddFullRound.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFullRoundDialog.main(character);
			}
		});
		
		JButton btnEditWeapons = new JButton("Edit Weapons");
		btnEditWeapons.setBackground(Color.LIGHT_GRAY);
		btnEditWeapons.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 30));
		panel_1.add(btnEditWeapons);

		JButton btnAddWeapon = new JButton("Add Weapon");
		btnAddWeapon.setBackground(Color.LIGHT_GRAY);
		panel_1.add(btnAddWeapon);
		btnAddWeapon.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 30));
		btnAddWeapon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					character.addWeapon(AddWeaponDialog.main(character));
					refreshFrame(lblChaName, lblChaBab, lblChaDex, lblChaStr, textAreaFeats, textAreaWeapons,
							fullRoundChoice);
				}catch(NullPointerException x){
					
				}
			}
		});

		JLabel lblMenu = new JLabel("menu:");
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 35));
		lblMenu.setBounds(893, 12, 252, 43);
		contentPane.add(lblMenu);

	}

	protected void refreshFrame(JLabel lblChaName, JLabel lblChaBab, JLabel lblChaDex, JLabel lblChaStr,
			TextArea textAreaFeats, TextArea textAreaWeapons, Choice fullRoundChoice) {
		lblChaName.setText(character.getName());
		lblChaBab.setText(String.valueOf(character.getBab()));
		lblChaDex.setText(String.valueOf(character.getDex()));
		lblChaStr.setText(String.valueOf(character.getStr()));
		textAreaFeats.setText("");
		for (String feat : character.getFeats()) {
			textAreaFeats.append(feat + "\n");
		}
		textAreaWeapons.setText(null);
		for (Weapon weapon : character.getWeapons()) {
			textAreaWeapons.append(weapon.getDescritpion() + "\n");
		}
		fullRoundChoice.removeAll();
		;
		for (FullRound fullround : character.getFullRounds()) {
			fullRoundChoice.add(fullround.getDescription() + "\n");
		}
	}

	private FullRound getFullRoundByName(PfCharacter character, Choice fullRoundChoice) {
		for (FullRound fullround : character.getFullRounds()) {
			if (fullround.getDescription().equals(fullRoundChoice.getSelectedItem()))
				return fullround;
		}
		return null;
	}

	public static void setCharacter(PfCharacter character2) {
		character = character2;
	}
}
