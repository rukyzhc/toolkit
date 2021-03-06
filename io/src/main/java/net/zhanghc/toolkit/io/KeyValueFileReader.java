package net.zhanghc.toolkit.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.function.Function;

import net.zhanghc.toolkit.lang.KeyValue;

public class KeyValueFileReader<K, V> extends EasyFileReader {
	protected Function<String, KeyValue<K, V>> convertor;
	/**
	 * Default as '\t';
	 */
	protected static char SplitChar = '\t';

	protected KeyValueFileReader(Reader r) {
		super(r);
	}

	protected KeyValueFileReader(String file, String charset) throws UnsupportedEncodingException, FileNotFoundException {
		super(file, charset);
	}
	
	public KeyValueFileReader(Reader r, Function<String, KeyValue<K, V>> convertor) {
		super(r);
		this.convertor = convertor;
	}

	public KeyValueFileReader(String file, String charset, Function<String, KeyValue<K, V>> convertor) throws UnsupportedEncodingException, FileNotFoundException {
		super(file, charset);
		this.convertor = convertor;
	}

	public static void setSplitChar(char sc) {
		SplitChar = sc;
	}

	public static char getSplitChar() {
		return SplitChar;
	}
	
	/**
	 * 
	 * Load key-value pairs into given map.
	 * 
	 * @param file
	 * @param charset
	 * @param value
	 * @param convertor
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static <K, V> void load(String file, String charset, Map<K, V> value, Function<String, KeyValue<K, V>> convertor) throws IOException, FileNotFoundException {
		EasyFileReader er = new EasyFileReader(file, charset);
		String line = null;
		while((line = er.readLine()) != null) {
			KeyValue<K, V> kv = convertor.apply(line);
			value.put(kv.getKey(), kv.getValue());
		}
		er.close();
	}

	public void setConvertor(Function<String, KeyValue<K, V>> convertor) {
		this.convertor = convertor;
	}

	public KeyValue<K, V> readKV() throws IOException {
		return readObj(convertor);
	}

	public static class StringDoubleFileReader extends KeyValueFileReader<String, Double> {

		public StringDoubleFileReader(Reader r) {
			super(r, (l) -> {
				int p = l.indexOf(SplitChar);
				return new KeyValue<String, Double>(
						l.substring(0, p), 
						Double.parseDouble(l.substring(p + 1)));
			});
		}

		public StringDoubleFileReader(String file, String charset)
				throws UnsupportedEncodingException, FileNotFoundException {
			super(file, charset, (l) -> {
				int p = l.indexOf(SplitChar);
				return new KeyValue<String, Double>(
						l.substring(0, p), 
						Double.parseDouble(l.substring(p + 1)));
			});
		}
		
		public static void load(String file, String charset, Map<String, Double> value) throws FileNotFoundException, IOException {
			KeyValueFileReader.load(file, charset, value, (l) -> {
				int p = l.indexOf(SplitChar);
				return new KeyValue<String, Double>(
						l.substring(0, p), 
						Double.parseDouble(l.substring(p + 1)));
			});
		}

	}

	public static class StringIntegerFileReader extends KeyValueFileReader<String, Integer> {

		public StringIntegerFileReader(Reader r) {
			super(r, (l) -> {
				int p = l.indexOf(SplitChar);
				return new KeyValue<String, Integer>(
						l.substring(0, p), 
						Integer.parseInt(l.substring(p + 1)));
			});
		}

		public StringIntegerFileReader(String file, String charset)
				throws UnsupportedEncodingException, FileNotFoundException {
			super(file, charset, (l) -> {
				int p = l.indexOf(SplitChar);
				return new KeyValue<String, Integer>(
						l.substring(0, p), 
						Integer.parseInt(l.substring(p + 1)));
			});
		}

		public static void load(String file, String charset, Map<String, Integer> value) throws FileNotFoundException, IOException {
			KeyValueFileReader.load(file, charset, value, (l) -> {
				int p = l.indexOf(SplitChar);
				return new KeyValue<String, Integer>(
						l.substring(0, p), 
						Integer.parseInt(l.substring(p + 1)));
			});
		}
	}
}
