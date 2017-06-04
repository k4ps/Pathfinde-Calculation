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

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.awt.Color;

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
		setExtendedState(MAXIMIZED_BOTH);
		//setResizable(false);
		setTitle("Pathfinder Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 35, 78, 167);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCharacterName = new JLabel("Character:");
		lblCharacterName.setBounds(10, 11, 62, 26);
		panel.add(lblCharacterName);

		JLabel lblFullRounds = new JLabel("Full Rounds");
		lblFullRounds.setBounds(10, 48, 68, 26);
		panel.add(lblFullRounds);

		JLabel lblBuffs = new JLabel("Buffs");
		lblBuffs.setBounds(10, 86, 46, 26);
		panel.add(lblBuffs);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(100, 35, 431, 167);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel characterNameLabel = new JLabel(character.getName());
		characterNameLabel.setBounds(10, 11, 119, 26);
		panel_1.add(characterNameLabel);

		Choice fullRoundChoice = new Choice();
		fullRoundChoice.setBounds(10, 50, 283, 22);
		for (FullRound fullround : character.getFullRounds()) {
			fullRoundChoice.add(fullround.getDescription());
		}
		panel_1.add(fullRoundChoice);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 86, 408, 75);
		panel_1.add(panel_2);

		JRadioButton rdbtnBuff = new JRadioButton("Buff 1");
		panel_2.add(rdbtnBuff);

		JRadioButton rdbtnBuff_1 = new JRadioButton("Buff 2");
		panel_2.add(rdbtnBuff_1);

		JRadioButton rdbtnBuff_2 = new JRadioButton("Buff 3");
		panel_2.add(rdbtnBuff_2);

		JRadioButton rdbtnBuff_3 = new JRadioButton("Buff 4");
		panel_2.add(rdbtnBuff_3);

		JRadioButton rdbtnBuff_4 = new JRadioButton("Buff 5");
		panel_2.add(rdbtnBuff_4);

		JRadioButton rdbtnBuff_5 = new JRadioButton("Buff 6");
		panel_2.add(rdbtnBuff_5);

		acTextField = new JTextField();
		acTextField.setText("0");
		acTextField.setBounds(356, 11, 62, 26);
		panel_1.add(acTextField);
		acTextField.setColumns(10);

		JButton btnAddFullRound = new JButton("Add Full Round");
		btnAddFullRound.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFullRoundDialog.main(character);
			}
		});
		btnAddFullRound.setBounds(299, 48, 119, 26);
		panel_1.add(btnAddFullRound);
		
		JButton btnEditCharacter = new JButton("Edit Character");
		btnEditCharacter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				character=CharacterEditingDialog.main(character);
				characterNameLabel.setText(character.getName());
				for (FullRound fullround : character.getFullRounds()) {
					fullRoundChoice.add(fullround.getDescription());
				}
			}
		});
		btnEditCharacter.setBounds(139, 11, 119, 26);
		panel_1.add(btnEditCharacter);

		JLabel lblEnemyAc = new JLabel("Enemy AC");
		lblEnemyAc.setBounds(276, 11, 62, 26);
		panel_1.add(lblEnemyAc);

		JTextArea outputTextArea = new JTextArea();
		outputTextArea.setForeground(new Color(51, 51, 51));
		outputTextArea.setBackground(new Color(255, 255, 255));
		outputTextArea.setEditable(false);
		outputTextArea.setBounds(10, 544, 519, 139);
		contentPane.add(outputTextArea);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(541, 647, 519, 36);
		contentPane.add(panel_3);

		JButton btnCalculateDpr = new JButton("Calculate DPR");
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
		panel_3.add(btnSimulateFullRound);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, this.getBounds().width-2, 26);
		contentPane.add(menuBar);

		JMenu mnDatei = new JMenu("File");
		menuBar.add(mnDatei);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
				fileChooser.showOpenDialog(null);
				character=Filesystem.load(fileChooser.getSelectedFile().getName());
				characterNameLabel.setText(character.getName());
				for (FullRound fullround : character.getFullRounds()) {
					fullRoundChoice.add(fullround.getDescription());
				}
			}
		});
		mnDatei.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filesystem.save(character);
			}
		});
		mnDatei.add(mntmSave);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(803, 38, 459, 570);
		contentPane.add(panel_4);
	}

	private FullRound getFullRoundByName(PfCharacter character, Choice fullRoundChoice) {
		for (FullRound fullround : character.getFullRounds()) {
			if (fullround.getDescription().equals(fullRoundChoice.getSelectedItem()))
				return fullround;
		}
		return null;
	}
	
	public static void setCharacter(PfCharacter character2){
		character = character2;
	}
}
