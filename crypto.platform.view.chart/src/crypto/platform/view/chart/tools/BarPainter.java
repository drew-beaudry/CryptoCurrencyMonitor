package crypto.platform.view.chart.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;

import org.jfree.chart.renderer.xy.XYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.ui.RectangleEdge;

public class BarPainter implements XYBarPainter {

  private int barWidthAdjust = -10;
  private Color barColor = new Color(255, 51, 51);

  @Override
  public void paintBarShadow(
      Graphics2D g2,
      XYBarRenderer renderer,
      int row,
      int column,
      RectangularShape bar,
      RectangleEdge base,
      boolean pegShadow) {

    bar.setFrame(bar.getX(), bar.getY(), bar.getWidth() + barWidthAdjust + 4, bar.getHeight());
    g2.setBackground(Color.black);
    // g2.setColor(Color.green.darker());
    g2.fill(bar);
    g2.draw(bar);
  }

  @Override
  public void paintBar(
      Graphics2D g2,
      XYBarRenderer renderer,
      int row,
      int column,
      RectangularShape bar,
      RectangleEdge base) {
    bar.setFrame(bar.getX(), bar.getY(), bar.getWidth() + barWidthAdjust, bar.getHeight());

    RectangularShape outline = new Rectangle();
    outline.setFrame(bar.getX() + 2, bar.getY() - 2, bar.getWidth() + 2, bar.getHeight() + 2);
    g2.setColor(barColor.darker());
    g2.fill(outline);
    g2.draw(outline);

    // Fills in corner
    g2.fillPolygon(
        new int[] {(int) bar.getX(), (int) (bar.getX() + 2), (int) (bar.getX() + 2)},
        new int[] {(int) bar.getY(), (int) (bar.getY() - 2), (int) bar.getY()},
        3);

    g2.setColor(barColor);
    g2.fill(bar);
    g2.draw(bar);
  }
}
