package com.xspace.nacos.sentinel;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @author xue.zeng
 */
@Slf4j
public class ExceptionUtil {

	public static SentinelClientHttpResponse handleException(HttpRequest request,
                                                             byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
		log.info("Oops: {}",  ex.getClass().getCanonicalName());
		return new SentinelClientHttpResponse("custom block info");
	}

}
