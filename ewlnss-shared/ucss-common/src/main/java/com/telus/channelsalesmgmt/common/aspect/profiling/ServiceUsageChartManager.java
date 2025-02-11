/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceUsageChartManager.
 */
public class ServiceUsageChartManager {
		
	/**
	 * Prepare daily usage data.
	 *
	 * @param classSelectorSet the class selector set
	 * @param methodNameSetForServiceUsageData the method name set for service usage data
	 * @param classMethodIndexSet the class method index set
	 * @return the map
	 * @throws Exception the exception
	 */
	public Map<Integer, Map<String, Long[]>> prepareDailyUsageData(Set<String> classSelectorSet, Set<String> methodNameSetForServiceUsageData, Set<String> classMethodIndexSet, boolean skipClassNameInLegend) throws Exception {
		if( classSelectorSet == null ) return null;
		
		DailyRollingServiceUsageProfileManager dailyManager = DailyRollingServiceUsageProfileManager.Factory.getInstance();
		Map<Integer, Map<String, Map<String, Long[]>>> dailyAvgExecutionTimeMillisMap = dailyManager.getDailyAvgExecutionTimeMillisMap();
		
		Map<Integer, Map<String, Long[]>> dailyData = new TreeMap<Integer, Map<String, Long[]>>();
		for( Iterator<Integer> i = dailyAvgExecutionTimeMillisMap.keySet().iterator(); i.hasNext(); ) {
			Integer hour = i.next();
			Map<String, Map<String, Long[]>> serviceMap = dailyAvgExecutionTimeMillisMap.get(hour);
			Map<String, Long[]> hourlyData = new HashMap<String, Long[]>();
			dailyData.put(hour, hourlyData);
			for( Iterator<String> j = serviceMap.keySet().iterator(); j.hasNext(); ) {
				String serviceClassName = j.next();
				String serviceClassShortName = serviceClassName;			
				int idx = serviceClassName.lastIndexOf(".");
				if( idx != -1 ) {
					serviceClassShortName = serviceClassName.substring(idx + 1, serviceClassName.length());
				}
				if( classSelectorSet != null && classSelectorSet.contains(serviceClassName) ) {					
					Map<String, Long[]> methodMap = serviceMap.get(serviceClassName);
					for( Iterator<String> k = methodMap.keySet().iterator(); k.hasNext(); ) {
						String method = k.next();
						if( methodNameSetForServiceUsageData == null || methodNameSetForServiceUsageData.contains(method) ) {
							String classMethodIndex = skipClassNameInLegend ? method : serviceClassShortName + "." + method;
							hourlyData.put(classMethodIndex, methodMap.get(method));
							if( classMethodIndexSet != null ) {
								classMethodIndexSet.add(classMethodIndex);
							}
						}
					}
				}
			}
		}
		
		return dailyData;
	}
	
	/**
	 * Draw combined daily usage chart to base64 string.
	 *
	 * @param chartTitle the chart title
	 * @param serviceUsageSubTitle the service usage sub title
	 * @param serviceUsageDataMap the service usage data map
	 * @param classMethodIndexSet the class method index set
	 * @param serviceUsageStackedUsageSubTitle the service usage stacked usage sub title
	 * @param serviceUsageStackedDataMap the service usage stacked data map
	 * @return the string
	 * @throws Exception the exception
	 */
	public String drawCombinedDailyUsageChartToBase64String(String chartTitle, String serviceUsageSubTitle, Map<Integer, Map<String, Long[]>> serviceUsageDataMap, Set<String> classMethodIndexSet, String serviceUsageStackedUsageSubTitle, Map<Integer, Map<String, Long[]>> serviceUsageStackedDataMap) throws Exception {
		if( serviceUsageDataMap == null && serviceUsageStackedDataMap == null ) return null;
						
		boolean isChartPlotted = false;
		int chartHeight = 0;
		
		CategoryAxis categoryaxis = new CategoryAxis("Hour");        
        CombinedDomainCategoryPlot combineddomaincategoryplot = new CombinedDomainCategoryPlot(categoryaxis);
        
		if( serviceUsageDataMap != null ) {
			if( classMethodIndexSet != null && classMethodIndexSet.size() > 0 ) {
				for( Iterator<String> i = classMethodIndexSet.iterator(); i.hasNext(); ) {
					String classMethodIndex = i.next();
					CategoryDataset[] serviceUsageDataset = createDataset(serviceUsageDataMap, classMethodIndex);
					if( serviceUsageDataset != null ) {						
						NumberAxis numberaxis = new NumberAxis("Avg. execution time (ms)");		
				        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				        BarRenderer barrenderer = new BarRenderer();
				        CategoryPlot categoryplot = new CategoryPlot(serviceUsageDataset[1], null, numberaxis, barrenderer);
				        categoryplot.setDomainGridlinesVisible(true);
				        
				        categoryplot.setDataset(1, serviceUsageDataset[0]);
				        categoryplot.mapDatasetToRangeAxis(1, 1);
				        numberaxis = new NumberAxis("Invocations");
				        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				        categoryplot.setRangeAxis(1, numberaxis);
				        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
				        categoryplot.setRenderer(1, lineandshaperenderer);
				        
				        String methodName = classMethodIndex.substring(classMethodIndex.lastIndexOf(".") + 1, classMethodIndex.length());
				        CategoryTextAnnotation categorytextannotation = new CategoryTextAnnotation(methodName, "1", 0.0D);
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
				}
			} else {
				CategoryDataset[] serviceUsageDataset = createDataset(serviceUsageDataMap, null);
				if( serviceUsageDataset != null ) {
					NumberAxis numberaxis = new NumberAxis("Avg. execution time (ms)");		
			        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			        BarRenderer barrenderer = new BarRenderer();
			        CategoryPlot categoryplot = new CategoryPlot(serviceUsageDataset[1], null, numberaxis, barrenderer);
			        categoryplot.setDomainGridlinesVisible(true);
			        
			        categoryplot.setDataset(1, serviceUsageDataset[0]);
			        categoryplot.mapDatasetToRangeAxis(1, 1);
			        numberaxis = new NumberAxis("Invocations");
			        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			        categoryplot.setRangeAxis(1, numberaxis);
			        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
			        categoryplot.setRenderer(1, lineandshaperenderer);
			        
			        CategoryTextAnnotation categorytextannotation = new CategoryTextAnnotation(serviceUsageSubTitle == null ? "Combined":  serviceUsageSubTitle, "1", 0.0D);
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
			}
		}
        
		if( serviceUsageStackedDataMap != null ) {
			CategoryDataset[] serviceUsageStackedDataset = createDataset(serviceUsageStackedDataMap, null);
			if( serviceUsageStackedDataset != null ) {
		        NumberAxis stackedNumberaxis = new NumberAxis("Avg. execution time (ms)");
		        stackedNumberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		        StackedBarRenderer stackedbarrenderer = new StackedBarRenderer();
		        CategoryPlot stackedCategoryplot = new CategoryPlot(serviceUsageStackedDataset[1], null, stackedNumberaxis, stackedbarrenderer);
		        stackedCategoryplot.setDomainGridlinesVisible(true);
		        
		        CategoryTextAnnotation categorytextannotation = new CategoryTextAnnotation(serviceUsageStackedUsageSubTitle == null ? "Stacked" : serviceUsageStackedUsageSubTitle, "1", 0.0D);
		        categorytextannotation.setFont(new Font("SansSerif", Font.PLAIN, 10));
		        categorytextannotation.setTextAnchor(TextAnchor.BOTTOM_LEFT);
		        categorytextannotation.setCategoryAnchor(CategoryAnchor.START);
		        categorytextannotation.setRotationAnchor(TextAnchor.BOTTOM_LEFT);
		        categorytextannotation.setRotationAngle(-1.57D);
		        stackedCategoryplot.addAnnotation(categorytextannotation);   
		        
		        combineddomaincategoryplot.add(stackedCategoryplot, 1);
		        chartHeight += 400;
		        isChartPlotted = true;
			}
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
	
	/**
	 * Creates the dataset.
	 *
	 * @param dailyStackedData the daily stacked data
	 * @param classMethodIndex the class method index
	 * @return the default category dataset[]
	 */
	protected DefaultCategoryDataset[] createDataset(Map<Integer, Map<String, Long[]>> dailyStackedData, String classMethodIndex) {
		DefaultCategoryDataset defaultcategorydatasetForInvocation = new DefaultCategoryDataset();
		DefaultCategoryDataset defaultcategorydatasetForAvgExecTime = new DefaultCategoryDataset();
		
		boolean isPopulated = false;
		for( int i = 0; i < 24; i++ ) {
			 Integer hour = Integer.valueOf(i + 1);
			 Map<String, Long[]> hourlyData = dailyStackedData.get(hour);
			 if( hourlyData != null ) {
				 Set<String> itemSet = hourlyData.keySet();
				 if( itemSet != null && itemSet.size() > 0 ) {
					 for (Iterator<String> j = itemSet.iterator(); j.hasNext(); ) {
						 String item = j.next();
						 if( classMethodIndex == null || classMethodIndex.equalsIgnoreCase(item) ) {
							 Long[] rawData = hourlyData.get(item);
							 long invocations = rawData[0].longValue();
							 long totalExecutionTimeMillis = rawData[1].longValue();
							 long avgExecTime = invocations == 0 ? 0 : totalExecutionTimeMillis/invocations;
							 defaultcategorydatasetForInvocation.addValue(invocations, item, hour.toString());
							 defaultcategorydatasetForAvgExecTime.addValue(avgExecTime, item, hour.toString());
							 isPopulated = true;
						 }
					 }
				 } else {
					 defaultcategorydatasetForInvocation.addValue(0, "idle", hour.toString());
					 defaultcategorydatasetForAvgExecTime.addValue(0, "idle", hour.toString());
				 }
			 } else {
				 defaultcategorydatasetForInvocation.addValue(0, "idle", hour.toString());
				 defaultcategorydatasetForAvgExecTime.addValue(0, "idle", hour.toString());
			 }
			 
		}
		if( isPopulated ) {
			return new DefaultCategoryDataset[] {defaultcategorydatasetForInvocation, defaultcategorydatasetForAvgExecTime};
		} else {
			return null;
		}
	}
	
}
