package com.tom.EBM_RuleManager.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.tom.EBM_RuleManager.Listeners.*;

public class Toolbar extends JPanel implements ActionListener {
	private JButton addRelation;
	private JButton addRule;
	private JButton export;
	private JButton load;
	private StringListener stringListener;

	public Toolbar() {
		addRelation = new JButton("Add Relation");
		addRule = new JButton("Add Rule");
		export = new JButton("Export");
		load = new JButton("Load");

		addRelation.addActionListener(this);
		addRule.addActionListener(this);
		export.addActionListener(this);
		load.addActionListener(this);

		setLayout(new FlowLayout(FlowLayout.LEFT));

		setBorder(BorderFactory.createEtchedBorder());

		add(addRelation);
		add(addRule);
		add(export);
		add(load);
	}

	public void setStringListener(StringListener listener) {
		this.stringListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		JButton clicked = (JButton) e.getSource();

		if (clicked == addRelation) {
			if (stringListener != null) {
				
				stringListener.textEmitted("Add Relation");
			}
		} else if (clicked == addRule) {
			if (stringListener != null) {
				stringListener.textEmitted("Add Rule");
			}
		}else if (clicked == export) {
			if (stringListener != null) {
				stringListener.textEmitted("export");
			}
		}else if (clicked == load) {
			if (stringListener != null) {
				stringListener.textEmitted("load");
			}
		}
	}

}
