package de.bkis.jaseval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SVDocument {
	
	private int currIndex;
	private ArrayList<String[]> entries;
	private String[] header;
	

	public SVDocument(){
		currIndex = 0;
		entries = new ArrayList<String[]>();
	}
	
	public String[] getEntry(int index){
		if (entries.size() > index){
			return entries.get(index);
		} else {
			System.out.println("[JASEVAL] Error! Entry at index " + index + " doesn't exist!");
			return null;
		}
	}
	
	public List<String[]> getEntriesContaining(String query){
		List<String[]> results = new ArrayList<String[]>();
		
		for (String[] entry : entries){
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
		
		if (entries.size() > currIndex){
			e = entries.get(currIndex);
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
		if (entries.size() > currIndex){
			return true;
		} else {
			reset();
			return false;
		}
	}
	
	public void addEntry(String entry, String delimiter){
		String[] e = entry.split(delimiter, (header != null ? header.length : -1));
		addEntry(e);
	}
	
	public void addEntry(String[] entry){
		//check field count
		if (entries.size() > 0 && header != null && entry.length != getHeader().length){
			System.out.println("[JASEVAL] Error! Added entries field count "
					+ "doesn't match header length: \"" + entry + "\"");
		} else if (entries.size() > 0 && entry.length != entries.get(entries.size()-1).length) {
			System.out.println("[JASEVAL] Error! Added entries field count "
					+ "doesn't match existing entries: \"" + entry + "\"");
		} else {
			entries.add(entry);
		}
	}
	
	public int getFieldIndex(String field){
		if (header == null){
			System.out.println("[JASEVAL] Error! No header specified.");
			return -1;
		} else {
			for (int i = 0; i < getHeader().length; i++) {
				if (getHeader()[i].equals(field)) return i;
			}
			System.out.println("[JASEVAL] Error! Field \"" + field + "\" not found.");
			return -1;
		}
	}
	
	public void setHeader(String headerLine, String delimiter){
		header = headerLine.split(delimiter);
	}
	
	public String[] getHeader(){
		return header;
	}
	
	public boolean removeEntry(String[] entry){
		long index = entries.indexOf(entry);
		boolean done = entries.remove(entry);
		if (index <= currIndex) currIndex--;
		return done;
	}
	
	public String[] removeEntry(int index){
		if (entries.size() > index){
			if (index <= currIndex) currIndex--;
			return entries.remove(index);
		} else {
			System.out.println("[JASEVAL] Error! Entry at index " + index + " could not be removed / doesn't exist!");
			return null;
		}
	}
	
	public int entriesCount(){
		return entries.size();
	}
	
	public void addField(String fieldName){
		System.out.print("[JASEVAL] adding field \"" + fieldName + "\"... ");
		header = Arrays.copyOf(header, header.length + 1);
		header[header.length - 1] = fieldName;
		
		for (int i = 0; i < entries.size(); i++) {
			String[] entry = Arrays.copyOf(entries.get(i), entries.get(i).length + 1);
			entry[entry.length-1] = "";
			entries.remove(i);
			entries.add(i, entry);
		}
		System.out.println("- OK");
	}

}
