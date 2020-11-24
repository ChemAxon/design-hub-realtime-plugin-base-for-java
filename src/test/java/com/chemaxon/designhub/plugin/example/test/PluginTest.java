package com.chemaxon.designhub.plugin.example.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.chemaxon.designhub.plugin.application.PluginApplication.class)
@AutoConfigureMockMvc
public class PluginTest {
    @Value("classpath:metadata.json")
    Resource resourceFileMetadata;
    @Value("classpath:update.json")
    Resource resourceFileUpdate;
    @Value("classpath:updateRequest.json")
    Resource resourceFileUpdateRequest;
    @Autowired
    private MockMvc mvc;

    @Test
    public void testMetadata() throws Exception {

        String response = mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode reference = mapper.readTree(response);
        JsonNode received = mapper.readTree(new BufferedReader(
                new InputStreamReader(resourceFileMetadata.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n")));

        Assert.assertTrue(reference.equals(received));

    }

    @Test
    public void testUpdate() throws Exception {

        String response = mvc.perform(post("/update")
                .contentType(MediaType.APPLICATION_JSON).content(new BufferedReader(
                        new InputStreamReader(resourceFileUpdateRequest.getInputStream(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"))))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode reference = mapper.readTree(response);
        JsonNode received = mapper.readTree(new BufferedReader(
                new InputStreamReader(resourceFileUpdate.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n")));

        Assert.assertTrue(reference.equals(received));

    }
}
