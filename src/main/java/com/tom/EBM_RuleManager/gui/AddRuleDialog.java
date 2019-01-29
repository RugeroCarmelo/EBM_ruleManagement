package com.tom.EBM_RuleManager.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tom.EBM_RuleManager.Listeners.AddRuleEvent;
import com.tom.EBM_RuleManager.Listeners.AddRuleListener;
import com.tom.EBM_RuleManager.readXML.ReadXML;

public class AddRuleDialog extends JDialog {
	private JLabel ruleName;
	private JLabel topic;
	private JLabel information;
	private JTextField ruleLocation;
	private JTextField ruleNameField;
	private JTextField topicField;
	private JScrollPane informationField;
	private JTextArea informationArea;
	private JFileChooser fileChooser;
	private JButton loadBtn;
	private JButton saveBtn;
	private String fileLocation;
	private AddRuleListener listener;
	private String id;
	private File file;
	private static JFrame frame = null;

	public AddRuleDialog(JFileChooser fileChooser) {
		super(frame, "Add a new Rule", false);
		this.fileChooser = fileChooser;
		ruleName = new JLabel("*Name of the rule: ");
		topic = new JLabel("*Topic of the rule: ");
		information = new JLabel("Description: ");
		ruleLocation = new JTextField(20);
		ruleLocation.setText("*No file loaded");
		ruleLocation.setEditable(false);
		ruleLocation.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		ruleNameField = new JTextField(20);
		topicField = new JTextField(20);
		informationArea = new JTextArea(5, 20);
		informationField = new JScrollPane(informationArea);
		informationArea.setLineWrap(true);
		//informationField.setBorder(BorderFactory.createEtchedBorder());
		loadBtn = new JButton("*Load");
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showOpenDialog(AddRuleDialog.this) == JFileChooser.APPROVE_OPTION) {
					fileLocation = fileChooser.getSelectedFile().toString();
					if (checkExtension(fileLocation)) {
						ruleLocation.setText(fileLocation);// gets the string of the location of the file
						ReadXML readXML = new ReadXML(fileLocation);
						file = readXML.getFile();
						id = readXML.getFileId();
						ruleNameField.setText(readXML.getFileName());
					} else {
						fileLocation = null;
						JOptionPane.showMessageDialog(AddRuleDialog.this, "This file is not in the right Format",
								"Error", JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		saveBtn = new JButton("Add");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ruleNameField.getText().length() > 0 && topicField.getText().length() > 0 /*&& fileLocation != null*/) {
					if(file != null) {
						AddRuleEvent event = new AddRuleEvent(this, fileLocation, topicField.getText(), ruleNameField.getText(), informationArea.getText(), id, file);
						if (listener != null) {
							listener.addRuleEventOccurred(event);
						}
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(AddRuleDialog.this, "Something went wrong when opening the file",
								"Error", JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(AddRuleDialog.this, "Please fill in the missing fields (marked with '*')",
							"Error", JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		formatComponents();

		setSize(new Dimension(450, 350));
	}

	private void formatComponents() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// First row
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(loadBtn, gc);

		//
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ruleLocation, gc);

		// 3rd row
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(ruleName, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ruleNameField, gc);

		// Second row
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(topic, gc);

		gc.gridx = 1;
		// gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(topicField, gc);

		// 3rd row
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(information, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(informationField, gc);

		// 3rd row
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		// add(ruleName, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(saveBtn, gc);

	}

	private boolean checkExtension(String url) {
		String extension = Utils.getFileExtension(url);
		if (extension == null) {
			return false;
		}

		if (extension.equals("dmn")) {
			return true;
		}
		return false;
	}

	public void setAddRuleListener(AddRuleListener listener) {
		this.listener = listener;
	}
}
