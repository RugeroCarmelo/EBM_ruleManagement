package com.tom.EBM_RuleManager.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.tom.EBM_RuleManager.Listeners.AddRelationEvent;
import com.tom.EBM_RuleManager.Listeners.AddRelationListener;
import com.tom.EBM_RuleManager.Model.Relation;
import com.tom.EBM_RuleManager.Model.Rule;

public class AddRelationDialog extends JDialog {
	private AddRelationListener listener;
	private JLabel name;
	private JTextField nameField;
	private JButton addBtn;
	private JTextField rulesField;
	private JButton addRulesBtn;
	List<Rule> db;
	ArrayList<Rule> addedRules;
	RelationRuleDialog dialog;
	private static JFrame frame;

	private JTable table;
	private RuleTableModel tableModel;

	public AddRelationDialog(List<Rule> db) {
		super(frame, "Add a new Relation", false);
		dialog = new RelationRuleDialog(db);//
		this.db = db;
		this.addedRules = new ArrayList<Rule>();
		name = new JLabel("*Relation name: ");
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().length() > 0) {///needs to be changed to get all the values in the list
					AddRelationEvent event = new AddRelationEvent(this, nameField.getText(), addedRules);
					if (listener != null) {
						listener.AddRelationEventOccurred(event);
					}
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(AddRelationDialog.this,
							"Please fill in the missing fields (marked with '*')", "Error",
							JOptionPane.OK_OPTION | JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		nameField = new JTextField(20);
		nameField.setEditable(false);
		rulesField = new JTextField(20);
		rulesField.setText("*No file loaded");
		rulesField.setEditable(false);
		rulesField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		addRulesBtn = new JButton("Add Rules");
		addRulesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelationRuleDialog dialog= new RelationRuleDialog(db);
				dialog.setAddRelationListener(new AddRelationListener() {
					public void AddRelationEventOccurred(AddRelationEvent event) {
						addedRules = event.getRules();
						setData(addedRules);
						tableModel.fireTableDataChanged();
					}
				});
				dialog.setVisible(true);
			}
		});

		makeTable();

		formatComponents();

		setSize(new Dimension(500, 600));
		setMinimumSize(new Dimension(500, 600));
		
		
	}

	private void makeTable() {
		tableModel = new RuleTableModel();
		setData(addedRules);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	public void setData(List<Rule> db) {
		tableModel.setData(db);
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
		add(name, gc);

		//
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

		// 3rd row
		gc.gridy++;
		gc.weightx = 0;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.gridwidth = 2;
		// gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.PAGE_START;
		add(addRulesBtn, gc);

		// 3rd row
		gc.gridy++;
		gc.weightx = 0;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.gridwidth = 2;
		// gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.PAGE_START;
		add(new JScrollPane(table), gc);

		// 3rd row
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(addBtn, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		// add(rulesField, gc);

	}

	public void setAddRelationListener(AddRelationListener listener) {
		this.listener = listener;
	}
	
	public void setNameField(String name){
		nameField.setText(name);
	}
}
