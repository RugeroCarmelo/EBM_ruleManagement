package com.tom.EBM_RuleManager.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.tom.EBM_RuleManager.Model.Database;
import com.tom.EBM_RuleManager.Model.Relation;
import com.tom.EBM_RuleManager.Model.Rule;

public class Controller {
	Database db = new Database();
	
	public List<Rule> getRules(){
		return db.getRules();
	}
	public List<Relation> getRelations(){
		return db.getRelations();
	}
	public void addRule(String ruleLocation, String topic, String ruleName, String info, String ID, File file) {
		Rule rule = new Rule(ruleLocation, topic, ruleName, info, ID, file);
		db.addRule(rule);
	}
	
	public void addRelation(Relation relation) {
		db.addRelations(relation);
	}
	
	public void setOntologyFilePath(String path) {
		db.setOntologyFilePath(path);
	}
	
	public void removeRule(int index) {
		db.removeRule(index);
	}
	
	public void removeRelation(int index) {
		db.removeRelation(index);
	}
	
	public void saveToFile(File file) throws IOException{
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException{
		db.loadFromFile(file);
	}
}