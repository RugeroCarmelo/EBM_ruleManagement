package com.tom.EBM.protege.view;

import java.awt.BorderLayout;
import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.semanticweb.owlapi.model.IRI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tom.EBM_RuleManager.Listeners.StringListener;
import com.tom.EBM_RuleManager.gui.MainFrame;

public class EBMViewComponent extends AbstractOWLViewComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3248136932028196172L;

	private static final Logger log = LoggerFactory.getLogger(EBMViewComponent.class);

	private MainFrame mainFrame;

	@Override
	protected void initialiseOWLView() throws Exception {
		setLayout(new BorderLayout());

		mainFrame = new MainFrame();
		mainFrame.setStringListener(new StringListener() {
			public void textEmitted(String type) {
				if (type.equals("addRelation")) {
					String nodeIRI = "";
					try {
						nodeIRI = getSelectedNodeIRI();
					} catch (NullPointerException e) {
						
					}
					//log.info("THE CURRENT SELECTED NODE IS:  " + nodeIRI);
					mainFrame.setCurrentSelectedClass(nodeIRI);
				} else if (type.equals("export")) {
					//log.info("THE CURRENT ontology uri is:  " + getCurrentOntlogyURI());
					mainFrame.setCurrentOntologyPath(getCurrentOntlogyURI());
				}
			}
		});
		add(mainFrame, BorderLayout.CENTER);
		log.info("Example View Component initialized");
	}

	private String getSelectedNodeIRI() {
		IRI iri = this.getOWLWorkspace().getOWLSelectionModel().getLastSelectedClass().getIRI();
		if (iri != null) {
			return iri.toURI().toString();
		}
		return "";
	}

	private String getCurrentOntlogyURI() throws NullPointerException {
		return this.getOWLEditorKit().getOWLModelManager()
				.getOntologyPhysicalURI(this.getOWLEditorKit().getModelManager().getActiveOntology()).getPath();
	}

	@Override
	protected void disposeOWLView() {
		mainFrame.dispose();
	}
}
