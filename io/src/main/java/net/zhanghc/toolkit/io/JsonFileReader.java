package net.zhanghc.toolkit.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import com.google.gson.Gson;

/**
 * 
 * File reader that parses each line to the specified object.
 * 
 * @author zhanghc
 *
 * @param <T> Class that reflects target json's structure.
 */
public class JsonFileReader<T> extends EasyFileReader {
	private Class<T> type;
	private Gson gson = new Gson();

	/**
	 * 
	 * Create JsonFileReader by given specified class type.
	 * 
	 * @param file given file name
	 * @param charset given file charset
	 * @param type specified class type
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public JsonFileReader(String file, String charset, Class<T> type)
			throws UnsupportedEncodingException, FileNotFoundException {
		super(file, charset);
		this.type = type;
	}

	/**
	 * 
	 * Read json object from next line.
	 * 
	 * @return
	 * @throws IOException
	 */
	public T readJson() throws IOException {
		return readObj((l) -> { return gson.fromJson(l, type); });
	}
	
	public static <T> void process(String file, String charset,
			Consumer<T> processor, Class<T> type) throws FileNotFoundException, IOException {
		JsonFileReader<T> jr = new JsonFileReader<T>(file, charset, type);
		T t = null;
		while((t = jr.readJson()) != null) {
			processor.accept(t);
		}
		jr.close();
	}
	
	public static <T> void process(String file, String charset,
			Process<T> processor, Class<T> type, int maxline) throws FileNotFoundException, IOException {
		JsonFileReader<T> jr = new JsonFileReader<T>(file, charset, type);
		T t = null;
		int i = 0;
		while((t = jr.readJson()) != null && (maxline < 0 || i++ < maxline)) {
			processor.accept(t, i);
		}
		jr.close();
	}
	
	public static <T> void load(String file, String charset, 
			Collection<T> target, Class<T> type) throws FileNotFoundException, IOException {
		JsonFileReader<T> jr = new JsonFileReader<T>(file, charset, type);
		T t = null;
		while((t = jr.readJson()) != null) {
			target.add(t);
		}
		jr.close();
	}
	
	public static <K, V extends ID<K>> void load(String file, String charset, 
			Map<K, V> target, Class<V> type) throws FileNotFoundException, IOException {
		JsonFileReader<V> jr = new JsonFileReader<V>(file, charset, type);
		V t = null;
		while((t = jr.readJson()) != null) {
			target.put(t.getID(), t);
		}
		jr.close();
	}
	
	public static interface ID<T> {
		public T getID();
	}

}
