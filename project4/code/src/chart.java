import java.awt.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;

//https://www.tutorialspoint.com/jfreechart/jfreechart_quick_guide.htm
public class chart extends ApplicationFrame {
    FNMS sim;

    public chart( String applicationTitle, String chartTitle, FNMS temp1, boolean chart) {
        super(applicationTitle);
        sim=temp1;

        JFrame frame = new JFrame("Many charts same frame");
        frame.setLayout( new FlowLayout() );
        //chart is a boolean used to pick which chart we want generated
        //if chart is true, create chart for inventory, daily items damaged, and items sold
        if (chart) {
            JFreeChart linear1 = ChartFactory.createXYLineChart(
                    chartTitle ,
                    "days" ,
                    "count" ,
                    createDataset(0) ,
                    PlotOrientation.VERTICAL ,
                    true , true , false);

            ChartPanel chartPanel = new ChartPanel( linear1 );
            chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
            final XYPlot plot = linear1.getXYPlot( );

            //uncomment for series 2 if going for 2nd graph
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , Color.RED );
            renderer.setSeriesPaint( 1 , Color.GREEN );
            //renderer.setSeriesPaint( 2 , Color.YELLOW );

            renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            //renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
            plot.setRenderer( renderer );
            setContentPane( chartPanel );
        //if chart is false, create chart for itemSales and Register
        } else {
            JFreeChart linear1 = ChartFactory.createXYLineChart(
                    chartTitle ,
                    "days" ,
                    "count" ,
                    createDataset(1) ,
                    PlotOrientation.VERTICAL ,
                    true , true , false);

            ChartPanel chartPanel = new ChartPanel( linear1 );
            chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
            final XYPlot plot = linear1.getXYPlot( );

            //uncomment for series 2 if going for 2nd graph
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , Color.RED );
            renderer.setSeriesPaint( 1 , Color.GREEN );
            renderer.setSeriesPaint( 2 , Color.YELLOW );

            renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
            plot.setRenderer( renderer );
            setContentPane( chartPanel );
        }
        /*
        JFreeChart linear1 = ChartFactory.createXYLineChart(
                chartTitle ,
                "days" ,
                "$" ,
                createDataset(1) ,
                PlotOrientation.VERTICAL ,
                true , true , false);

         */
        /*
        JFreeChart linear1 = ChartFactory.createXYLineChart(
                chartTitle ,
                "days" ,
                "count" ,
                createDataset(0) ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( linear1 );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = linear1.getXYPlot( );

        //uncomment for series 2 if going for 2nd graph
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
        //renderer.setSeriesPaint( 2 , Color.YELLOW );

        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
        //renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
        */
    }

    //depending on the integer d inputted, generate the chart that we want
    private XYDataset createDataset(int d) {
        final XYSeries itemSales = new XYSeries( "itemSales" );
        for(int i=0; i<sim.day_itemSales.size(); i++){
            itemSales.add(i,sim.day_itemSales.get(i));
        }
        final XYSeries register = new XYSeries( "totalRegister" );
        for(int i=0; i<sim.day_totalRegister.size(); i++){
            register.add(i,sim.day_totalRegister.get(i));
        }
        final XYSeries inventory = new XYSeries( "inventory" );
        for(int i=0; i<sim.day_inventoryCount.size(); i++){
            inventory.add(i,sim.day_inventoryCount.get(i));
        }
        final XYSeries day_dmg = new XYSeries( "dmg" );
        for(int i=0; i<sim.day_damagedItems.size(); i++){
            day_dmg.add(i,sim.day_damagedItems.get(i));
        }
        final XYSeries sold = new XYSeries( "sold" );
        for(int i=0; i<sim.day_itemSold.size(); i++){
            sold.add(i,sim.day_itemSold.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection( );
        //if chart is false, and therefore d = 1, then add the itemSales and register Series
        if(d==1) {
            dataset.addSeries(itemSales);
            dataset.addSeries(register);
        }
        //else chart is true, and therefore d = 0, then add the inventory, day_dmg, and sold Series
        else{
            dataset.addSeries(inventory);
            dataset.addSeries(day_dmg);
            dataset.addSeries(sold);
        }
        return dataset;
        /*
        if(d==1){
            XYSeriesCollection dataset = new XYSeriesCollection( );
            dataset.addSeries( itemSales );
            dataset.addSeries( register );
            return dataset;
        }
        else{
            XYSeriesCollection dataset = new XYSeriesCollection( );
            dataset.addSeries( inventory );
            dataset.addSeries( day_dmg );
            dataset.addSeries( sold );
            return dataset;
        }

         */
    }


}