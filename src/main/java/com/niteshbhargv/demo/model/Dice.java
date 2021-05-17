package com.niteshbhargv.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
public class Dice {

    @Value("${game.dice}")
    private Integer dice;

    public int roll() {
        //System.out.println(dice);
        int roll =(int) (Math.random()*dice + 1);
        //System.out.println("roll " + roll);
        return roll;
    }
}
