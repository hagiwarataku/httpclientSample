package dto;

/**
 * �T�[�r�X���ʕێ��N���X
 * @author hagiwara
 * @param <T> response body���ێ��N���X�̌^���w��
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
