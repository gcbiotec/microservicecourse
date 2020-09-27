package br.com.fundatec.microservicecourse.controller;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import br.com.fundatec.microservicecourse.domain.Veterinario;
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
public class VeterinarioControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void retornaTodosVeterinariosShouldReturnOk() throws Exception{
		mockMvc
			.perform(MockMvcRequestBuilders.get("/veterinarios"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
	}
    @Test
    public void retornaPostVeterinarioOK() throws Exception {

        Veterinario veterinario = new Veterinario(null, "Marvin", "11/07/2001", "3237467364", null);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(veterinario);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/veterinarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());


        mockMvc
                .perform(MockMvcRequestBuilders.get("/veterinarios/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())

                .andExpect(jsonPath("$.nome").value("Marvin"))
                .andExpect(jsonPath("$.dataNascimento").value("11/07/2001"))
                .andExpect(status().isOk());

    }
}
