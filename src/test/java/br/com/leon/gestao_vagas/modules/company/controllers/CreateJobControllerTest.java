package br.com.leon.gestao_vagas.modules.company.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.leon.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.leon.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.leon.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.leon.gestao_vagas.utils.TestUtils;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()).build();
    }

    @Test
    public void shouldBeAbleToCreateANewJob() throws Exception{

        var company = CompanyEntity.builder()
                .username("USERNAME_TEST")
                .email("email@gmail.com")
                .password("1234567890")
                .website("WEBSITE_TEST")
                .name("NAME_TEST")
                .description("DESCRIPTION_TEST")
                .build();

        CompanyEntity companySaved = companyRepository.saveAndFlush(company);


        CreateJobDTO createJobDTO = CreateJobDTO
                .builder()
                .description("DESCRIPTION_TEST")
                .benefits("BENEFTIS_TEST")
                .level("LEVEL_TEST")
                .build();

        mockMvc.perform(post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO))
                .header("Authorization", TestUtils.generateToken(companySaved.getId(), "JAVAGAS_@123#"))
        ).andExpect(status().isOk());
    }

}
