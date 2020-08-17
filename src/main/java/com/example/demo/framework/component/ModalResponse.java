package com.example.demo.framework.component;

import lombok.Value;
import org.springframework.ui.Model;

public class ModalResponse {

    public enum Type {
        SUCCESS,
        INFO,
        WARNING,
        ERROR
    }

    private final Model model;

    public ModalResponse(Model model) {

        this.model = model;
    }

    public ModalResponse toast(String message, Type type) {

        model.addAttribute("message", message);
        model.addAttribute("type", type);

        return this;
    }

    public ModalResponse event(String contentId, String eventName) {

        model.addAttribute("event", new Event(contentId, eventName));

        return this;
    }

    public ModalResponse redirect(String url) {

        model.addAttribute("redirectUrl", url);

        return this;
    }

    public String build() {

        return "component/modal-response";
    }

    @Value
    public static class Event {

        String id;
        String name;
    }
}
