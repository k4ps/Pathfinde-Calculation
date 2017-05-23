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
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class AddWeaponDialog.
 */
public class AddWeaponDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Weapon weapon = new Weapon();

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			AddWeaponDialog dialog = new AddWeaponDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddWeaponDialog() {
		setBounds(100, 100, 697, 241);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 104, 149);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Weapon Type");
		lblNewLabel.setBounds(10, 11, 77, 33);
		panel_1.add(lblNewLabel);
		
		JLabel lblWeapon = new JLabel("Weapon");
		lblWeapon.setBounds(10, 50, 55, 20);
		panel_1.add(lblWeapon);
		
		JLabel lblSpecialAbilitiy = new JLabel("Special abilitiy");
		lblSpecialAbilitiy.setBounds(10, 115, 84, 22);
		panel_1.add(lblSpecialAbilitiy);
		
		JLabel lblEnhancement = new JLabel("enhancement");
		lblEnhancement.setBounds(10, 76, 84, 33);
		panel_1.add(lblEnhancement);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(126, 11, 545, 149);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 525, 33);
		panel_2.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JRadioButton twoHButton = new JRadioButton("2 Handed");
		panel.add(twoHButton);
		
		JRadioButton oneHButton = new JRadioButton("1 Handed");
		panel.add(oneHButton);
		
		JRadioButton offHButton = new JRadioButton("Off-Hand");
		panel.add(offHButton);
		
		JRadioButton rangeButton = new JRadioButton("Ranged");
		panel.add(rangeButton);
		
		JRadioButton primaryNaturalButton = new JRadioButton("Primary Natural");
		panel.add(primaryNaturalButton);
		
		JRadioButton secondaryNaturalButton = new JRadioButton("Secondary Natural");
		panel.add(secondaryNaturalButton);
		
		Choice choice = new Choice();
		choice.setBounds(10, 50, 525, 27);
		panel_2.add(choice);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_3.setBounds(10, 76, 525, 33);
		panel_2.add(panel_3);
		
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		panel_3.add(rdbtnNormal);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		panel_3.add(rdbtnM);
		
		JRadioButton radioButton = new JRadioButton("+1");
		panel_3.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("+2");
		panel_3.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("+3");
		panel_3.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("+4");
		panel_3.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("+5");
		panel_3.add(radioButton_4);
		
		JButton btnAddSpecial = new JButton("Add Special");
		btnAddSpecial.setBounds(428, 115, 107, 22);
		panel_2.add(btnAddSpecial);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(10, 115, 412, 22);
		panel_2.add(choice_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add Weapon");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
