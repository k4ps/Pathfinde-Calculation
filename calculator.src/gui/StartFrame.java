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
import javax.swing.JLabel;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class StartFrame.
 */
public class StartFrame extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
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
	public StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 116);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWouldYouLike = new JLabel("Would you like to create a new Character or load an existing one?");
		lblWouldYouLike.setBounds(10, 11, 371, 14);
		contentPane.add(lblWouldYouLike);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 371, 37);
		contentPane.add(panel);
		
		//clicking this button will open the CharacterCreationDialog and dispose this frame.
		JButton btnNewCharacter = new JButton("New Character");
		panel.add(btnNewCharacter);
		
		//clicking this button will open a data manager to load a character. Upon selection of one, it will open the mainframe with the selected character and dispose this frame.
		JButton btnLoadCharacter = new JButton("Load Character");
		panel.add(btnLoadCharacter);
	}
}
