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

public class UI_V2 extends JPanel {

    private int width = 410;
    private int heigth = 210;
    private int padding = 25;//25;
    private int labelPadding = 0;//25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke st = new BasicStroke(1.9f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private double lamda, cd, A;
    private final double h = 6.625 * Math.pow(10, -34);
    private final double c = 3E8;
    private final double e = 1.6E-19;
    private double I0bh = 58, U0bh = 28, I01 = 4.5, U0 = 0;

    private double U = 0;
    private double I = 5;
    private final Stroke net = new BasicStroke(6f);
    private int wlp = 385; // getWidth()-labelPadding-padding
    private int hlp = 185; // getHeight()-labelPadding-padding

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
        //System.out.println(this.U);
    }

    public double getI() {
        return I;
    }

    private List<Double> scores;

    public UI_V2(List<Double> scores, double lamda, double cd, double A) {
        this.scores = scores;
        this.lamda = lamda;
        this.cd = cd;
        this.A = A;
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x11 = (getWidth() - labelPadding - padding - padding) / (scores.size() - 1);//đơn vị ô x trong bảng
        int x22 = (getHeight() - labelPadding - padding - padding) / (scores.size() - 3);
        int viTriTung = (getWidth() - labelPadding - padding - padding);
        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);
        g2.drawString("( µA )", getWidth() - padding - padding / 2, padding / 2);
        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i += 2) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding - 3 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = i / 2 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, viTriTung + padding / 2 + 7 + x0 - labelWidth - 4, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0 + viTriTung - 4, y0, x1 + viTriTung - 4, y1);// vẽ gạch chia truc y
        }
        // and for x axis
        g2.drawString("( V )", padding / 7, getHeight() - padding - labelPadding);
        for (int i = 0; i < scores.size() - 1; i += 2) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);// vẽ vạch chia
                    g2.setColor(Color.BLACK);
                    String xLabel = "-" + ((11 - i) / 2) + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }
        //truc x
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
        // ve truc y
        g2.drawLine(padding + labelPadding + viTriTung, getHeight() - padding - labelPadding, padding + labelPadding + viTriTung, padding);
        Graphics2D g5 = (Graphics2D) g2.create();
        double donviX = (double) x11, donviY = (double) x22;
        double U1 = ((h * c) / lamda - A) / e, I1 = 0;
        double U2 = 0, I2 = cd / 100 * I01;

        if (cd > 0 && lamda <= 549E-9) {
            double x1 = -U1;
            double y1 = 0;
            double x3 = 0;
            double y3 = I2;
            double x2 = -U1 / 2;
            double y2 = y3 / 4.3;
            double c = y3;
            double a = (y3 * x1 - y2 * x1 - y3 * x2) / (x1 * x1 * x2 - x2 * x2 * x1);
            double b = (-a * x1 * x1 - c) / x1;
            double u1 = wlp + x3 * 2 * donviX;
            double i1 = hlp - y3 * 1.6 * donviY;
            x2 = -U1 * 0.0625;
            y2 = a * x2 * x2 + b * x2 + c;
            double u2 = wlp + x2 * 2 * donviX;
            double i2 = hlp - y2 * 1.6 * donviY;
            g5.setColor(Color.RED);
            g5.setStroke(st);
            QuadCurve2D.Double curve = new QuadCurve2D.Double();
            for (double i = 0.125; i <= 1; i += 0.0625) {
                x3 = -U1 * i;
                y3 = a * x3 * x3 + b * x3 + c;
                double u3 = wlp + x3 * 2 * donviX;
                double i3 = hlp - y3 * 1.6 * donviY;
                curve.setCurve(u1, i1, u2, i2, u3, i3);
                g5.draw(curve);
                u1 = u2;
                i1 = i2;
                u2 = u3;
                i2 = i3;
            }
            if (U > -U1 && U <= 0) {
                I = a * U * U + b * U + c;
                double u = getWidth() - labelPadding - padding - (-U) * 2 * donviX;
                double v = getHeight() - labelPadding - padding - I * 1.6 * donviY;
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
