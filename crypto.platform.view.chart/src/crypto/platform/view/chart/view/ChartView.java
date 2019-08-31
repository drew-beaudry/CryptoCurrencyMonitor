package crypto.platform.view.chart.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import crypto.api.cryptocompare.constants.Constants;
import crypto.platform.service.Service;
import crypto.platform.view.chart.Activator;
import crypto.platform.view.chart.component.ChartComponent;
import crypto.platform.view.chart.controller.ChartController;

/**View where charts are drawn*/
public class ChartView extends ViewPart {

  public static final String ID = "crypto.platform.view.chart.view";
  private static Service service = Activator.getService();
  private JFreeChart chart;
  
  @Override
  public void createPartControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.EMBEDDED);
    java.awt.Frame chartFrame = SWT_AWT.new_Frame(composite);
    ChartController controller = new ChartComponent().create(composite);
    service.addCreateChartListener(
        (c) -> {
          chartFrame.removeAll();
          String type = c.getName();
         if (type.equals(Constants.BARGRAPH)) 
           chart = controller.createBarGraph();
   
         else if (type.equals(Constants.LINEGRAPH)) 
           chart = controller.createLineGraph();
          
         else if (type.equals(Constants.PIECHART)) 
           chart = controller.createPieChart();
         
         else if (type.equals(Constants.CANDLESTICKCHART))
           chart = controller.createCandleStickChart();
         
          ChartPanel chartPanel = new ChartPanel(chart);
          chartFrame.add(chartPanel);
          chartFrame.setVisible(true);
        });

  }

  @Override
  public void setFocus() {
    // TODO Auto-generated method stub
  }
}
