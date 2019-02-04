package com.tom.EBM_RuleManager.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tom.EBM.protege.tab.EBMWorkspaceTab;

public class Database {
	private List<Rule> rules;
	private List<Relation> relations;
	private String ontologyPath;
	private static final Logger log = LoggerFactory.getLogger(EBMWorkspaceTab.class);
	public Database() {
		rules = new LinkedList<Rule>();
		relations = new LinkedList<Relation>();
	}
	
	public void addRule(Rule rule){
		rules.add(rule);
	}
	
	public void addRelations(Relation relation) {
		relations.add(relation);
	}
	
	public List<Relation> getRelations(){
		return Collections.unmodifiableList(relations);
	}
	
	public List<Rule> getRules() {
		return Collections.unmodifiableList(rules);
	}

	public void removeRule(int index) {
		rules.remove(index);
	}
	public void removeRelation(int index) {
		relations.remove(index);
	}
	
	public void saveToFile(File file) throws IOException{
		FileOutputStream fos = new FileOutputStream(file);
		log.info("1");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		log.info("2");
		//Relation[] relationsArray = relations.toArray(new Relation[relations.size()]);
		RuleRelationSerial RRS = new RuleRelationSerial();
		log.info("3");
		RRS.setRelations(relations);
		log.info("4");
		RRS.setRules(rules);
		log.info("5");
		//File ontology = new File(ontologyPath);
		InputStream tmp = new FileInputStream(ontologyPath);
		byte[] ontology = IOUtils.toByteArray(tmp);
		int length = ontology.length;
		log.info("length: " + length);
		log.info("6");
		RRS.setOntology(ontology);
		log.info("7");
		oos.writeObject(RRS);
		log.info("8");
		oos.close();
		log.info("9");
	}
	
	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			RuleRelationSerial RRS = (RuleRelationSerial)ois.readObject();
			relations.clear();
			rules.clear();
			relations.addAll(RRS.getRelations());
			rules.addAll(RRS.getRules());
			ois.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setOntologyFilePath(String path) {
		ontologyPath = path;
	}
}
