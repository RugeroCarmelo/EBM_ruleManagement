package com.tom.EBM_RuleManager.Listeners;

import java.io.File;
import java.io.InputStream;
import java.util.EventObject;

public class AddRuleEvent extends EventObject {
	private String ruleName;
	private String topic;
	private String info;
	private String ruleLocation;
	String ID;
	File file;

	public AddRuleEvent(Object source, String ruleLocation, String topic, String ruleName, String info, String ID,
			File file) {
		super(source);// this calls the constructor of the parent class in this case EventObject //If
						// you wanted to call a function from the parent use super.NameOfFunction();

		this.ruleName = ruleName;
		this.topic = topic;
		this.info = info;
		this.ID = ID;
		this.file = file;
		this.ruleLocation = ruleLocation;
	}

	public String getID() {
		return ID;
	}

	public File getFile() {
		return file;
	}

	public String getRuleName() {
		return ruleName;
	}

	public String getTopic() {
		return topic;
	}

	public String getInfo() {
		return info;
	}

	public String getRuleLocation() {
		return ruleLocation;
	}

}
