package br.com.fundatec.microservicecourse.controller;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CachorroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void retornaTodosCachorrosShouldReturnOk() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cachorros"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void retornaByIdShouldReturnOk() throws Exception {
        Cachorro cachorro = new Cachorro("Biscoito", 2);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cachorro);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/cachorros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/cachorros/{nome}", "Biscoito"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Biscoito"))
                .andExpect(jsonPath("$.idade").value(2));
    }

}
