package ru.alejov.study.juliaset;

import java.awt.Color;
import java.awt.Graphics;

public class ComplexNumber {

    private final double r;
    private final double i;
    private final double module;

    public ComplexNumber(double r, double i) {
        this.r = r;
        this.i = i;
        this.module = Math.sqrt(i * i + r * r);
    }

    public ComplexNumber(ComplexNumber complexNumber) {
        this(complexNumber.r, complexNumber.i);
    }

    public double getR() {
        return r;
    }

    public double getI() {
        return i;
    }

    public static ComplexNumber add(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.getR() + b.getR(), a.getI() + b.getI());
    }

    public static ComplexNumber multi(ComplexNumber a, ComplexNumber b) {
        double r = a.getR() * b.getR() - a.getI() * b.getI();
        double i = a.getI() * b.getR() + a.getR() * b.getI();
        return new ComplexNumber(r, i);
    }

    public static ComplexNumber getConjugate(ComplexNumber a) {
        return new ComplexNumber(a.getR(), -a.getI());
    }

    public static ComplexNumber divide(ComplexNumber a, ComplexNumber b) {
        ComplexNumber enumerator = multi(a, getConjugate(b));
        double denominator = b.getR() * b.getR() + b.getI() * b.getI();
        return new ComplexNumber(enumerator.getR() / denominator,
                enumerator.getI() / denominator);
    }

    public double getModule() {
        return module;
    }
}
