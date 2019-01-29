package com.tom.EBM_RuleManager.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.tom.EBM_RuleManager.Listeners.AddRelationEvent;
import com.tom.EBM_RuleManager.Listeners.AddRelationListener;
import com.tom.EBM_RuleManager.Model.Rule;

public class RelationRuleDialog extends JDialog {
	private AddRelationListener listener;
	private JButton loadBtn;
	List<Rule> db;

	private JTable table;
	private RuleTableModel tableModel;

	public RelationRuleDialog(List<Rule> db) {
		//super(parent);
		super();
		this.db = db;
		makeTable();
		loadBtn = new JButton("Add Rules");
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selected = table.getSelectedRows();
				ArrayList<Rule> rules = new ArrayList<Rule>();
				for (int i = 0; i < selected.length; i++) {
					rules.add(db.get(selected[i]));
				}

				AddRelationEvent event = new AddRelationEvent(this, "", rules);
				if (listener != null) {
					listener.AddRelationEventOccurred(event);
				}
				setVisible(false);
			}
		});

		formatComponents();
		setSize(new Dimension(500, 600));
		setMinimumSize(new Dimension(500, 600));
	}

	private void makeTable() {
		tableModel = new RuleTableModel();
		setData(db);
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
		// add(name, gc);

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
		gc.weightx = 0;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.gridwidth = 2;
		// gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.PAGE_START;
		add(loadBtn, gc);

	}
	public void setAddRelationListener(AddRelationListener listener) {
		this.listener = listener;
	}
}
