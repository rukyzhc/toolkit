package net.zhanghc.toolkit.io;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.function.Function;

import com.google.gson.Gson;

public class EasyFileWriter extends BufferedWriter {
	Gson gson = new Gson();

	public EasyFileWriter(Writer w) {
		super(w);
	}

	public EasyFileWriter(String file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
		super(new OutputStreamWriter(new FileOutputStream(file), charset));
	}

	public EasyFileWriter(String file, String charset, boolean append) throws FileNotFoundException, UnsupportedEncodingException {
		super(new OutputStreamWriter(new FileOutputStream(file, append), charset));
	}

	public void printf(String format, Object...args) throws IOException {
		super.write(String.format(format, args));
	}

	public void printfln(String format, Object...args) throws IOException {
		super.write(String.format(format, args));
		super.newLine();
	}

	public void print(String str) throws IOException {
		super.write(str);
	}

	public void println(String line) throws IOException {
		super.write(line);
		super.newLine();
	}

	public void println() throws IOException {
		super.newLine();
	}

	public <T> void printObj(T obj, Function<T, String> convertor) throws IOException {
		super.write(convertor.apply(obj));
		super.newLine();
	}
	
	public <T> void printJson(T obj) throws IOException {
		super.write(gson.toJson(obj));
		super.newLine();
	}

}
