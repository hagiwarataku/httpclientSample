package batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class BatchInvoker {

	/** ���K�[ **/
	private Log logger = LogFactory.getLog(BatchInvoker.class);

	/**
	 * �o�b�`�N������
	 * @param args �N���p�����[�^
	 */
	public static void main(final String[] args) {
		int resultStatus = new BatchInvoker().execute(args);
		System.exit(resultStatus);
	}

	/**
	 * �o�b�`�N������
	 * @param args �N���N���X��
	 * @return ���ʃR�[�h
	 */
	public int execute(final String[] args) {

		//TODO �o�b�`�N�����O
		logger.info("start batch");

		int result = -1;
		try {
			// �p�����[�^�`�F�b�N

			// �p�����[�^

			// ���s�N���X������
			String className = "batch.FaxSendBatch";
			// �N���X�C���X�^���X����
			AbstractBaseBatch batchInstance = createInstance(className);

			// �o�b�`�������s
			Integer resultCd = batchInstance.executeBatch();
			// ���ʃR�[�h�ݒ�
			result = resultCd.intValue();

			//TODO �o�b�`�I���i����j���O
			logger.info("end batch success");
		} catch (Exception e) {
			//TODO �o�b�`�I���i�ُ�j���O
			logger.error("end batch error");

		}
		// ���ʃR�[�h�ԋp
		return result;
	}

	/**
	 * �w�肳�ꂽ�N���X�̃C���X�^���X�𐶐�����B
	 * @param className �N���X��
	 * @return �N���X�C���X�^���X
	 */
	@SuppressWarnings("unchecked")
	public static AbstractBaseBatch createInstance(final String className) {
		Class<Object> clazz;
		try {
			clazz = (Class<Object>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			LogFactory.getLog(BatchInvoker.class).error("�w�肵���N���X��������܂���");
			throw new RuntimeException(e);
		}
		AbstractBaseBatch obj;
		try {
			obj = (AbstractBaseBatch)clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return obj;
	}

}
