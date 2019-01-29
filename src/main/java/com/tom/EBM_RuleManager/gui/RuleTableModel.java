package com.tom.EBM_RuleManager.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tom.EBM_RuleManager.Model.Rule;

public class RuleTableModel extends AbstractTableModel{
	private List<Rule> db;
	private String[] colNames = {"ID","Name", "Topic", "Location", "Info"};
	
	public RuleTableModel() {
		
	}
	
	public void setData(List<Rule> db) {
		this.db = db;
	}
	
	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}
	
	public String getColumnName(int col) {
		return colNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Rule rule = db.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return rule.getID();
		case 1:
			return rule.getRuleName();
		case 2:
			return rule.getTopic();
		case 3:
			return rule.getRuleLocation();
		case 4: 
			return rule.getInformation();

		}
		return null;
	}
}
