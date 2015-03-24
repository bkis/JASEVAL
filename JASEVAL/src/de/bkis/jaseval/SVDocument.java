package de.bkis.jaseval;

import java.util.ArrayList;


public class SVDocument {
	
	private int currIndex;
	private String[] header;
	private ArrayList<String[]> list;
	

	public SVDocument(){
		currIndex = 0;
	}
	
	public String[] getEntry(int index){
		//TODO
		return null;
	}
	
	public String[] getEntry(String field){
		//check for header
		if (header == null){
			System.out.println("[JASEVAL] Error! No header specified.");
			return null;
		}
		
		//TODO
	}
	
	public String[] getNextEntry(){
		//TODO
		return null;
	}
	
	public boolean hasNextEntry(){
		//TODO
		return false;
	}
	
	public void setHeader(String[] header){
		this.header = header;
	}
	
	public void addEntry(String entry, String delimiter){
		String[] e = entry.split(delimiter);
		list.add(e);
		
		//field count warnings
		if (header != null && e.length != header.length){
			System.out.println("[JASEVAL] Warning! Added entries field count "
					+ "doesn't match header length: \"" + entry + "\"");
		} else if (list.size() > 0 && e.length != list.get(list.size()-1).length) {
			System.out.println("[JASEVAL] Warning! Added entries field count "
					+ "doesn't match existing entries: \"" + entry + "\"");
		}
	}
	
	public String[] removeEntry(int index){
		if (list.size() > index){
			return list.remove(index);
		} else {
			System.out.println("[JASEVAL] Error! Entry at index " + index + " could not be removed / doesn't exist!");
			return null;
		}
	}

}
