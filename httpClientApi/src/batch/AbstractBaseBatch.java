package batch;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.FileUtil;
import constants.AppConst;

public abstract class AbstractBaseBatch {

	/** ��`��� **/
	Properties props;
	/** ���b�Z�[�W��� **/
	Properties messages;

	/** ���K�[ **/
	private Log logger = LogFactory.getLog(AbstractBaseBatch.class);

	public int executeBatch() {

		int result = 1;

		// TODO �N�����O

		// �v���p�e�B�t�@�C���ǂݍ���
		loadConfigulation();

		// ���b�Z�[�W�t�@�C���ǂݍ���
		loadMessage();

		logger.info(props.getProperty("host.name"));
		logger.info(messages.getProperty("warn.1001"));
		logger.info(MessageFormat.format(messages.getProperty("warn.1002"),"8", "32"));
		try {
			// �O����
			beforeExecute();
			// ������
			result = execute();
		} catch (Exception e) {
			// TODO ��O���������߂�
			throw new RuntimeException(e);
		} finally {
			// �㏈��
			afterExecute();
		}

		return result;
	}

	protected abstract int execute();

	/**
	 * �o�b�`���s�O����
	 * �������i�K�v�ł���Βǉ��j
	 */
	protected void beforeExecute() {
	}

	/**
	 * �o�b�`���s�㏈��
	 * �������i�K�v�ł���Βǉ��j
	 */
	protected void afterExecute() {
	}

	/**
	 * ��`���ǂݍ���
	 * @return �v���p�e�B�t�@�C��
	 */
	protected void loadConfigulation() {
		try {
			props = FileUtil.loadConfiguration(AppConst.APP_PROP_PATH);
		} catch (IOException e) {
			// TODO ���b�Z�[�W�o��
			logger.error("�v���p�e�B�t�@�C���ǂݍ��ݎ��s");
		}
	}

	/**
	 * ���b�Z�[�W���ǂݍ���
	 */
	protected void loadMessage() {
		try {
			messages = FileUtil.loadConfiguration(AppConst.MESSAGE_PROP_PATH);
		} catch (IOException e) {
			// TODO ���b�Z�[�W�o��
			logger.error("���b�Z�[�W�t�@�C���ǂݍ��ݎ��s");

		}
	}

}
