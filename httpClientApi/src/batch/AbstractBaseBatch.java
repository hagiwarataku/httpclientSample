package batch;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.FileUtil;
import constants.AppConst;

public abstract class AbstractBaseBatch {

	/** 定義情報 **/
	Properties props;
	/** メッセージ情報 **/
	Properties messages;

	/** ロガー **/
	private Log logger = LogFactory.getLog(AbstractBaseBatch.class);

	public int executeBatch() {

		int result = 1;

		// TODO 起動ログ

		// プロパティファイル読み込み
		loadConfigulation();

		// メッセージファイル読み込み
		loadMessage();

		logger.info(props.getProperty("host.name"));
		logger.info(messages.getProperty("warn.1001"));
		logger.info(MessageFormat.format(messages.getProperty("warn.1002"),"8", "32"));
		try {
			// 前処理
			beforeExecute();
			// 実処理
			result = execute();
		} catch (Exception e) {
			// TODO 例外処理を決める
			throw new RuntimeException(e);
		} finally {
			// 後処理
			afterExecute();
		}

		return result;
	}

	protected abstract int execute();

	/**
	 * バッチ実行前処理
	 * 未実装（必要であれば追加）
	 */
	protected void beforeExecute() {
	}

	/**
	 * バッチ実行後処理
	 * 未実装（必要であれば追加）
	 */
	protected void afterExecute() {
	}

	/**
	 * 定義情報読み込み
	 * @return プロパティファイル
	 */
	protected void loadConfigulation() {
		try {
			props = FileUtil.loadConfiguration(AppConst.APP_PROP_PATH);
		} catch (IOException e) {
			// TODO メッセージ出力
			logger.error("プロパティファイル読み込み失敗");
		}
	}

	/**
	 * メッセージ情報読み込み
	 */
	protected void loadMessage() {
		try {
			messages = FileUtil.loadConfiguration(AppConst.MESSAGE_PROP_PATH);
		} catch (IOException e) {
			// TODO メッセージ出力
			logger.error("メッセージファイル読み込み失敗");

		}
	}

}
