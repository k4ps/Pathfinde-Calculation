/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import attacks.FullRound;
import calculations.Calculation;
import characters.PfCharacter;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

// TODO: Auto-generated Javadoc
// TODO: Output panel
/**
 * The Class MainFrame.
 */
public class MainFrame extends JFrame {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The text field. */
	private JTextField acTextField;
	
	private PfCharacter character;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args, PfCharacter character) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(character);
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
	public MainFrame(PfCharacter character) {
		setResizable(false);
		setTitle("Pathfinder Calculator");
		this.character=character;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 78, 167);
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
		panel_1.setBounds(98, 11, 431, 167);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel characterNameLabel = new JLabel(character.getName());
		characterNameLabel.setBounds(10, 11, 119, 26);
		panel_1.add(characterNameLabel);
		
		JButton btnEditCharacter = new JButton("Edit Character");
		btnEditCharacter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CharacterEditingFrame.main(character);
				dispose();
			}
		});
		btnEditCharacter.setBounds(139, 11, 119, 26);
		panel_1.add(btnEditCharacter);
		
		Choice fullRoundChoice = new Choice();
		fullRoundChoice.setBounds(10, 50, 283, 22);
		for(FullRound fullround:character.getFullRounds()){
			fullRoundChoice.add(fullround.getName());
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
		btnAddFullRound.setBounds(299, 48, 119, 26);
		panel_1.add(btnAddFullRound);
		
		JLabel lblEnemyAc = new JLabel("Enemy AC");
		lblEnemyAc.setBounds(276, 11, 62, 26);
		panel_1.add(lblEnemyAc);
		
		JTextArea outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		outputTextArea.setBounds(10, 238, 519, 139);
		contentPane.add(outputTextArea);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 190, 519, 36);
		contentPane.add(panel_3);
		
		JButton btnCalculateDpr = new JButton("Calculate DPR");
		btnCalculateDpr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				outputTextArea.setText(outputTextArea.getText() + "Dpr von " + getFullRoundByName(character, fullRoundChoice).getName() + " = " +  Calculation.calcDPR(getFullRoundByName(character, fullRoundChoice), Integer.parseInt(acTextField.getText())));
			}
		});
		panel_3.add(btnCalculateDpr);
		
		JButton btnSimulateFullRound = new JButton("Simulate Full Round");
		panel_3.add(btnSimulateFullRound);
	}
	
	private FullRound getFullRoundByName(PfCharacter character, Choice fullRoundChoice) {
		for(FullRound fullround:character.getFullRounds()){
			if(fullround.getName().equals(fullRoundChoice.getSelectedItem())) return fullround;
		}
		return null;
	}
}
