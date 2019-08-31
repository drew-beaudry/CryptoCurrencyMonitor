package crypto.platform.view.chart.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Composite;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;

import crypto.model.historical.HistoCoin;
import crypto.platform.service.Service;
import crypto.platform.ui.api.controller.ComponentController;
import crypto.platform.view.chart.Activator;
import crypto.platform.view.chart.controller.AlignedDateAxis.TickLabelPosition;
import crypto.platform.view.chart.tools.BarPainter;
import crypto.platform.view.chart.tools.CustomCandlestickRenderer;
import crypto.platform.view.chart.tools.CustomCandlestickRenderer;

public class ChartController extends ComponentController {

  private Service service = Activator.getService();
  private OHLCSeries ohlcSeries;
  private TimeSeries volumeSeries;
  private static final Logger log = Logger.getLogger(ChartController.class);
  private BarPainter painter = new BarPainter();

  // TODO: Create colors here, manage disposal of colors

  public ChartController(Composite parent) {
    super(parent);
  }

  public String getTitle() {
    if (service.getCurrency() != null) {
      String title = "Volume By Date: " + service.getCurrency().getName();
      return title;
    } else return "";
  }

  public CategoryDataset createBarGraphDataset() {

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    List<HistoCoin> list = service.getHistoData();
    long unixSeconds;
    double volume;
    Date date;
    String fDate;
    SimpleDateFormat sdf;
    sdf = new java.text.SimpleDateFormat("MM/dd");
    sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

    for (HistoCoin c : list) {
      unixSeconds = c.getTime();
      date = new java.util.Date(unixSeconds * 1000L);
      fDate = sdf.format(date);
      volume = c.getVolumefrom();
      dataset.addValue(volume, "one", fDate);
    }
    return dataset;
  }

  private XYDataset createLineGraphDataset() {
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    volumeSeries = new TimeSeries("Volume");
    ohlcSeries = new OHLCSeries("Unused");
    addCandles();
    dataset.addSeries(volumeSeries);

    return dataset;
  }

  public JFreeChart createLineGraph() {

    XYDataset dataset = createLineGraphDataset();

    // Create volume chart volumeAxis
    NumberAxis volumeAxis = new NumberAxis("Volume");
    volumeAxis.setAutoRangeIncludesZero(false);
    volumeAxis.setNumberFormatOverride(new DecimalFormat("0"));

    // Create volume chart renderer
    XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer();
    lineRenderer.setBaseToolTipGenerator(
        new StandardXYToolTipGenerator(
            "Volume--> Time={1} Size={2}", new SimpleDateFormat("MM:dd"), new DecimalFormat("0")));

    // Creating charts common dateAxis
    AlignedDateAxis dateAxis = new AlignedDateAxis(TickLabelPosition.INTERVAL_MIDDLE);
    dateAxis.setDateFormatOverride(new SimpleDateFormat("MM/dd"));
    dateAxis.setLabel("Date");
    // reduce the default left/right margin from 0.05 to 0.02
    dateAxis.setLowerMargin(0.02);
    dateAxis.setUpperMargin(0.02);

    JFreeChart chart =
        ChartFactory.createXYLineChart(
            getTitle(), "Date", "Volume", dataset, PlotOrientation.VERTICAL, false, true, false);
    chart.removeLegend();

    XYPlot chartPlot = chart.getXYPlot();
    chartPlot.setDomainAxis(dateAxis);
    chartPlot.setRangeAxis(volumeAxis);
    chartPlot.setDataset(dataset);
    chartPlot.setRenderer(lineRenderer);
    chartPlot.setBackgroundPaint(Color.white);
    chartPlot.setRangeGridlinesVisible(true);
    chartPlot.setRangeGridlinePaint(Color.black);
    chartPlot.setDomainGridlinesVisible(true);
    chartPlot.setDomainGridlinePaint(Color.gray);
    chart.setBackgroundPaint(new Color(238, 238, 238));

    return chart;
  }

  public JFreeChart createShittyLineGraph() {
    DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

    List<HistoCoin> list = service.getHistoData();
    long unixSeconds;
    double volume;
    Date date;
    String fDate;
    SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd");

    sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

    for (HistoCoin c : list) {
      unixSeconds = c.getTime();
      date = new java.util.Date(unixSeconds * 1000L);
      fDate = sdf.format(date);
      volume = c.getVolumefrom();
      line_chart_dataset.addValue(volume, "key", fDate);
    }

    JFreeChart lineChartObject =
        ChartFactory.createLineChart(
            "Volume: " + service.getCurrency().getName(),
            "Date",
            "Volume",
            line_chart_dataset,
            PlotOrientation.VERTICAL,
            false,
            true,
            false);

    CategoryPlot plot = (CategoryPlot) lineChartObject.getPlot();
    CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(
        CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
    domainAxis.setUpperMargin(0);
    domainAxis.setLowerMargin(0);

    plot.setDomainGridlinesVisible(true);
    plot.setRangeGridlinesVisible(true);
    plot.setRangeGridlinePaint(Color.black);
    plot.setDomainGridlinePaint(Color.gray);

    plot.setBackgroundPaint(Color.white);

    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    plot.setRenderer(renderer);

    return lineChartObject;
  }

  public PieDataset createPieDataset() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue("IPhone 5s", new Double(20));
    dataset.setValue("SamSung Grand", new Double(20));
    dataset.setValue("MotoG", new Double(40));
    dataset.setValue("Nokia Lumia", new Double(10));
    return dataset;
  }

  public JFreeChart createPieChart() {
    PieDataset dataset = createPieDataset();

    JFreeChart chart =
        ChartFactory.createPieChart(
            "Mobile Sales", // chart title
            dataset, // data
            true, // include legend
            true,
            false);

    return chart;
  }

  public JFreeChart createBarGraph() {
    XYDataset dataset = createLineGraphDataset();

    // Create volume chart volumeAxis
    NumberAxis volumeAxis = new NumberAxis("Volume");
    volumeAxis.setAutoRangeIncludesZero(false);
    // volumeAxis.setNumberFormatOverride(new DecimalFormat("0"));
    
    // Create volume chart renderer
    XYBarRenderer.setDefaultBarPainter(painter);
    XYBarRenderer timeRenderer = new XYBarRenderer();
    timeRenderer.setShadowVisible(false);

    timeRenderer.setBaseToolTipGenerator(
        new StandardXYToolTipGenerator(
            "Volume--> Time={1} Size={2}", new SimpleDateFormat("MM:dd"), new DecimalFormat("0")));

    // Creating charts common dateAxis
    AlignedDateAxis dateAxis = new AlignedDateAxis(TickLabelPosition.INTERVAL_MIDDLE);
    dateAxis.setDateFormatOverride(new SimpleDateFormat("MM/dd"));
    dateAxis.setLabel("Date");

    // reduce the default left/right margin from 0.05 to 0.02
    dateAxis.setLowerMargin(0.02);
    dateAxis.setUpperMargin(0.02);

    // Create volumeSubplot
    XYPlot volumeSubplot = new XYPlot(dataset, null, volumeAxis, timeRenderer);
    volumeSubplot.setBackgroundPaint(Color.white);
    ((XYBarRenderer) volumeSubplot.getRenderer()).setBarPainter(painter);

    volumeSubplot.setRangeGridlinesVisible(true);
    volumeSubplot.setRangeGridlinePaint(Color.black);
    volumeSubplot.setDomainGridlinesVisible(true);
    volumeSubplot.setDomainGridlinePaint(Color.gray);

    // Create mainPlot
    CombinedDomainXYPlot mainPlot = new CombinedDomainXYPlot(dateAxis);
    mainPlot.setGap(10.0);
    mainPlot.add(volumeSubplot, 1);
    mainPlot.setOrientation(PlotOrientation.VERTICAL);

    JFreeChart chart = new JFreeChart(getTitle(), JFreeChart.DEFAULT_TITLE_FONT, mainPlot, true);
    chart.removeLegend();

    XYPlot chartPlot = chart.getXYPlot();
    chartPlot.setDomainAxis(dateAxis);
    chartPlot.setRangeAxis(volumeAxis);
    chartPlot.setDataset(dataset);
    // chartPlot.setRenderer(lineRenderer);
    chartPlot.setBackgroundPaint(Color.white);
    chart.setBackgroundPaint(new Color(238, 238, 238));

    return chart;
  }

  public JFreeChart createShittyBarGraph() {
    // create the chart...
    CategoryDataset dataset = createBarGraphDataset();

    JFreeChart chart =
        ChartFactory.createBarChart(
            "Volume: " + service.getCurrency().getName(), // chart title
            "Date", // domain axis label
            "Volume", // range axis label
            dataset,
            PlotOrientation.VERTICAL,
            false,
            false,
            false);

    // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

    // get a reference to the plot for further customisation...
    CategoryPlot plot = (CategoryPlot) chart.getPlot();

    // set the range axis to display integers only...
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    // disable bar outlines...
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setDrawBarOutline(false);

    // the SWTGraphics2D class doesn't handle GradientPaint well, so
    // replace the gradient painter from the default theme with a
    // standard painter...
    renderer.setBarPainter(new StandardBarPainter());

    CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(
        CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

    plot.setBackgroundPaint(Color.white);
    plot.setRangeGridlinesVisible(true);
    plot.setRangeGridlinePaint(Color.black);
    // OPTIONAL CUSTOMISATION COMPLETED.

    return chart;
  }

  public JFreeChart createCandleStickChart() {

    /*
     * Creating candlestick subplot
     */
    // Create OHLCSeriesCollection as a price dataset for candlestick chart
    OHLCSeriesCollection candlestickDataset = new OHLCSeriesCollection();
    ohlcSeries = new OHLCSeries("Price");
    candlestickDataset.addSeries(ohlcSeries);

    // Create candlestick chart priceAxis
    NumberAxis priceAxis = new NumberAxis("Price");
    priceAxis.setAutoRangeIncludesZero(false);

    // Create candlestick chart renderer
    CandlestickRenderer renderer =
        new CandlestickRenderer(CandlestickRenderer.WIDTHMETHOD_AVERAGE, false, null);

    renderer.setUseOutlinePaint(true);

    // Create candlestickSubplot
    XYPlot candlestickSubplot = new XYPlot(candlestickDataset, null, priceAxis, renderer);
    candlestickSubplot.setBackgroundPaint(Color.white);
    candlestickSubplot.setRangeGridlinesVisible(true);
    candlestickSubplot.setRangeGridlinePaint(Color.black);
    candlestickSubplot.setDomainGridlinesVisible(true);
    candlestickSubplot.setDomainGridlinePaint(Color.gray);

    /** Creating volume subplot */

    // creates TimeSeriesCollection as a volume dataset for volume chart
    TimeSeriesCollection volumeDataset = new TimeSeriesCollection();
    volumeSeries = new TimeSeries("Volume");
    volumeDataset.addSeries(volumeSeries);

    // Create volume chart volumeAxis
    NumberAxis volumeAxis = new NumberAxis("Volume");
    volumeAxis.setAutoRangeIncludesZero(false);

    // Set to no decimal
    volumeAxis.setNumberFormatOverride(new DecimalFormat("0"));
    
    // Create volume chart renderer
    XYBarRenderer.setDefaultBarPainter(painter);
    XYBarRenderer timeRenderer = new XYBarRenderer();
    timeRenderer.setShadowVisible(false);

    timeRenderer.setBaseToolTipGenerator(
        new StandardXYToolTipGenerator(
            "Volume--> Time={1} Size={2}", new SimpleDateFormat("MM:dd"), new DecimalFormat("0")));

    // Create volumeSubplot
    XYPlot volumeSubplot = new XYPlot(volumeDataset, null, volumeAxis, timeRenderer);
    volumeSubplot.setBackgroundPaint(Color.white);
    volumeSubplot.setRangeGridlinesVisible(true);
    volumeSubplot.setRangeGridlinePaint(Color.black);
    volumeSubplot.setDomainGridlinesVisible(true);
    volumeSubplot.setDomainGridlinePaint(Color.gray);
    ((XYBarRenderer) volumeSubplot.getRenderer()).setBarPainter(painter);

    /**
     * Create chart main plot with two subplots (candlestickSubplot, volumeSubplot) and one common
     * dateAxis
     */

    // Creating charts common dateAxis
    AlignedDateAxis dateAxis = new AlignedDateAxis(TickLabelPosition.INTERVAL_MIDDLE);
    dateAxis.setDateFormatOverride(new SimpleDateFormat("MM/dd"));
    dateAxis.setLabel("Date");

    // reduce the default left/right margin from 0.05 to 0.02
    dateAxis.setLowerMargin(0.02);
    dateAxis.setUpperMargin(0.02);

    // Create mainPlot
    CombinedDomainXYPlot mainPlot = new CombinedDomainXYPlot(dateAxis);
    mainPlot.setGap(10.0);
    mainPlot.add(candlestickSubplot, 3);
    mainPlot.add(volumeSubplot, 1);
    mainPlot.setOrientation(PlotOrientation.VERTICAL);

    JFreeChart chart = new JFreeChart(getTitle(), JFreeChart.DEFAULT_TITLE_FONT, mainPlot, true);
    chart.removeLegend();

    addCandles();

    return chart;
  }

  private void addCandles() {
    List<HistoCoin> list = service.getHistoData();
    double volume, open, high, low, close;
    FixedMillisecond time;
    Day date;

    for (HistoCoin c : list) {
      time = new FixedMillisecond(c.getTime() * 1000L);
      date = new Day(time.getStart());
      volume = c.getVolumefrom();
      open = c.getOpen();
      high = c.getHigh();
      low = c.getLow();
      close = c.getClose();

      // If currency not trading within this period
      // Omit graph result
      // if(volume == 0)
      // continue;

      ohlcSeries.add(date, open, high, low, close);
      volumeSeries.add(date, volume);
    }
  }
}
