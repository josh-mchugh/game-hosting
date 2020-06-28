package com.example.demo.controller.index.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {

    private Cpu cpu;
    private Memory memory;
    private Disk disk;

    @Data
    public static class Cpu {
        private Integer count;
        private Double percent;
    }

    @Data
    public static class Memory {
        private Long total;
        private Long used;
        private Long free;
        private Double percent;
    }

    @Data
    public static class Disk {
        private Long total;
        private Long used;
        private Long free;
        private Double percent;
    }
}
