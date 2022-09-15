package org.lhq.leetcode.struc;

public class ParkingSystem {


    private int big;
    private int medium;
    private int small;

    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        switch (carType) {
            case 1: {
                if (small > 0) {
                    small--;
                    return true;
                }
            }
            case 2: {
                if (medium > 0) {
                    medium--;
                    return true;
                }
            }
            case 3: {
                if (big > 0) {
                    big--;
                    return false;
                }
            }


        }
        return false;
    }
}
