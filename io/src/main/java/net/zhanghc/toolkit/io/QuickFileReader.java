package net.zhanghc.toolkit.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @deprecated <br/>
 * See {@link EasyFileReader}
 * 
 * @author zhanghc
 *
 */
public class QuickFileReader extends BufferedReader {

	public QuickFileReader(Reader r) {
		super(r);
	}
	
	public QuickFileReader(String file) throws FileNotFoundException {
		super(new InputStreamReader(new FileInputStream(file)));
	}
	
	public QuickFileReader(String file, String charset) throws UnsupportedEncodingException, FileNotFoundException {
		super(new InputStreamReader(new FileInputStream(file), charset));
	}

	public String readLineSkip(char c) throws IOException {
		String line = null;
		while((line = readLine()) != null && line.charAt(0) != c) ;
		return line;
	}

}
