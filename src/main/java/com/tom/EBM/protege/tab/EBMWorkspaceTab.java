package com.tom.EBM.protege.tab;

import org.protege.editor.owl.ui.OWLWorkspaceViewsTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EBMWorkspaceTab extends OWLWorkspaceViewsTab {

	private static final Logger log = LoggerFactory.getLogger(EBMWorkspaceTab.class);

	public EBMWorkspaceTab() {
		setToolTipText("EBM rule management tab");
	}

    @Override
	public void initialise() {
		super.initialise();
		log.info("EBM rule management tab initialized");
	}

	@Override
	public void dispose() {
		super.dispose();
		log.info("EBM rule management tab disposed");
	}
}