package httpService;

import java.io.File;
import java.io.IOException;

import net.arnx.jsonic.JSON;

import org.apache.http.Consts;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import dto.ResultDto;
import dto.TestResponseDto;

public class FaxSendService {


	public ResultDto<TestResponseDto> doPost() {

		// �p�����[�^�ݒ�
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
	    multipartEntityBuilder.setCharset(Consts.UTF_8);

	    // �t�@�C����ǉ�
	    multipartEntityBuilder.addBinaryBody("file", new File("c:/test/test.txt"), ContentType.DEFAULT_BINARY, "test.txt");

	    // �e�L�X�g�������ݒ肷��Ƃ���ContentType�𐶐�
	    ContentType textContentType = ContentType.create("text/plain", Consts.UTF_8);

	    // �e�L�X�g��ǉ�
	    multipartEntityBuilder.addTextBody("text", "�e�X�g", textContentType);

		// POST�f�[�^�쐬
		String url = "http://localhost:8080/httpServerDummy/rest";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(multipartEntityBuilder.build());

		String body = "";
		int responseStatus = 0;

		CloseableHttpResponse response = null;

		// ���M���s
		try {
			response = httpclient.execute(httpPost);
			responseStatus = response.getStatusLine().getStatusCode();
			body = EntityUtils.toString(response.getEntity(), "UTF-8");
			response.close();
		} catch (ClientProtocolException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}

		// resuponse�̐ݒ�
		ResultDto<TestResponseDto> result = new ResultDto<TestResponseDto>();
		result.setResultCd(responseStatus);
		result.setResultObject(JSON.decode(body, TestResponseDto.class));
//		TestResponseDto dto = new TestResponseDto();
//		dto.result = body;
//		result.setResultObject(dto);

		return result;

	}
}
