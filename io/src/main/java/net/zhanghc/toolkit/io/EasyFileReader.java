package net.zhanghc.toolkit.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 
 * An easy-to-use file reader providing methods to read lines or objects.
 * 
 * @author zhanghc
 *
 */
public class EasyFileReader extends BufferedReader {

	/**
	 * 
	 * Create EasyFileReader by given reader.
	 * 
	 * @param r
	 */
	public EasyFileReader(Reader r) {
		super(r);
	}

	/**
	 * 
	 * Create EasyFileReader by given file name and charset.
	 * 
	 * @param file given file name
	 * @param charset given file charset
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public EasyFileReader(String file, String charset) throws UnsupportedEncodingException, FileNotFoundException {
		super(new InputStreamReader(new FileInputStream(file), charset));
	}
	
	/**
	 * 
	 * Read the next line and convert it to specified object.
	 * 
	 * @param convertor convert line to specified object.
	 * @return converted object from next line.
	 * @throws IOException
	 */
	public <T> T readObj(Function<String, T> convertor) throws IOException {
		String l = readLine();
		return l == null ? null : convertor.apply(l);
	}

	/**
	 * 
	 * Process specified file with provided function.
	 * 
	 * @param file specified file name.
	 * @param charset specified file charset.
	 * @param processor provided function to process each line.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void process(String file, String charset, Consumer<String> processor) throws FileNotFoundException, IOException {
		EasyFileReader er = new EasyFileReader(file, charset);
		String line = null;
		while((line = er.readLine()) != null) {
			processor.accept(line);
		}
		er.close();
	}
	
	public static void process(String file, String charset, Process<String> processor, int maxline)
			throws FileNotFoundException, IOException {
		EasyFileReader er = new EasyFileReader(file, charset);
		String line = null;
		int i = 0;
		while((line = er.readLine()) != null && (maxline < 0 || i++ < maxline)) {
			processor.accept(line, i);
		}
		er.close();
	}
	
	/**
	 * 
	 * Load all lines from the given file to the target collection.
	 * 
	 * @param file
	 * @param charset
	 * @param target
	 * @throws FileNotFoundException
	 * @throws IOException 
	 * 
	 */
	public static void load(String file, String charset, Collection<String> target) throws FileNotFoundException, IOException {
		EasyFileReader er = new EasyFileReader(file, charset);
		String line = null;
		while((line = er.readLine()) != null) {
			target.add(line);
		}
		er.close();
	}
	
	public static interface Process<T> {
		public void accept(T t, int no);
	}

}
