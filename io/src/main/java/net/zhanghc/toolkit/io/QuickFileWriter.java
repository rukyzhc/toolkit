package net.zhanghc.toolkit.io;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class QuickFileWriter extends BufferedWriter {

	public QuickFileWriter(Writer w) {
		super(w);
	}
	
	public QuickFileWriter(String file) throws FileNotFoundException {
		super(new OutputStreamWriter(new FileOutputStream(file)));
	}
	
	public QuickFileWriter(String file, boolean append) throws FileNotFoundException {
		super(new OutputStreamWriter(new FileOutputStream(file, append)));
	}
	
	public QuickFileWriter(String file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
		super(new OutputStreamWriter(new FileOutputStream(file), charset));
	}
	
	public QuickFileWriter(String file, String charset, boolean append) throws FileNotFoundException, UnsupportedEncodingException {
		super(new OutputStreamWriter(new FileOutputStream(file, append), charset));
	}
	
	public void printf(String format, Object...args) throws IOException {
		super.write(String.format(format, args));
	}
	
	public void printfln(String format, Object...args) throws IOException {
		super.write(String.format(format, args));
		super.newLine();
	}
	
	public void println(String line) throws IOException {
		super.write(line);
		super.newLine();
	}
	
	public void println() throws IOException {
		super.newLine();
	}
	
}
