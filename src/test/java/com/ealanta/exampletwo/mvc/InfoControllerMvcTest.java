package com.ealanta.exampletwo.mvc;

import com.ealanta.exampletwo.GitInfo;
import com.ealanta.exampletwo.Time;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class InfoControllerMvcTest {

    private static final String SHA = "c212ece";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void test() throws Exception {
        MvcResult mvc = mockMvc.perform(get("/info/git"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String response = mvc.getResponse().getContentAsString();
        GitInfo info = mapper.readValue(response, GitInfo.class);
        assertEquals(info.getGitSha(), SHA);
    }

}
