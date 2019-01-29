package com.tom.EBM_RuleManager.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tom.EBM_RuleManager.Model.Relation;
import com.tom.EBM_RuleManager.Model.Rule;

public class RelationTableModel extends AbstractTableModel{
	private List<Relation> db;
	private String[] colNames = {"ID","Name", "Topic", "Location", "Info"};
	
	public RelationTableModel(){
		
	}
	
	public void setData(List<Relation> db) {
		this.db = db;
	}
	
	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Relation relation = db.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return relation.getID();
		case 1:
			return relation.getConceptName();
		case 2:
			return relation.getRuleNames();
		}
		return null;
	}
	
	public String getColumnName(int col) {
		return colNames[col];
	}
}
