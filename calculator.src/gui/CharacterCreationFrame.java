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
import java.awt.Component;
import java.awt.FlowLayout;

public class CharacterCreationFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtTwoweaponFighting;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setTitle("Character Creation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 308);
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
		panel_1.setBounds(102, 12, 281, 202);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 12, 162, 26);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 50, 60, 26);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(12, 88, 60, 26);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(12, 126, 60, 26);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		txtTwoweaponFighting = new JTextField();
		txtTwoweaponFighting.setBounds(12, 164, 162, 26);
		panel_1.add(txtTwoweaponFighting);
		txtTwoweaponFighting.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Feat");
		btnNewButton.setBounds(186, 164, 83, 26);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 226, 371, 36);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreateCharacter = new JButton("Create Character");
		panel_2.add(btnCreateCharacter);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, panel, lblName, lblBab, lblDex, lblStr, lblFeats, panel_1, textField, textField_1, textField_2, textField_3, txtTwoweaponFighting, btnNewButton}));
	}
}
