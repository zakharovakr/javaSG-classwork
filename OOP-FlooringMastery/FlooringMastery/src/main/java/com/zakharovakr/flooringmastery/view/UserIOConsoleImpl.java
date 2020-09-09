/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.view;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author kristinazakharova
 */
public class UserIOConsoleImpl implements UserIO {

    private Scanner input = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.print(msg);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double value;
        try {
            value = Double.parseDouble(input.nextLine());
        } catch (NumberFormatException e) {
            return readDouble("Please enter a valid number: ");
        }
        return value;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double value;
        try {
            value = Double.parseDouble(input.nextLine());
        } catch (NumberFormatException e) {
            return readDouble("Please enter a valid number: ", min, max);
        }
        if (value > max || value < min) {
            return readDouble("Please enter a number between the range of " + min + " and " + max + ".", min, max);
        }
        return value;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float value;
        try {
            value = Float.parseFloat(input.nextLine());
        } catch (NumberFormatException e) {
            return readFloat("Please enter a valid number: ");
        }
        return value;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float value;
        try {
            value = Float.parseFloat(input.nextLine());
        } catch (NumberFormatException e) {
            return readFloat("Please enter a valid number: ", min, max);
        }
        if (value < min || value > max) {
            return readFloat("Please enter a number between the range of " + min + " and " + max + ".", min, max);
        }
        return value;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int value;
        try {
            value = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return readInt("Please enter a valid number: ");
        }
        return value;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int value;
        try {
            value = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return readInt("Please enter a valid number: ", min, max);
        }
        if (value < min || value > max) {
            return readInt("Please enter a number between the range of " + min + " and " + max + ".", min, max);
        }
        return value;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long value;
        try {
            value = Long.parseLong(input.nextLine());
        } catch (NumberFormatException e) {
            return readLong("Please enter a valid number: ");
        }
        return value;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long value;
        try {
            value = Long.parseLong(input.nextLine());
        } catch (NumberFormatException e) {
            return readLong("Please enter a valid number: ", min, max);
        }

        if (value < min || value > max) {
            return readLong("Please enter a number between the range of " + min + " and " + max + ".", min, max);
        }
        return value;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        BigDecimal value;
        try {
            value = new BigDecimal(input.nextLine());
        } catch (NumberFormatException e) {
            return readBigDecimal("Please enter a valid number: ");
        }
        return value;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min) {
        System.out.println(prompt);
        BigDecimal value;
        try {
            value = new BigDecimal(input.nextLine());
        } catch (NumberFormatException e) {
            return readBigDecimal("Please enter a valid number: ", min);
        }
        if (value.compareTo(min) == -1) {
            return readBigDecimal("Please enter a number greater than " + min.toString() + ".");
        }
        return value;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        System.out.println(prompt);
        BigDecimal value;
        try {
            value = new BigDecimal(input.nextLine());
        } catch (NumberFormatException e) {
            return readBigDecimal("Please enter a valid number: ", min, max);
        }
        if (value.compareTo(min) == -1 || value.compareTo(max) == 1) {
            return readBigDecimal("Please enter a number between the range of " + min.toString() + " and " + max.toString() + ".", min, max);
        }
        return value;
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        System.out.println(prompt);
        LocalDate date;
        try {
            date = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("MMddyyyy"));
        } catch (DateTimeException e) {
            return readLocalDate("Please input a date with the format MMDDYYYY");
        }

        return date;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }
}
