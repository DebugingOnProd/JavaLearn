package org.lhq.design.builder;

import org.lhq.design.builder.entity.*;

public interface Builder {
    Builder buildCPU(CPU cpu);

    Builder buildRAM(RAM ram);

    Builder buildGPU(GPU gpu);

    Builder buildDisk(Disk disk);

    Builder buildPowerSupply(PowerSupply powerSupply);

    Builder buildMotherboard(Motherboard motherboard);

    Computer build();
}
