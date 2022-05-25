package org.lhq.design.builder;

import org.lhq.design.builder.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComputerBuilder implements Builder {
    private Computer computer = new Computer();

    @Override
    public Builder buildCPU(CPU cpu) {
        computer.setCpu(cpu);
        return this;
    }

    @Override
    public Builder buildRAM(RAM ram) {
        computer.setRam(ram);
        return this;
    }

    @Override
    public Builder buildGPU(GPU gpu) {
        List<GPU> gpuList = computer.getGpuList();
        gpuList = Optional.ofNullable(gpuList).orElse(new ArrayList<>());
        gpuList.add(gpu);
        computer.setGpuList(gpuList);
        return this;
    }

    @Override
    public Builder buildDisk(Disk disk) {
        List<Disk> diskList = computer.getDiskList();
        diskList = Optional.ofNullable(diskList).orElse(new ArrayList<>());
        diskList.add(disk);
        computer.setDiskList(diskList);
        return this;
    }

    @Override
    public Builder buildPowerSupply(PowerSupply powerSupply) {
        computer.setPowerSupply(powerSupply);
        return this;
    }

    @Override
    public Builder buildMotherboard(Motherboard motherboard) {
        computer.setMotherboard(motherboard);
        return this;

    }

    @Override
    public Computer build() {
        return computer;
    }


}
