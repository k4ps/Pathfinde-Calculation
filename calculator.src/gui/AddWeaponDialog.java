/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import weapons.Weapon;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Choice;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import characters.PfCharacter;
import main.Filesystem;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class AddWeaponDialog.
 */
public class AddWeaponDialog extends JDialog {

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();

	/** The weapon. */
	private static Weapon choosenWeapon = new Weapon();
	
	private static int enhancement;
	
	private static String filename;
	
	private static ArrayList<Weapon> viableWeapons = new ArrayList<>();

	/**
	 * Launch the application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static Weapon main(PfCharacter character) {
		try {
			AddWeaponDialog dialog = new AddWeaponDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return choosenWeapon;
	}

	/**
	 * Create the dialog.
	 */
	public AddWeaponDialog() {
		setFont(new Font("Razer Header Regular Oblique", Font.PLAIN, 20));
		setBackground(Color.DARK_GRAY);
		setModal(true);
		setBounds(100, 100, 826, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(10, 11, 168, 164);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Weapon Type");
		lblNewLabel.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 17, 130, 19);
		panel_1.add(lblNewLabel);

		JLabel lblWeapon = new JLabel("Weapon");
		lblWeapon.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblWeapon.setForeground(Color.WHITE);
		lblWeapon.setBounds(10, 52, 78, 19);
		panel_1.add(lblWeapon);

		JLabel lblSpecialAbilitiy = new JLabel("Special abilitiy");
		lblSpecialAbilitiy.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblSpecialAbilitiy.setForeground(Color.WHITE);
		lblSpecialAbilitiy.setBounds(10, 127, 146, 19);
		panel_1.add(lblSpecialAbilitiy);

		JLabel lblEnhancement = new JLabel("Enhancement");
		lblEnhancement.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblEnhancement.setForeground(Color.WHITE);
		lblEnhancement.setBounds(10, 87, 135, 19);
		panel_1.add(lblEnhancement);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBorder(null);
		panel_2.setBounds(190, 11, 610, 164);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 10, 590, 33);
		panel_2.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		Choice choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				for(Weapon weapon:viableWeapons){
					if(weapon.getName().equals(choice.getSelectedItem())){
						choosenWeapon=weapon;
					}
				}
			}
		});
		choice.setForeground(Color.WHITE);
		choice.setBackground(Color.DARK_GRAY);
		choice.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		choice.setBounds(10, 49, 590, 22);
		panel_2.add(choice);

		JRadioButton twoHButton = new JRadioButton("2 Handed");
		twoHButton.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		twoHButton.setForeground(Color.WHITE);
		twoHButton.setBackground(Color.DARK_GRAY);
		twoHButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				refreshChoice(0, choice);
			}
		});
		panel.add(twoHButton);

		JRadioButton oneHButton = new JRadioButton("1 Handed");
		oneHButton.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		oneHButton.setForeground(Color.WHITE);
		oneHButton.setBackground(Color.DARK_GRAY);
		panel.add(oneHButton);

		JRadioButton offHButton = new JRadioButton("Off-Hand");
		offHButton.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		offHButton.setForeground(Color.WHITE);
		offHButton.setBackground(Color.DARK_GRAY);
		panel.add(offHButton);

		JRadioButton rangeButton = new JRadioButton("Ranged");
		rangeButton.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rangeButton.setForeground(Color.WHITE);
		rangeButton.setBackground(Color.DARK_GRAY);
		panel.add(rangeButton);

		JRadioButton primaryNaturalButton = new JRadioButton("Primary Natural");
		primaryNaturalButton.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		primaryNaturalButton.setForeground(Color.WHITE);
		primaryNaturalButton.setBackground(Color.DARK_GRAY);
		panel.add(primaryNaturalButton);

		JRadioButton secondaryNaturalButton = new JRadioButton("Secondary Natural");
		secondaryNaturalButton.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		secondaryNaturalButton.setForeground(Color.WHITE);
		secondaryNaturalButton.setBackground(Color.DARK_GRAY);
		panel.add(secondaryNaturalButton);

		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.WHITE);
		panel_3.setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_3.setBounds(10, 80, 590, 33);
		panel_2.add(panel_3);

		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rdbtnNormal.setForeground(Color.WHITE);
		rdbtnNormal.setBackground(Color.DARK_GRAY);
		panel_3.add(rdbtnNormal);

		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		rdbtnM.setForeground(Color.WHITE);
		rdbtnM.setBackground(Color.DARK_GRAY);
		panel_3.add(rdbtnM);

		JRadioButton radioButton = new JRadioButton("+1");
		radioButton.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		radioButton.setForeground(Color.WHITE);
		radioButton.setBackground(Color.DARK_GRAY);
		panel_3.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("+2");
		radioButton_1.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setBackground(Color.DARK_GRAY);
		panel_3.add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("+3");
		radioButton_2.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		radioButton_2.setForeground(Color.WHITE);
		radioButton_2.setBackground(Color.DARK_GRAY);
		panel_3.add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("+4");
		radioButton_3.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		radioButton_3.setForeground(Color.WHITE);
		radioButton_3.setBackground(Color.DARK_GRAY);
		panel_3.add(radioButton_3);

		JRadioButton radioButton_4 = new JRadioButton("+5");
		radioButton_4.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		radioButton_4.setForeground(Color.WHITE);
		radioButton_4.setBackground(Color.DARK_GRAY);
		panel_3.add(radioButton_4);
		
		
		//Action Listener for all of the enhancement buttons
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enhancement=-1;
				rdbtnM.setSelected(false);
				radioButton.setSelected(false);
				radioButton_1.setSelected(false);
				radioButton_2.setSelected(false);
				radioButton_3.setSelected(false);
				radioButton_4.setSelected(false);
			}
		});
		rdbtnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enhancement=0;
				rdbtnNormal.setSelected(false);
				radioButton.setSelected(false);
				radioButton_1.setSelected(false);
				radioButton_2.setSelected(false);
				radioButton_3.setSelected(false);
				radioButton_4.setSelected(false);
			}
		});
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enhancement=1;
				rdbtnNormal.setSelected(false);
				rdbtnM.setSelected(false);
				radioButton_1.setSelected(false);
				radioButton_2.setSelected(false);
				radioButton_3.setSelected(false);
				radioButton_4.setSelected(false);
			}
		});
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enhancement=2;
				rdbtnNormal.setSelected(false);
				rdbtnM.setSelected(false);
				radioButton.setSelected(false);
				radioButton_2.setSelected(false);
				radioButton_3.setSelected(false);
				radioButton_4.setSelected(false);
			}
		});
		radioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enhancement=3;
				rdbtnNormal.setSelected(false);
				rdbtnM.setSelected(false);
				radioButton.setSelected(false);
				radioButton_1.setSelected(false);
				radioButton_3.setSelected(false);
				radioButton_4.setSelected(false);
			}
		});
		radioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enhancement=4;
				rdbtnNormal.setSelected(false);
				rdbtnM.setSelected(false);
				radioButton.setSelected(false);
				radioButton_1.setSelected(false);
				radioButton_2.setSelected(false);
				radioButton_4.setSelected(false);
			}
		});
		radioButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enhancement=5;
				rdbtnNormal.setSelected(false);
				rdbtnM.setSelected(false);
				radioButton.setSelected(false);
				radioButton_1.setSelected(false);
				radioButton_2.setSelected(false);
				radioButton_3.setSelected(false);
			}
		});

		JButton btnAddSpecial = new JButton("Add Special");
		btnAddSpecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddSpecial.setBackground(Color.LIGHT_GRAY);
		btnAddSpecial.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 20));
		btnAddSpecial.setBounds(425, 119, 175, 35);
		panel_2.add(btnAddSpecial);

		Choice choice_1 = new Choice();
		choice_1.setForeground(Color.WHITE);
		choice_1.setBackground(Color.DARK_GRAY);
		choice_1.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		choice_1.setBounds(10, 124, 380, 25);
		panel_2.add(choice_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setForeground(Color.WHITE);
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add Weapon");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						choosenWeapon.setEnhancement(enhancement);
						dispose();
					}
				});
				okButton.setBackground(Color.LIGHT_GRAY);
				okButton.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 20));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						choosenWeapon=null;
						dispose();
					}
				});
				cancelButton.setBackground(Color.LIGHT_GRAY);
				cancelButton.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 20));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void refreshChoice(int i, Choice choice) {
		switch (i) {
		case (0): {
			choice.removeAll();
			filename="twoHandedWeapons.txt";
			getWeaponList();
			for(Weapon weapon:viableWeapons){
				choice.add(weapon.getName());
			}
		}
		}

	}

	private void getWeaponList() {
		Path filepath = FileSystems.getDefault().getPath(filename);
		viableWeapons.removeAll(viableWeapons);
		String line;
		try (BufferedReader reader = Files.newBufferedReader(filepath);) {
			while((line=reader.readLine())!=null){
				viableWeapons.add(Filesystem.readWeapon(line));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
