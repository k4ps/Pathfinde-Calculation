package gui;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import characters.PfCharacter;

public class CharacterEditingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	/** The text field. */
	private JTextField nameField;

	/** The text field 1. */
	private JTextField babField;

	/** The text field 2. */
	private JTextField dexField;

	/** The text field 3. */
	private JTextField strField;

	private static ArrayList<String> implementedFeats = new ArrayList<>();
	private ArrayList<String> newFeatList = new ArrayList<>();
	
	private static PfCharacter editedCharacter;

	/**
	 * Launch the application.
	 */
	public static PfCharacter main(PfCharacter character) {
		try {
			CharacterEditingDialog dialog = new CharacterEditingDialog(character);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editedCharacter;
	}

	/**
	 * Create the dialog.
	 * @param character 
	 */
	public CharacterEditingDialog(PfCharacter character) {
		setModal(true);
		editedCharacter=character;
		newFeatList=character.getFeats();
		setTitle("Character Editing");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 451);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 78, 202);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(12, 12, 55, 22);
		panel.add(lblName);

		JLabel lblBab = new JLabel("BAB");
		lblBab.setBounds(12, 47, 55, 22);
		panel.add(lblBab);

		JLabel lblDex = new JLabel("Dex");
		lblDex.setBounds(12, 82, 55, 22);
		panel.add(lblDex);

		JLabel lblStr = new JLabel("Str");
		lblStr.setBounds(12, 117, 55, 22);
		panel.add(lblStr);

		JLabel lblFeats = new JLabel("Feats");
		lblFeats.setBounds(12, 152, 55, 22);
		panel.add(lblFeats);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(102, 12, 281, 311);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		nameField = new JTextField(character.getName());
		nameField.setBounds(12, 12, 162, 22);
		panel_1.add(nameField);
		nameField.setColumns(10);

		babField = new JTextField(String.valueOf(character.getBab()));
		babField.setBounds(12, 47, 60, 22);
		panel_1.add(babField);
		babField.setColumns(10);

		dexField = new JTextField(String.valueOf(character.getDex()));
		dexField.setBounds(12, 82, 60, 22);
		panel_1.add(dexField);
		dexField.setColumns(10);

		strField = new JTextField(String.valueOf(character.getStr()));
		strField.setBounds(12, 117, 60, 22);
		panel_1.add(strField);
		strField.setColumns(10);

		Choice featChoice = new Choice();
		for (String feat : implementedFeats) {
			if (!character.getFeats().contains(feat))
				featChoice.add(feat);
		}
		featChoice.setBounds(12, 152, 162, 22);
		panel_1.add(featChoice);

		List list = new List();
		list.setBounds(12, 187, 162, 98);
		for (String feat : editedCharacter.getFeats()) {
			list.add(feat);
		}
		panel_1.add(list);

		JButton btnAddFeat = new JButton("Add Feat");
		btnAddFeat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newFeatList.add(featChoice.getSelectedItem());
				list.add(featChoice.getSelectedItem());
				featChoice.remove(featChoice.getSelectedItem());
			}
		});
		btnAddFeat.setBounds(186, 152, 83, 22);
		panel_1.add(btnAddFeat);

		JButton btnDeleteFeat = new JButton("Remove Feat");
		btnDeleteFeat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newFeatList.remove(list.getSelectedIndex());
				featChoice.add(list.getSelectedItem());
				list.remove(list.getSelectedIndex());
			}
		});
		btnDeleteFeat.setBounds(186, 187, 83, 22);
		panel_1.add(btnDeleteFeat);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 334, 371, 36);
		contentPanel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnCreateCharacter = new JButton("Edit Character");
		btnCreateCharacter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editedCharacter.setName(nameField.getText());
				editedCharacter.setBab(Integer.parseInt(babField.getText()));
				editedCharacter.setStr(Integer.parseInt(strField.getText()));
				editedCharacter.setDex(Integer.parseInt(dexField.getText()));
				editedCharacter.setFeats(newFeatList);
				dispose();
			}
		});
		panel_2.add(btnCreateCharacter);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editedCharacter = character;
				dispose();
			}
		});
		panel_2.add(btnCancel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { contentPanel, panel, lblName, lblBab, lblDex,
				lblStr, lblFeats, panel_1, nameField, babField, dexField, strField, btnAddFeat }));
	}

}
