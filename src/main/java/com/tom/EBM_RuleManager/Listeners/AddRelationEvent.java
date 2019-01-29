package com.tom.EBM_RuleManager.Listeners;
import java.util.ArrayList;
import java.util.EventObject;

import com.tom.EBM_RuleManager.Model.Rule;

public class AddRelationEvent extends EventObject{
	private String relationName;
	private ArrayList<Rule> rules;
	
	public AddRelationEvent(Object source, String name, ArrayList<Rule> rules) {
		super(source);
		this.relationName = name;
		this.rules = rules;
	}

	public String getRelationName() {
		return relationName;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

}
