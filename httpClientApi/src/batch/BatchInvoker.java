package batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class BatchInvoker {

	/** ロガー **/
	private Log logger = LogFactory.getLog(BatchInvoker.class);

	/**
	 * バッチ起動処理
	 * @param args 起動パラメータ
	 */
	public static void main(final String[] args) {
		int resultStatus = new BatchInvoker().execute(args);
		System.exit(resultStatus);
	}

	/**
	 * バッチ起動処理
	 * @param args 起動クラス名
	 * @return 結果コード
	 */
	public int execute(final String[] args) {

		//TODO バッチ起動ログ
		logger.info("start batch");

		int result = -1;
		try {
			// パラメータチェック

			// パラメータ

			// 実行クラス名生成
			String className = "batch.FaxSendBatch";
			// クラスインスタンス生成
			AbstractBaseBatch batchInstance = createInstance(className);

			// バッチ処理実行
			Integer resultCd = batchInstance.executeBatch();
			// 結果コード設定
			result = resultCd.intValue();

			//TODO バッチ終了（正常）ログ
			logger.info("end batch success");
		} catch (Exception e) {
			//TODO バッチ終了（異常）ログ
			logger.error("end batch error");

		}
		// 結果コード返却
		return result;
	}

	/**
	 * 指定されたクラスのインスタンスを生成する。
	 * @param className クラス名
	 * @return クラスインスタンス
	 */
	@SuppressWarnings("unchecked")
	public static AbstractBaseBatch createInstance(final String className) {
		Class<Object> clazz;
		try {
			clazz = (Class<Object>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			LogFactory.getLog(BatchInvoker.class).error("指定したクラスが見つかりません");
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
