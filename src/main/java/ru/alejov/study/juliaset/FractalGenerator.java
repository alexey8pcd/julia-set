package ru.alejov.study.juliaset;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FractalGenerator {

    private final int maxIter;
    private final int width;
    private final int height;
    private final ExecutorService executorService;
    private final BufferedImage generated;
    private final int[] array;
    private final int threadCount;
    private volatile int maxIdx;

    public FractalGenerator(int threadCount, int maxIter, int width, int height) {
        this.maxIter = maxIter;
        this.width = width;
        this.height = height;
        this.executorService = Executors.newFixedThreadPool(threadCount);
        this.threadCount = threadCount;
        this.array = new int[width * height];
        this.generated = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        maxIdx = 0;
    }

    public BufferedImage generate(ComplexNumber c) {
        double r = calculateR(c);
        Params params = new Params(r);
        int startX = width * 4 / 10;
        int partsCount = height / 64;
        if (threadCount > 1) {
            parallelGenerateByHeight(c, params, startX, width, height, partsCount);
        } else {
            seqTask(c, params, startX);
        }
        return generated;
    }

    private void seqTask(ComplexNumber c, Params params, int startX) {
        task(c, params, startX, width, 0, height);
        generated.setRGB(0, 0, width, height, array, 0, width);
    }

    private record Params(double r,
                          double xMin,
                          double yMin,
                          double xMax,
                          double yMax) {

        public Params(double r) {
            this(r, -r, -r, r, r);
        }

        public double xStep(double width) {
            return Math.abs(xMax - xMin) / width;
        }

        public double yStep(double height) {
            return Math.abs(yMax - yMin) / height;
        }
    }

    private void parallelGenerateByHeight(ComplexNumber c,
                                          Params params,
                                          int startX,
                                          int endX,
                                          int height,
                                          int partsCount) {
        int perThreadSize = Math.round(height / (float) partsCount);
        this.maxIdx = 0;
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < partsCount; i++) {
            int finalI = i;
            tasks.add(() -> {
                int partStartY = finalI * perThreadSize;
                int partEndY = (finalI + 1) * perThreadSize;
                if (partEndY > height - perThreadSize) {
                    partEndY = height;
                }
                task(c, params, startX, endX, partStartY, partEndY);
                return null;
            });
        }
        try {
            List<Future<Void>> futures = executorService.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get();
            }
            generated.setRGB(0, 0, width, height, array, 0, width);
            Arrays.fill(array, 0);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void task(ComplexNumber c,
                      Params params,
                      int startX,
                      int endX,
                      int startY,
                      int endY) {
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {

                double x = params.xMin() + i * params.xStep(width);
                double y = params.yMin() + j * params.yStep(height);
                ComplexNumber z = new ComplexNumber(x, y);
                int idx = sqPolyIteration(z, c, params.r()) - 1;
                if (maxIdx < idx) {
                    maxIdx = idx;
                }
                int color = complexHeatMap(idx, maxIdx, z.getModule() / params.r());
                array[j * width + i] = color;
                int xx = width - i - 1;
                int yy = height - j - 1;
                array[yy * width + xx] = color;
            }
        }
    }

    private int sqPolyIteration(ComplexNumber z, ComplexNumber c, double r) {
        int counter = 1;
        ComplexNumber lastElement = z;
        for (int i = 0; i < this.maxIter; i++) {
            if (lastElement.getModule() >= r) {
                break;
            }
            ComplexNumber z2 = ComplexNumber.multi(lastElement, lastElement);
            lastElement = ComplexNumber.add(z2, c);
            ++counter;
        }
        return counter;
    }

    private static double calculateR(ComplexNumber c) {
        return (1 + Math.sqrt(1 + 4 * c.getModule())) / 2;
    }

    private static int complexHeatMap(double value,
                                      double max,
                                      double v) {
        double bright = value / max;
        int red = (int) (255 * bright) << 16;
        int green = (int) (255 * (1 - bright)) << 8;
        int blue;
        if (v > 1) {
            blue = 255;
        } else {
            blue = (int) (255 * v);
        }
        return blue + green + red;
    }
}
