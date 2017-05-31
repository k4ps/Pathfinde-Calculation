/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Sch�n
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextField;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import characters.PfCharacter;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComboBox;
import java.awt.List;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.DefaultComboBoxModel;

// TODO: Auto-generated Javadoc
/**
 * The Class CharacterCreationFrame.
 */
public class CharacterCreationFrame extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/** The text field. */
	private JTextField nameField;

	/** The text field 1. */
	private JTextField babField;

	/** The text field 2. */
	private JTextField dexField;

	/** The text field 3. */
	private JTextField strField;
	
	private static ArrayList<String> implementedFeats = new ArrayList<>();

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
					getImplementedFeats();
					CharacterCreationFrame frame = new CharacterCreationFrame();
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
	public CharacterCreationFrame() {
		setResizable(false);
		PfCharacter createdCharacter = new PfCharacter();
		setTitle("Character Creation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 78, 202);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(12, 12, 55, 26);
		panel.add(lblName);

		JLabel lblBab = new JLabel("BAB");
		lblBab.setBounds(12, 50, 55, 26);
		panel.add(lblBab);

		JLabel lblDex = new JLabel("Dex");
		lblDex.setBounds(12, 88, 55, 26);
		panel.add(lblDex);

		JLabel lblStr = new JLabel("Str");
		lblStr.setBounds(12, 126, 55, 26);
		panel.add(lblStr);

		JLabel lblFeats = new JLabel("Feats");
		lblFeats.setBounds(12, 164, 55, 26);
		panel.add(lblFeats);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(102, 12, 281, 310);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		nameField = new JTextField();
		nameField.setBounds(12, 12, 162, 26);
		panel_1.add(nameField);
		nameField.setColumns(10);

		babField = new JTextField();
		babField.setBounds(12, 50, 60, 26);
		panel_1.add(babField);
		babField.setColumns(10);

		dexField = new JTextField();
		dexField.setBounds(12, 88, 60, 26);
		panel_1.add(dexField);
		dexField.setColumns(10);

		strField = new JTextField();
		strField.setBounds(12, 126, 60, 26);
		panel_1.add(strField);
		strField.setColumns(10);
		
		Choice featChoice = new Choice();
		for (String feat : implementedFeats) {
				featChoice.add(feat);
		}
		featChoice.setBounds(12, 168, 162, 22);
		panel_1.add(featChoice);
		
		List selectedFeatsList = new List();
		selectedFeatsList.setMultipleMode(false);
		selectedFeatsList.setBounds(12, 196, 257, 64);
		panel_1.add(selectedFeatsList);

		JButton btnNewButton = new JButton("Add Feat");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createdCharacter.addFeat(featChoice.getSelectedItem());
				selectedFeatsList.add(featChoice.getSelectedItem());
				featChoice.remove(featChoice.getSelectedItem());
			}
		});
		btnNewButton.setBounds(186, 164, 83, 26);
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 334, 371, 36);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnCreateCharacter = new JButton("Create Character");
		btnCreateCharacter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createdCharacter.setName(nameField.getText());
				createdCharacter.setBab(Integer.parseInt(babField.getText()));
				createdCharacter.setStr(Integer.parseInt(strField.getText()));
				createdCharacter.setDex(Integer.parseInt(dexField.getText()));
				MainFrame.main(null, createdCharacter);
				dispose();
			}
		});
		panel_2.add(btnCreateCharacter);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartFrame.main(null);
				dispose();
			}
		});
		panel_2.add(btnCancel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, panel, lblName, lblBab, lblDex, lblStr, lblFeats, panel_1, nameField, babField, dexField, strField, btnNewButton}));
	}
	
	private static void getImplementedFeats() {
		implementedFeats.add("Weapon Finesse");
		implementedFeats.add("Power Attack");
		implementedFeats.add("Rapid Shot");
		implementedFeats.add("Flury of Blows");
		implementedFeats.add("Point-Blank Shot");
		Collections.sort(implementedFeats);
	}
}
