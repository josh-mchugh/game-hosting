package com.example.demo.framework.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

public class ModalResponseTest {

    @Test
    public void testComponentPath() {

        Assertions.assertEquals("component/modal-response", new ModalResponse(new ConcurrentModel()).build());
    }

    @Test
    public void testRedirectUrl() {

        Model model = new ConcurrentModel();
        new ModalResponse(model)
                .redirect("/test-path");

        Assertions.assertTrue(model.containsAttribute("redirectUrl"));
        Assertions.assertEquals("/test-path", model.getAttribute("redirectUrl"));
    }

    @Test
    public void testModalResponseEvent() {

        Model model = new ConcurrentModel();
        new ModalResponse(model)
                .event("contentId", "eventName");

        Assertions.assertTrue(model.containsAttribute("event"));
        Assertions.assertEquals(new ModalResponse.Event("contentId", "eventName"), model.getAttribute("event"));
    }

    @Test
    public void testModalResponseToastSuccess() {

        Model model = new ConcurrentModel();
        new ModalResponse(model)
                .toast("success test", ModalResponse.Type.SUCCESS);

        Assertions.assertTrue(model.containsAttribute("message"));
        Assertions.assertTrue(model.containsAttribute("type"));
        Assertions.assertEquals("success test", model.getAttribute("message"));
        Assertions.assertEquals(ModalResponse.Type.SUCCESS, model.getAttribute("type"));
    }

    @Test
    public void testModalResponseToastInfo() {

        Model model = new ConcurrentModel();
        new ModalResponse(model)
                .toast("info test", ModalResponse.Type.INFO);

        Assertions.assertTrue(model.containsAttribute("message"));
        Assertions.assertTrue(model.containsAttribute("type"));
        Assertions.assertEquals("info test", model.getAttribute("message"));
        Assertions.assertEquals(ModalResponse.Type.INFO, model.getAttribute("type"));
    }

    @Test
    public void testModalResponseToastWarning() {

        Model model = new ConcurrentModel();
        new ModalResponse(model)
                .toast("warning test", ModalResponse.Type.WARNING);

        Assertions.assertTrue(model.containsAttribute("message"));
        Assertions.assertTrue(model.containsAttribute("type"));
        Assertions.assertEquals("warning test", model.getAttribute("message"));
        Assertions.assertEquals(ModalResponse.Type.WARNING, model.getAttribute("type"));
    }

    @Test
    public void testModalResponseToastError() {

        Model model = new ConcurrentModel();
        new ModalResponse(model)
                .toast("error test", ModalResponse.Type.ERROR);

        Assertions.assertTrue(model.containsAttribute("message"));
        Assertions.assertTrue(model.containsAttribute("type"));
        Assertions.assertEquals("error test", model.getAttribute("message"));
        Assertions.assertEquals(ModalResponse.Type.ERROR, model.getAttribute("type"));
    }
}
