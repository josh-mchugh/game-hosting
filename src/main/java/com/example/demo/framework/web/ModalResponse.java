package com.example.demo.framework.web;

import lombok.Value;
import org.springframework.ui.Model;

public class ModalResponse {

    public static final String RELOAD = "reload";

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

    public ModalResponse toast(Type type, String message) {

        model.addAttribute("message", message);
        model.addAttribute("type", type);

        return this;
    }

    public ModalResponse event(String eventName, String contentId) {

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
