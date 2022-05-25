package org.lhq.design.builder;

import org.lhq.design.builder.entity.CPU;
import org.lhq.design.builder.entity.GPU;
import org.lhq.design.builder.entity.RAM;

public class Director {
    private Builder builder;

    public void construct(Builder builder) {
        this.builder = builder;
    }
    public void construct(CPU cpu, RAM ram, GPU gpu){
        this.builder.buildGPU(gpu);
        this.builder.buildRAM(ram);
        this.builder.buildCPU(cpu);
    }

}

