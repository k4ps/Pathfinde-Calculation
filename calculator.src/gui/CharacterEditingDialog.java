package gui;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import characters.PfCharacter;
import java.awt.Color;
import java.awt.Font;

public class CharacterEditingDialog extends JDialog {

	private final JPanel characterEditingPanel = new JPanel();

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
		getImplementedFeats();
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
	 * 
	 * @param character
	 */
	public CharacterEditingDialog(PfCharacter character) {
		setBackground(Color.DARK_GRAY);
		setModal(true);
		editedCharacter = character;
		newFeatList = character.getFeats();
		setTitle("Character Editing");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 394);
		characterEditingPanel.setForeground(Color.WHITE);
		characterEditingPanel.setBackground(Color.DARK_GRAY);
		characterEditingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(characterEditingPanel);
		characterEditingPanel.setLayout(null);

		JPanel characterEditingPanel_1 = new JPanel();
		characterEditingPanel_1.setBackground(Color.DARK_GRAY);
		characterEditingPanel_1.setBounds(12, 12, 78, 182);
		characterEditingPanel.add(characterEditingPanel_1);
		characterEditingPanel_1.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(12, 12, 59, 19);
		characterEditingPanel_1.add(lblName);

		JLabel lblBab = new JLabel("BAB");
		lblBab.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblBab.setForeground(Color.WHITE);
		lblBab.setBounds(12, 47, 41, 19);
		characterEditingPanel_1.add(lblBab);

		JLabel lblDex = new JLabel("Dex");
		lblDex.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblDex.setForeground(Color.WHITE);
		lblDex.setBounds(12, 82, 37, 19);
		characterEditingPanel_1.add(lblDex);

		JLabel lblStr = new JLabel("Str");
		lblStr.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblStr.setForeground(Color.WHITE);
		lblStr.setBounds(12, 117, 30, 19);
		characterEditingPanel_1.add(lblStr);

		JLabel lblFeats = new JLabel("Feats");
		lblFeats.setFont(new Font("Razer Text Regular", Font.BOLD, 20));
		lblFeats.setForeground(Color.WHITE);
		lblFeats.setBounds(12, 152, 56, 19);
		characterEditingPanel_1.add(lblFeats);

		JPanel characterEditingPanel_2 = new JPanel();
		characterEditingPanel_2.setBackground(Color.DARK_GRAY);
		characterEditingPanel_2.setBounds(102, 12, 291, 182);
		characterEditingPanel.add(characterEditingPanel_2);
		characterEditingPanel_2.setLayout(null);

		nameField = new JTextField(character.getName());
		nameField.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		nameField.setBounds(12, 12, 269, 22);
		characterEditingPanel_2.add(nameField);
		nameField.setColumns(10);

		babField = new JTextField(String.valueOf(character.getBab()));
		babField.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		babField.setBounds(12, 47, 30, 23);
		characterEditingPanel_2.add(babField);
		babField.setColumns(10);

		dexField = new JTextField(String.valueOf(character.getDex()));
		dexField.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		dexField.setBounds(12, 82, 30, 23);
		characterEditingPanel_2.add(dexField);
		dexField.setColumns(10);

		strField = new JTextField(String.valueOf(character.getStr()));
		strField.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		strField.setBounds(12, 117, 30, 23);
		characterEditingPanel_2.add(strField);
		strField.setColumns(10);

		Choice featChoice = new Choice();
		featChoice.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		for (String feat : implementedFeats) {
			if (!(editedCharacter.getFeats().contains(feat)))
				featChoice.add(feat);
		}
		featChoice.setBounds(12, 152, 269, 25);
		characterEditingPanel_2.add(featChoice);

		JPanel characterEditingPanel_3 = new JPanel();
		characterEditingPanel_3.setBackground(Color.DARK_GRAY);
		characterEditingPanel_3.setBounds(12, 304, 381, 45);
		characterEditingPanel.add(characterEditingPanel_3);
		characterEditingPanel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		List list = new List();
		list.setBackground(Color.DARK_GRAY);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Razer Text Regular", Font.PLAIN, 20));
		list.setBounds(12, 200, 192, 98);
		characterEditingPanel.add(list);
		for (String feat : editedCharacter.getFeats()) {
			list.add(feat);
		}

		JButton btnDeleteFeat = new JButton("Remove Feat");
		btnDeleteFeat.setBackground(Color.LIGHT_GRAY);
		btnDeleteFeat.setBounds(210, 252, 183, 35);
		characterEditingPanel.add(btnDeleteFeat);
		btnDeleteFeat.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 20));
		btnDeleteFeat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newFeatList.remove(list.getSelectedIndex());
				featChoice.add(list.getSelectedItem());
				list.remove(list.getSelectedIndex());
			}
		});

		JButton btnAddFeat = new JButton("Add Feat");
		btnAddFeat.setBackground(Color.LIGHT_GRAY);
		btnAddFeat.setBounds(210, 211, 183, 35);
		characterEditingPanel.add(btnAddFeat);
		btnAddFeat.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 20));
		btnAddFeat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (featChoice.getSelectedItem() != null) {
						newFeatList.add(featChoice.getSelectedItem());
						list.add(featChoice.getSelectedItem());
						featChoice.remove(featChoice.getSelectedItem());
					}
				} catch (Exception x) {

				}
			}
		});
		
		JButton btnCreateCharacter = new JButton("Edit Character");
		btnCreateCharacter.setBackground(Color.LIGHT_GRAY);
		btnCreateCharacter.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 20));
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
		characterEditingPanel_3.add(btnCreateCharacter);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.LIGHT_GRAY);
		btnCancel.setFont(new Font("Razer Header Regular Oblique", Font.BOLD, 20));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editedCharacter = character;
				dispose();
			}
		});
		characterEditingPanel_3.add(btnCancel);
	}

	private static void getImplementedFeats() {
		implementedFeats.add("Weapon Finesse");
		implementedFeats.add("Power Attack");
		implementedFeats.add("Rapid Shot");
		implementedFeats.add("Flury of Blows");
		implementedFeats.add("Point-Blank Shot");
	}

}
