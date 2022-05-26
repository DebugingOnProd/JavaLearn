package org.lhq.design.builder;

import org.lhq.design.builder.entity.*;

public class Director {
    private Builder builder;

    public void construct(Builder builder) {
        this.builder = builder;
    }

    public void constructGamingPC(){
        this.builder.buildGPU(GPU.RTX3090);
        this.builder.buildRAM(RAM.THIRTY_TWO_GIGABYTES);
        this.builder.buildCPU(CPU.I9);
        this.builder.buildDisk(Disk.SSD_1T);
        this.builder.buildMotherboard(Motherboard.ATX);
        this.builder.buildPowerSupply(PowerSupply.CORSAIR);
    }
    public void constructOfficePC(){
        this.builder.buildCPU(CPU.I3);
        this.builder.buildGPU(GPU.RTX3060);
        this.builder.buildRAM(RAM.SIXTEEN_GIGABYTES);
    }

}

