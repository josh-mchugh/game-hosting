package com.example.demo.web.registration;

import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.web.registration.service.RegistrationService;
import com.example.demo.web.registration.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.registration.service.model.ExistsUserByEmailResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerPostRegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService service;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenRequestIsEmptyFormThenReturnOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsEmptyFormThenReturnView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"));
    }

    @Test
    public void whenRequestIsFormThenExpectErrors() throws Exception {

        mockExistsUserByEmail();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());;
    }

    @Test
    public void whenRequestHasInvalidEmailThenExpectAttributeError() throws  Exception {

        mockExistsUserByEmail("asdfasdf", false);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "asdfasdf")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());;
    }

    @Test
    public void whenRequestHasDuplicateEmailThenExpectAttributeError() throws  Exception {

        mockExistsUserByEmail("test@test", true);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "test@test")
                .param("password", "password")
                .param("confirmPassword", "password");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasMismatchingPasswordThenExpectAttributeErrors() throws Exception {

        mockExistsUserByEmail();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "test@test")
                .param("password", "Password1!")
                .param("confirmPassword", "Password2!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasEmptyPasswordsThenExpectAttributeErrors() throws Exception {

        mockExistsUserByEmail();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "test@test")
                .param("password", "")
                .param("confirmPassword", "");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());;
    }

    @Test
    public void whenRequestHasWeakPasswordsThenExpectAttributeErrors() throws Exception {

        mockExistsUserByEmail();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "test@test")
                .param("password", "password")
                .param("confirmPassword", "password");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());;
    }

    @Test
    public void whenRequestIsValidThenExpectRedirection() throws Exception {

        mockExistsUserByEmail();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "test@test")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void whenRequestIsValidThenExpectRedirectUrl() throws Exception {

        mockExistsUserByEmail();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "test@test")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/registration/success"));
    }

    @Test
    public void whenRequestIsValidThenExpectCommandCalled() throws Exception {

        mockExistsUserByEmail();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "test@test")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!");

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserCreateRegularCommand.class));
    }

    private void mockExistsUserByEmail() {

        mockExistsUserByEmail("test@test", false);
    }

    private void mockExistsUserByEmail(String email, boolean exists) {

        Mockito.when(service.existsByEmail(new ExistsUserByEmailQuery(email)))
                .thenReturn(new ExistsUserByEmailResponse(exists));
    }
}
