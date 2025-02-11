package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class WebUsageChartManager {

	public String drawCombinedDailyUsageChartToBase64String(String chartTitle, String webUsageSubTitle, Map<Integer, AggregatedWebSessionUsageStatistics> webUsageDataMap) throws Exception {
		if( webUsageDataMap == null ) return null;
						
		boolean isChartPlotted = false;
		int chartHeight = 0;
		
		CategoryAxis categoryaxis = new CategoryAxis("Hour");        
        CombinedDomainCategoryPlot combineddomaincategoryplot = new CombinedDomainCategoryPlot(categoryaxis);
        
		CategoryDataset[] webUsageDataset = createDataset(webUsageDataMap);
		if( webUsageDataset != null ) {
			NumberAxis numberaxis = new NumberAxis("Avg. execution time (ms)");		
	        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        BarRenderer barrenderer = new BarRenderer();
	        CategoryPlot categoryplot = new CategoryPlot(webUsageDataset[1], null, numberaxis, barrenderer);
	        categoryplot.setDomainGridlinesVisible(true);
	        
	        categoryplot.setDataset(1, webUsageDataset[0]);
	        categoryplot.mapDatasetToRangeAxis(1, 1);
	        numberaxis = new NumberAxis("Sessions");
	        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        categoryplot.setRangeAxis(1, numberaxis);
	        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
	        categoryplot.setRenderer(1, lineandshaperenderer);
	        
	        CategoryTextAnnotation categorytextannotation = new CategoryTextAnnotation(webUsageSubTitle == null ? "Combined":  webUsageSubTitle, "1", 0.0D);
	        categorytextannotation.setFont(new Font("SansSerif", Font.PLAIN, 10));
	        categorytextannotation.setTextAnchor(TextAnchor.BOTTOM_LEFT);
	        categorytextannotation.setCategoryAnchor(CategoryAnchor.START);
	        categorytextannotation.setRotationAnchor(TextAnchor.BOTTOM_LEFT);
	        categorytextannotation.setRotationAngle(-1.57D);
	        categoryplot.addAnnotation(categorytextannotation);   
	        
	        combineddomaincategoryplot.add(categoryplot, 1);
	        chartHeight += 400;
	        isChartPlotted = true;
		}
		
		if( isChartPlotted ) {
	        JFreeChart jfreechart = new JFreeChart(chartTitle, new Font("SansSerif", 1, 8), combineddomaincategoryplot, true);
	        ChartUtilities.applyCurrentTheme(jfreechart);
	        
	        BufferedImage img =	new BufferedImage(1200, chartHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = img.createGraphics();
			jfreechart.draw(g2, new Rectangle2D.Double(0, 0, 1200, chartHeight));
			
			ByteArrayOutputStream byteaOutput = new ByteArrayOutputStream();
			Base64OutputStream base64Output = new Base64OutputStream(byteaOutput);
			ImageIO.write(img, "png", base64Output);
			return new String(byteaOutput.toByteArray(), "UTF-8");
		} else {
			return null;
		}
	}
	
	protected DefaultCategoryDataset[] createDataset(Map<Integer, AggregatedWebSessionUsageStatistics> dailyAggregatedData) {
		DefaultCategoryDataset defaultcategorydatasetForSession = new DefaultCategoryDataset();
		DefaultCategoryDataset defaultcategorydatasetForAvgExecTime = new DefaultCategoryDataset();
		
		boolean isPopulated = false;
		for( int i = 0; i < 24; i++ ) {
			 Integer hour = Integer.valueOf(i + 1);
			 AggregatedWebSessionUsageStatistics hourlyData = dailyAggregatedData.get(hour);
			 if( hourlyData != null ) {
				 Map<String, WebRequestUsageStatistics> requestExecutionStatistics = hourlyData.getRequestExecutionStatistics();
				 Set<String> itemSet = requestExecutionStatistics.keySet();
				 if( itemSet != null && itemSet.size() > 0 ) {
					 for (Iterator<String> j = itemSet.iterator(); j.hasNext(); ) {
						 String item = j.next();
						 WebRequestUsageStatistics rawData = requestExecutionStatistics.get(item);
						 defaultcategorydatasetForSession.addValue(rawData.getUniqueSessionCount(), item, hour.toString());
						 defaultcategorydatasetForAvgExecTime.addValue(rawData.getAvgTimeMillis(), item, hour.toString());
						 isPopulated = true;
					 }
				 } else {
					 defaultcategorydatasetForSession.addValue(0, "idle", hour.toString());
					 defaultcategorydatasetForAvgExecTime.addValue(0, "idle", hour.toString());
				 }
			 } else {
				 defaultcategorydatasetForSession.addValue(0, "idle", hour.toString());
				 defaultcategorydatasetForAvgExecTime.addValue(0, "idle", hour.toString());
			 }
			 
		}
		if( isPopulated ) {
			return new DefaultCategoryDataset[] {defaultcategorydatasetForSession, defaultcategorydatasetForAvgExecTime};
		} else {
			return null;
		}
	}
}
