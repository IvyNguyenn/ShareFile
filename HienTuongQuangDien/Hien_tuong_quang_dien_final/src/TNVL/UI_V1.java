package TNVL;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.QuadCurve2D;
import java.util.List;
import javax.swing.JPanel;

public class UI_V1 extends JPanel {

    private int width = 400;
    private int heigth = 100;
    private int padding = 27;
    private int labelPadding = 0;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke st = new BasicStroke(1.8f);
    private int pointWidth = 4;
    //private int numberYDivisions = 10;
    private int numberYDivisions = 8;
    private double lamda, cd, A;
    private double I0bh = 60, U0bh = 28, I01 = 4.5, U0 = 0, delLamda = 150, I0 = 0;
    //private static double Ibhbd1 = 58, U0bh = 28, Ibh1 = 5, U0 = 0, I0 = 0;
    private final double h = 6.625 * Math.pow(10, -34);
    private final double c = 3E8;
    private final double e = 1.6E-19;

    private double U = 0;
    private double I = 5;
    private final Stroke net = new BasicStroke(6f);

    public void setLamda(double lamda) {
        this.lamda = lamda;
    }

    public void setCd(double cd) {
        this.cd = cd;
    }

    public void setA(double A) {
        this.A = A;
    }

    public void setU(double U) {
        this.U = U;
    }

    public double getI() {
        return I;
    }

    private List<Double> scores;

    public UI_V1(List<Double> scores, double lamda, double cd, double A) {
        this.scores = scores;
        this.cd = cd;
        this.lamda = lamda;
        this.A = A;
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double x11 = (getWidth() - labelPadding - padding - padding) / (scores.size() - 1);//đơn vị ô x trong bảng
        double x22 = (getHeight() - labelPadding - padding - padding) / (scores.size() - 2);
        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);
        g2.drawString("( µA )", labelPadding + padding + (int) x11 + 7, padding);
        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding - 3 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                if (i != 0) {
                    String yLabel = i * 10 + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(yLabel);
                    g2.drawString(yLabel, (int) x11 + x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
                }
            }
            g2.drawLine(x0 + (int) x11, y0, x1 + (int) x11, y1);// vẽ gạch chia truc y
        }
        // and for x axis
        g2.drawString("( V )", getWidth() - padding + 6, getHeight() - padding - labelPadding);
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);// vẽ vạch chia
                    g2.setColor(Color.BLACK);
                    String xLabel = (i - 1) * 10 + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }
        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
        // ve truc y
        g2.drawLine(padding + labelPadding + (int) x11, getHeight() - padding - labelPadding, padding + labelPadding + (int) x11, padding);
        Graphics2D g5 = (Graphics2D) g2.create();
        double donviX = (double) x11 / 10, donviY = (double) x22 / 10;
        double U1 = 0, I1 = cd / 100 * I01;
        double U2 = U0bh, Imax = cd / 100 * (I0bh + 2);
        double Uh = -((h * c) / lamda - A) / e;
        if (cd > 0 && lamda <= 549E-9) {
            double x1 = U2;
            double y1 = Imax;
            double x3 = 0;
            double y3 = I1;
            double x2 = x1 * 0.4;
            double y2 = y1 * 0.65;
            double c = y3;
            double a = (y3 * x1 - y2 * x1 - y3 * x2 + y1 * x2) / (x1 * x1 * x2 - x2 * x2 * x1);
            double b = (-a * x1 * x1 - c + y1) / x1;
            double u1 = x11 + x3 * donviX + labelPadding + padding;
            double i1 = getHeight() - labelPadding - padding - y3 * donviY;
            x2 = U2 * 0.0625;
            y2 = a * x2 * x2 + b * x2 + c;
            double u2 = x11 + x2 * donviX + labelPadding + padding;
            double i2 = getHeight() - labelPadding - padding - y2 * donviY;
            g5.setColor(Color.RED);
            g5.setStroke(st);
            QuadCurve2D.Double curve = new QuadCurve2D.Double();
            for (double i = 0.125; i <= 1; i += 0.0625) {
                x3 = U2 * i;
                y3 = a * x3 * x3 + b * x3 + c;
                double u3 = x11 + x3 * donviX + labelPadding + padding;
                double i3 = getHeight() - labelPadding - padding - y3 * donviY;
                curve.setCurve(u1, i1, u2, i2, u3, i3);
                g5.draw(curve);
                u1 = u2;
                i1 = i2;
                u2 = u3;
                i2 = i3;
            }
            double u4 = x11 + U2 * donviX + labelPadding + padding;
            double v4 = getHeight() - labelPadding - padding - Imax * donviY;
            g5.drawLine((int) u4, (int) v4, getWidth() - padding, (int) v4);

            double uh = x11 + Uh * donviX + labelPadding + padding;
            double vh = getHeight() - labelPadding - padding;
            double u5 = x11 + U1 * donviX + labelPadding + padding;
            double v5 = getHeight() - labelPadding - padding - I1 * donviY;
            double u6 = x11 + (Uh / 5) * donviX + labelPadding + padding;
            double v6 = getHeight() - labelPadding - padding - (I1 / 200) * donviY + I1 / 4;
            curve.setCurve(uh, vh, u6, v6, u5, v5);
            g5.draw(curve);
            if (U >= 0) {
                I = a * U * U + b * U + c;
                double u = x11 + U * donviX + labelPadding + padding;
                double v = getHeight() - labelPadding - padding - I * donviY;
                g5.setColor(Color.BLUE);
                g5.setStroke(net);
                g5.drawLine((int) u, (int) v, (int) u, (int) v);
            }
        }
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
