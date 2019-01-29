package com.tom.EBM_RuleManager.gui;
import java.io.File;
import javax.swing.filechooser.FileFilter;
public class RuleFileFilter extends FileFilter {
	private String ext;
	private String description;
	public RuleFileFilter(String extension, String description) {
		super();
		this.ext = extension;
		this.description = description;
	}

	public boolean accept(File file) {
		String name = file.getName();
		String extension = Utils.getFileExtension(name);
		
		if(file.isDirectory()) {
			return true;
		}
		
		if(extension == null) {
			return false;
		}
		
		if(extension.equals(ext)) {
			return true;
		}
		return false;
	}

	public String getDescription() {
		
		return description + "(*." + ext +  ")";
	}

}
