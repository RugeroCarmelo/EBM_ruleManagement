package com.tom.EBM_RuleManager.Listeners;
import java.util.EventListener;

public interface AddRuleListener extends EventListener{
	public void addRuleEventOccurred(AddRuleEvent event);
}
