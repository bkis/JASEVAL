package de.bkis.jaseval;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class JASEVAL {

	public void writeSV(SVDocument svDoc, String delimiter, String path) {
		System.out.print("[JASEVAL] writing data to " + path + " ... ");
		StringBuilder sb = new StringBuilder();

		// generate text data
		for (String[] sArr : svData) {
			for (String s : sArr) {
				sb.append(s + delimiter);
			}
			sb.replace(sb.length() - delimiter.length(), sb.length(), "\n");
		}

		// write to file
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path), "UTF-8"));
		out.write(sb.toString());
		out.flush();
		out.close();
		
		System.out.println("OK");
	}

	private String readFile(String path) throws IOException {
		System.out.print("[JASEVAL] reading " + path + "... ");
		StringBuffer sb = new StringBuffer();

		FileReader fr = new FileReader(new File(path));
		System.out.print("(Detected File Encoding: " + fr.getEncoding() + ") ");
		int c;
		while ((c = fr.read()) > -1){
			sb.append((char) c);
		}
		fr.close();

		System.out.println("OK");
		return sb.toString();
	}

}
