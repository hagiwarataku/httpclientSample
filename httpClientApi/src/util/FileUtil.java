package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtil {


	/**
	 * �v���p�e�B�t�@�C���ǂݍ���
	 * @param filePath �v���p�e�B�t�@�C���p�X
	 * @return ��`�t�@�C�����
	 * @throws IOException
	 */
	public static Properties loadConfiguration(String filePath) throws IOException {

		// �v���p�e�B�t�@�C���ǂݍ���
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
				// �N���[�Y�����ł̗�O�͖���
				e.printStackTrace();
			}
		}
		return props;

	}

	/**
	 * �t�@�C���N���[�Y����
	 * @throws IOException
	 */
	public static void close(InputStream in) throws IOException {
		if (in == null) {
			return;
		}
		in.close();
	}


}
