package com.upc.tplanner.TPlanner.utils;

import com.upc.tplanner.TPlanner.advertising.model.Advertising;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSelection {

    public static Advertising getRandomAdvertising(List<Advertising> advertisements) {

        Random random = new Random();
        var totalAmount = advertisements.stream().mapToDouble(Advertising::getAmountInvested).sum();
        var randomAmount = random.nextDouble() * totalAmount;

        Collections.shuffle(advertisements, new Random());

        for (var advertisement : advertisements) {
            randomAmount -= advertisement.getAmountInvested();
            if (randomAmount <= 0) {
                return advertisement;
            }
        }
        return null;
    }
}
