package com.tom.EBM_RuleManager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.tom.EBM_RuleManager.Listeners.RuleTableListener;
import com.tom.EBM_RuleManager.Model.Relation;

public class RelationTablePanel extends JPanel {
	private JTable table;
	private RelationTableModel tableModel;
	private RuleTableListener ruleTableListener;
	private JPopupMenu popup;

	public RelationTablePanel(List<Relation> db) {
		tableModel = new RelationTableModel();
		setData(db);
		popup = new JPopupMenu();
		createPopupMenu(popup);
		table = new JTable(tableModel);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				if(e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});

		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public void setData(List<Relation> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	
	private void createPopupMenu(JPopupMenu popup) {
		JMenuItem removeItem = new JMenuItem("Delete");
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(ruleTableListener != null) {
					ruleTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
			}
		});
		popup.add(removeItem);
	}
	public void setRuleTableListener(RuleTableListener listener) {
		this.ruleTableListener = listener;
	}
}
