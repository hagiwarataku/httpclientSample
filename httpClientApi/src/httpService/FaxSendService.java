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

		// パラメータ設定
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
	    multipartEntityBuilder.setCharset(Consts.UTF_8);

	    // ファイルを追加
	    multipartEntityBuilder.addBinaryBody("file", new File("c:/test/test.txt"), ContentType.DEFAULT_BINARY, "test.txt");

	    // テキスト文字列を設定するときのContentTypeを生成
	    ContentType textContentType = ContentType.create("text/plain", Consts.UTF_8);

	    // テキストを追加
	    multipartEntityBuilder.addTextBody("text", "テスト", textContentType);

		// POSTデータ作成
		String url = "http://localhost:8080/httpServerDummy/rest";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(multipartEntityBuilder.build());

		String body = "";
		int responseStatus = 0;

		CloseableHttpResponse response = null;

		// 送信実行
		try {
			response = httpclient.execute(httpPost);
			responseStatus = response.getStatusLine().getStatusCode();
			body = EntityUtils.toString(response.getEntity(), "UTF-8");
			response.close();
		} catch (ClientProtocolException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// resuponseの設定
		ResultDto<TestResponseDto> result = new ResultDto<TestResponseDto>();
		result.setResultCd(responseStatus);
		result.setResultObject(JSON.decode(body, TestResponseDto.class));
//		TestResponseDto dto = new TestResponseDto();
//		dto.result = body;
//		result.setResultObject(dto);

		return result;

	}
}
