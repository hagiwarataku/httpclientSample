package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtil {


	/**
	 * プロパティファイル読み込み
	 * @param filePath プロパティファイルパス
	 * @return 定義ファイル情報
	 * @throws IOException
	 */
	public static Properties loadConfiguration(String filePath) throws IOException {

		// プロパティファイル読み込み
		FileInputStream is = null;
		Properties props = new Properties();
		try {
			is = new FileInputStream(new File(filePath));
			props = new Properties();
			props.load(is);
		} finally {
			try {
				close(is);
			} catch (IOException e) {
				// クローズ処理での例外は無視
				e.printStackTrace();
			}
		}
		return props;

	}

	/**
	 * ファイルクローズ処理
	 * @throws IOException
	 */
	public static void close(InputStream in) throws IOException {
		if (in == null) {
			return;
		}
		in.close();
	}


}
