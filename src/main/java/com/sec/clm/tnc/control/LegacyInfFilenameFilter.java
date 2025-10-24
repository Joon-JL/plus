package com.sec.clm.tnc.control;

import java.io.File;
import java.io.FilenameFilter;

public class LegacyInfFilenameFilter implements FilenameFilter{
    private String fileName;
    
    public LegacyInfFilenameFilter(String fileName){
        this.fileName = fileName;
    }
    
	public boolean accept(File dir, String name, int num) {
		// TODO Auto-generated method stub
		if (name != null) {
			if(fileName.length()==num)
			return fileName.startsWith(name);
		}
		return false;
	}

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		if (name != null) {
			return fileName.startsWith(name);
		}
		return false;
	}
}
