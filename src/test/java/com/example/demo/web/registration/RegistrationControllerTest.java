package com.example.demo.web.registration;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.web.registration.model.RegistrationForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.UUID;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserService userService;

    @Test
    public void testRegistrationDefault() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/registration");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("form", new RegistrationForm()));
    }

    @Test
    public void testPostRegistrationEmptyForm() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("form"));
    }

    @Test
    public void testPostRegistrationInvalidEmail() throws  Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "asdfasdf")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("form"));
    }

    @Test
    public void testPostRegistrationExistingUser() throws  Exception {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        userService.handleCreated(event);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "test@test")
                .param("password", "password")
                .param("confirmPassword", "password");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("form"));
    }

    @Test
    public void testPostRegistrationMismatchPasswords() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "asdfasdf")
                .param("password", "Password1!")
                .param("confirmPassword", "Password2!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("form"));
    }

    @Test
    public void testPostRegistrationEmptyPasswords() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "asdfasdf")
                .param("password", "")
                .param("confirmPassword", "");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("form"));
    }

    @Test
    public void testPostRegistrationEWeakPasswords() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "asdfasdf")
                .param("password", "password")
                .param("confirmPassword", "password");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("form"));
    }

    @Test
    public void testPostRegistrationValid() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("email", "valid-submission@registration-controller.com")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/registration/success"));
    }

    @Test
    public void testRegistrationSuccessDefault() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/registration/success");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-success"));
    }
}
