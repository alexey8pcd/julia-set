package ru.alejov.study.juliaset;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.LocalDateTime;

public class Surface extends Canvas implements Runnable {

    private final Canvas canvas;
    private int width;
    private int threadCount;
    private ComplexNumber complexNumber;
    private BufferedImage image = null;
    private double increment;
    private boolean isIncrease;
    private FractalGenerator fractalGenerator;
    private int depth;
    private final Thread thread = new Thread(this);

    public Surface(Canvas canvas) {
        this.canvas = canvas;
    }

    public void start(ComplexNumber c, int width, int height, int depth, int threadCount) {
        this.depth = depth;
        this.complexNumber = c;
        this.width = width;
        this.threadCount = threadCount;
        this.fractalGenerator = new FractalGenerator(threadCount, depth, width, height);
        increment = 0.01;
        isIncrease = true;
        thread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            LocalDateTime begin = LocalDateTime.now();
            update();
            Duration duration = Duration.between(begin, LocalDateTime.now());
            long millis = duration.toMillis();
            int fps = (int) (1000. / millis);
            render(fps);
        }
    }

    private void render(int fps) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        if (bufferStrategy == null) {
            canvas.createBufferStrategy(2);
            requestFocus();
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        display(graphics);
        graphics.setColor(Color.RED);
        graphics.drawString("D: " + depth + ", T: " + threadCount + ", FPS: " + fps, width / 2, 20);
        graphics.dispose();
        bufferStrategy.show();
    }

    private void display(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        }
    }

    private void update() {
        double r = complexNumber.getR();
        double i;
        double m = 0.85;
        if (isIncrease) {
            r += increment;
            if (r < 0) {
                i = -m * Math.cos(r * Math.PI / 2);
            } else {
                i = -m * Math.cos(r * Math.PI);
            }
            if (r >= 0.5) {
                isIncrease = false;
            }
        } else {
            r -= increment;
            if (r < 0) {
                i = m * Math.cos(r * Math.PI / 2);
            } else {
                i = m * Math.cos(r * Math.PI);
            }
            if (r < -1) {
                isIncrease = true;
            }
        }
        complexNumber = new ComplexNumber(r, i);
        image = fractalGenerator.generate(complexNumber);
    }

    public void stop() {
        thread.interrupt();
        while (!thread.isAlive()) {
            thread.interrupt();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
