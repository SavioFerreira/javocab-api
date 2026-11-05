package com.sfc.dictionary;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class DictionaryExceptionMapper {

    private static final Logger LOG = Logger.getLogger(DictionaryExceptionMapper.class);

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> mapDictionaryUnavailable(DictionaryUnavailableException e) {
        // technical details go to the log; the client only gets a generic message
        LOG.error(e.getMessage(), e);
        return RestResponse.status(RestResponse.Status.INTERNAL_SERVER_ERROR,
                new ErrorResponse("dictionary_unavailable", "Dictionary is temporarily unavailable"));
    }
}
