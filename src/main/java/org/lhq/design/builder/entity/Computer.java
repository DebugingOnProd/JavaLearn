package org.lhq.design.builder.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@Data
public class Computer {
    private CPU cpu;
    private RAM ram;
    private Motherboard motherboard;
    private List<GPU> gpuList;
    private List<Disk> diskList;
    private PowerSupply powerSupply;
}
