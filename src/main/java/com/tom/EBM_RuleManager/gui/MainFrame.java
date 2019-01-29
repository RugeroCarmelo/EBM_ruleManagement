package com.tom.EBM_RuleManager.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;

import com.tom.EBM_RuleManager.Listeners.AddRelationEvent;
import com.tom.EBM_RuleManager.Listeners.AddRelationListener;
import com.tom.EBM_RuleManager.Listeners.AddRuleEvent;
import com.tom.EBM_RuleManager.Listeners.AddRuleListener;
import com.tom.EBM_RuleManager.Listeners.RuleTableListener;
import com.tom.EBM_RuleManager.Listeners.StringListener;
import com.tom.EBM_RuleManager.Model.Relation;
import com.tom.EBM_RuleManager.Model.Rule;
import com.tom.EBM_RuleManager.controller.Controller;

public class MainFrame extends JPanel {
	private Toolbar toolbar;
	private Controller controller;
	private RuleTablePanel ruleTablePanel;
	private JTabbedPane tabbedPane;
	private RelationTablePanel relationTablePanel;
	private JFileChooser OWLFileChooser;
	private JFileChooser fileChooser;
	private StringListener stringListener;
	private String currentSelectedClass;
	private String currentOntologyPath;

	public MainFrame() {
		// super(title);
		fileChooser = new JFileChooser();
		OWLFileChooser = new JFileChooser();
		FileFilter fileFilter = new RuleFileFilter("dmn", "rule files");
		OWLFileChooser.addChoosableFileFilter(fileFilter);
		OWLFileChooser.setFileFilter(fileFilter);
		setLayout(new BorderLayout());
		toolbar = new Toolbar();
		controller = new Controller();
		relationTablePanel = new RelationTablePanel(controller.getRelations());
		relationTablePanel.setData(controller.getRelations());
		relationTablePanel.setRuleTableListener(new RuleTableListener() {
			public void rowDeleted(int row) {
				controller.removeRelation(row);
			}
		});
		ruleTablePanel = new RuleTablePanel(controller.getRules());
		ruleTablePanel.setData(controller.getRules());
		ruleTablePanel.setRuleTableListener(new RuleTableListener() {
			public void rowDeleted(int row) {
				controller.removeRule(row);
			}
		});

		toolbar.setStringListener(new StringListener() {

			public void textEmitted(String type) {
				if (type.equals("Add Rule")) {
					AddRuleDialog addRuleDialog = new AddRuleDialog(OWLFileChooser);
					addRuleDialog.setAddRuleListener(new AddRuleListener() {
						public void addRuleEventOccurred(AddRuleEvent event) {
							controller.addRule(event.getRuleLocation(), event.getTopic(), event.getRuleName(),
									event.getInfo(), event.getID(), event.getFile());
							ruleTablePanel.refresh();
						}
					});
					addRuleDialog.setVisible(true);
				} else if (type.equals("Add Relation")) {
					stringListener.textEmitted("addRelation");// ------------------------------------------------------
					AddRelationDialog addRelationDialog = new AddRelationDialog(controller.getRules());
					addRelationDialog
							.setNameField(currentSelectedClass.substring(currentSelectedClass.lastIndexOf("#") + 1));
					addRelationDialog.setAddRelationListener(new AddRelationListener() {
						public void AddRelationEventOccurred(AddRelationEvent event) {
							Relation relation = new Relation(event.getRelationName());
							relation.setRules(event.getRules());
							controller.addRelation(relation);
							relationTablePanel.refresh();
						}
					});
					addRelationDialog.setVisible(true);
				} else if (type.equals("load")) {
					if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
						try {
							controller.loadFromFile(fileChooser.getSelectedFile());
							// System.out.println(fileChooser.getSelectedFile());
							relationTablePanel.refresh();
							ruleTablePanel.refresh();
						} catch (IOException e) {
							JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} else if (type.equals("export")) {
					stringListener.textEmitted("export");
					controller.setOntologyFilePath(currentOntologyPath);
					if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
						try {
							controller.saveToFile((fileChooser.getSelectedFile()));
						} catch (IOException e) {
							JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Relations", relationTablePanel);
		tabbedPane.setToolTipTextAt(0, "Relations.");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_G);
		
		tabbedPane.addTab("Rules", ruleTablePanel);
		tabbedPane.setToolTipTextAt(1, "Rules.");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_T);

		add(toolbar, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
	}

	public void setStringListener(StringListener listener) {
		this.stringListener = listener;
	}

	public void setCurrentSelectedClass(String currentSelectedClass) {
		this.currentSelectedClass = currentSelectedClass;
	}

	public void setCurrentOntologyPath(String currentOntologyPath) {
		this.currentOntologyPath = currentOntologyPath;
	}

	public void dispose() {
		stringListener = null;
	}
}
