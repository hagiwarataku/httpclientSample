package batch;

import httpService.FaxSendService;
import dto.ResultDto;
import dto.TestResponseDto;

public class FaxSendBatch extends AbstractBaseBatch {

	@Override
	protected int execute() {

		FaxSendService faxSendService = new FaxSendService();
		ResultDto<TestResponseDto> resultDto = faxSendService.doPost();

		if (resultDto.getResultCd() == 200) {
			return 1;
		}

		return 0;

	}

}
