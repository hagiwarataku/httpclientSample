package dto;

/**
 * サービス結果保持クラス
 * @author hagiwara
 * @param <T> response body部保持クラスの型を指定
 */
public class ResultDto<T> {

	private int resultCd;

	private T resultObject;

	public int getResultCd() {
		return resultCd;
	}

	public void setResultCd(int resultCd) {
		this.resultCd = resultCd;
	}

	public T getResultObject() {
		return resultObject;
	}

	public void setResultObject(T resultObject) {
		this.resultObject = resultObject;
	}

}
