package de.bkis.jaseval;

import java.util.ArrayList;
import java.util.List;


public class SVDocument {
	
	private int currIndex;
	private String[] header;
	private ArrayList<String[]> list;
	

	public SVDocument(){
		currIndex = 0;
	}
	
	public String[] getEntry(int index){
		if (list.size() > index){
			return list.get(index);
		} else {
			System.out.println("[JASEVAL] Error! Entry at index " + index + " doesn't exist!");
			return null;
		}
	}
	
	public List<String[]> getEntriesContaining(String query){
		List<String[]> results = new ArrayList<String[]>();
		
		for (String[] entry : list){
			for (String s : entry){
				if (s.contains(query)){
					results.add(entry);
					break;
				}
			}
		}
		
		return results;
	}
	
	public String[] nextEntry(){
		String[] e;
		
		if (list.size() > currIndex){
			e = list.get(currIndex);
			currIndex++;
			return e;
		} else {
			return null;
		}
	}
	
	public void reset(){
		currIndex = 0;
	}
	
	public boolean hasNextEntry(){
		if (list.size() > currIndex){
			return true;
		} else {
			return false;
		}
	}
	
	public void setHeader(String[] header){
		this.header = header;
	}
	
	public void addEntry(String entry, String delimiter){
		String[] e = entry.split(delimiter, -1);
		addEntry(e);
	}
	
	public void addEntry(String[] entry){
		//check field count
		if (header != null && entry.length != header.length){
			System.out.println("[JASEVAL] Error! Added entries field count "
					+ "doesn't match header length: \"" + entry + "\"");
		} else if (list.size() > 0 && entry.length != list.get(list.size()-1).length) {
			System.out.println("[JASEVAL] Warning! Added entries field count "
					+ "doesn't match existing entries: \"" + entry + "\"");
		} else {
			list.add(entry);
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
	
	public int getFieldIndex(String field){
		if (header == null){
			System.out.println("[JASEVAL] Error! No header specified.");
			return -1;
		} else {
			for (int i = 0; i < header.length; i++) {
				if (header[i].equals(field)) return i;
			}
			System.out.println("[JASEVAL] Error! Field \"" + field + "\" not found.");
			return -1;
		}
	}

}
