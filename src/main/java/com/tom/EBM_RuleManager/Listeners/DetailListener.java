package com.tom.EBM_RuleManager.Listeners;
import java.util.EventListener;

public interface DetailListener extends EventListener {

    public void detailEventOccurred(DetailEvent event);

}
