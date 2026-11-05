package com.sfc.dictionary;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path ("/dictionary")
public class DictionaryAPI {

    private final DictionaryClient dictionaryClient;

    public DictionaryAPI(DictionaryClient dictionaryClient) {
        this.dictionaryClient = dictionaryClient;
    }

    @GET
    @Path("/word-exists/{word}")
    public WordExistsResponse wordExistsInDictionary(@PathParam("word") String word) {
        return new WordExistsResponse(word, dictionaryClient.wordExists(word));
    }   
    
}
