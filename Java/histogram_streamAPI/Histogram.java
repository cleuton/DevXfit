/*
Copyright 2018 Cleuton Sampaio

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.obomprogramador.stream;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Stream API demo.
 * @author Cleuton Sampaio. 
 */
public class Histogram {

	public static void main(String[] args) {
		/*
		 * Ok, this implementation is not fully functional, as it uses "side effect" methods, and
		 * changes the internal state (variable "map"). I'm using concrete class "TreeMap", which
		 * offers ordering and the method "floorKey()".
		 */
		TreeMap<Double, Integer> map = new TreeMap<Double, Integer>(Stream.of(new Object[][] { 
		    {0.0, 0},{2.0, 0},{4.0, 0},{6.0, 0},{8.0, 0}
		}).collect(Collectors.toMap(data -> (Double) data[0], data -> (Integer) data[1])));
		List<Double> averages = (new Random(42)).doubles(100, 0, 1)
				.map(n -> BigDecimal.valueOf(n*10).setScale(2, RoundingMode.HALF_UP).doubleValue())
				.boxed().collect(Collectors.toList());
		System.out.println(averages);
		averages.forEach(average -> {
			Double key = map.floorKey(average);
			map.put(key, map.get(key) + 1);
		});
		System.out.println(map);
		
		
		/*
		 * You can use JavaFX classes instead of Swing and AWT.
		 */
		JFrame f = new JFrame();
		f.setSize(400, 300);
		f.getContentPane().add(new ChartPanel(
				map.values().stream().mapToInt(x -> x).toArray(),
				map.keySet().stream().mapToDouble(key -> key).toArray(),
				"Average Histogram"
				));

	    WindowListener wndCloser = (WindowListener) new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    };
	    f.addWindowListener(wndCloser);
	    f.setVisible(true);		
		
	}

}

/*
 * This is a class from http://www.java2s.com/Code/Java/2D-Graphics-GUI/Simplebarchart.htm
 * Just to plot the Histogram. You can use JavaFX or other components. 
 */
class ChartPanel extends JPanel {
	  private int [] values;

	  private double[] names;

	  private String title;

	  public ChartPanel(int [] v, double[] n, String t) {
	    names = n;
	    values = v;
	    title = t;
	  }

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (values == null || values.length == 0)
	      return;
	    double minValue = 0;
	    double maxValue = 0;
	    for (int i = 0; i < values.length; i++) {
	      if (minValue > values[i])
	        minValue = values[i];
	      if (maxValue < values[i])
	        maxValue = values[i];
	    }

	    Dimension d = getSize();
	    int clientWidth = d.width;
	    int clientHeight = d.height;
	    int barWidth = clientWidth / values.length;

	    Font titleFont = new Font("SansSerif", Font.BOLD, 20);
	    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
	    Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
	    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

	    int titleWidth = titleFontMetrics.stringWidth(title);
	    int y = titleFontMetrics.getAscent();
	    int x = (clientWidth - titleWidth) / 2;
	    g.setFont(titleFont);
	    g.drawString(title, x, y);

	    int top = titleFontMetrics.getHeight();
	    int bottom = labelFontMetrics.getHeight();
	    if (maxValue == minValue)
	      return;
	    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
	    y = clientHeight - labelFontMetrics.getDescent();
	    g.setFont(labelFont);

	    for (int i = 0; i < values.length; i++) {
	      int valueX = i * barWidth + 1;
	      int valueY = top;
	      int height = (int) (values[i] * scale);
	      if (values[i] >= 0)
	        valueY += (int) ((maxValue - values[i]) * scale);
	      else {
	        valueY += (int) (maxValue * scale);
	        height = -height;
	      }

	      g.setColor(Color.red);
	      g.fillRect(valueX, valueY, barWidth - 2, height);
	      g.setColor(Color.black);
	      g.drawRect(valueX, valueY, barWidth - 2, height);
	      int labelWidth = labelFontMetrics.stringWidth(Double.toString(names[i]));
	      x = i * barWidth + (barWidth - labelWidth) / 2;
	      g.drawString(Double.toString(names[i]), x, y);
	    }
	  }
}
