package TNVL;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Plank extends JPanel {

    public void setE1(double E1) {
        this.E1 = E1;
    }

    public void setF1(double f1) {
        this.f1 = f1;
    }
    private double E, E1;
    private double f, f1 = 7.5 * Math.pow(10, 14);
    private final double h = 6.625 * Math.pow(10, -34);
    private double A;

    public void setA(double A) {
        this.A = A;
    }

    private final int padding = 30;
    private final int labelPadding = 0;
    private final Color lineColor = new Color(44, 102, 230, 180);
    private final Color gridColor = new Color(200, 200, 200, 200);
    private final int pointWidth = 4;
    private final int numberYDivisions = 6;
    private List<Double> scores;

    public Plank(List<Double> scores, double A) {
        this.scores = scores;
        this.A = A;
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);

            graphPoints.add(new Point(x1, y1));
        }

        // <editor-fold defaultstate="collapsed" desc=" background, truc x, truc y ">
        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        //tạo đơn vị cho 2 trục
        String donviE = "E(eV)";
        String donviF = "xE14";
        g2.drawString(donviE, padding + labelPadding - 17, padding - 10);
        g2.drawString(donviF,
                getWidth() - padding + 5 - 20 + 14,
                (getHeight() - padding - padding - labelPadding) / 2 + padding + labelPadding - 3);
        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;

            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel;
                yLabel = (i - 3) + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
        }

        // and for x axis
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    if (i != 0) {
                        g2.drawString(xLabel,
                                x0 - labelWidth / 2,
                                (padding + getHeight() - padding - labelPadding) / 2 + 1 + metrics.getHeight() + 3);
                    }
                }
            }
        }

        // create x and y axes (double)x
        //truc y
        int y11 = (padding + getHeight() - padding - labelPadding) / 2 + 1;
        g2.drawLine(padding + labelPadding, y11, getWidth() - padding, y11);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        //truc x
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
        // </editor-fold>

        //ve duong thang giua cac diem
        int donviX = (getWidth() - 2 * padding - labelPadding) / (scores.size() - 1);
        int donviY = (getHeight() - padding - padding - labelPadding) / numberYDivisions;
        int x00 = padding + labelPadding;
        int y00 = padding + numberYDivisions / 2 * donviY;

        int x1_pixel, y1_pixel, x2_pixel, y2_pixel;
        
        E = 2.9 * 1.6 * Math.pow(10, -19);       
        
        f = (E + A) / h;                      
        x1_pixel = x00;
        y1_pixel = y00 + (int) (donviY * A / (1.6E-19));
        
        x2_pixel = (int) (x00 + donviX * f / 1E14);
        y2_pixel = (int) (y00 - donviY * E / (1.6E-19));

        g2.setColor(lineColor);
        g2.setStroke(new BasicStroke(3f));
        g2.drawLine(x1_pixel, y1_pixel, x2_pixel, y2_pixel);
        int f1_pixel = (int) (x00 + donviX * f1 / 1E14);
        E1 = h * f1 - A;
        int E1_pixel = (int) (y00 - donviY * E1 / (1.6E-19));
        if (E1_pixel > 26 && f1<12.45826415E14) {
            g2.fillOval(f1_pixel-5, E1_pixel-5, 10, 10);
        } else {
            E1_pixel = y2_pixel;
            f1_pixel = x2_pixel;
            g2.fillOval(f1_pixel-5, E1_pixel-5, 10, 10);
        }
    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Double> getScores() {
        return scores;
    }
}
