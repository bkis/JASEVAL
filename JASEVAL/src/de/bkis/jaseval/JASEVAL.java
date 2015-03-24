package de.bkis.jaseval;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class JASEVAL {
	
	
	public SVDocument readSVFile(String path, String delimiter, boolean firstLineIsHeader) throws IOException{
		System.out.print("[JASEVAL] reading " + path + "... ");
		
		SVDocument doc = new SVDocument();
		FileReader fr = new FileReader(new File(path));
		BufferedReader br = new BufferedReader(fr);
		String currLine;
		
		System.out.print("(Detected File Encoding: " + fr.getEncoding() + ") ");
		
		//set header
		if (firstLineIsHeader && (currLine = br.readLine()) != null){
			doc.setHeader(br.readLine().split(delimiter));
		}
		
		//read content
		while ((currLine = br.readLine()) != null){
			doc.addEntry(currLine, delimiter);
		}
		br.close();

		System.out.println("- OK");
		return doc;
	}

	public void writeSV(SVDocument doc, String delimiter, String path, String encoding) throws IOException {
		System.out.print("[JASEVAL] writing data to " + path + " ... ");
		StringBuilder sb = new StringBuilder();

		// generate text data
		doc.reset();
		String[] currEntry;
		while (doc.hasNextEntry()) {
			currEntry = doc.nextEntry();
			for (String s : currEntry) sb.append(s + delimiter);
			sb.replace(sb.length() - delimiter.length(), sb.length(), "\n");
		}

		// write to file
		if (encoding == null) encoding = "URF-8";
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path), encoding));
		out.write(sb.toString());
		out.flush();
		out.close();
		
		System.out.println("- OK");
	}

}
