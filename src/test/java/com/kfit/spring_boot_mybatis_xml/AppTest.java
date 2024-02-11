package com.kfit.spring_boot_mybatis_xml;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        getDate();
        int x = getOrderNo();
      //  ExportExcelUtils.batchSnycTestceshi();
    }

    private int getOrderNo()
    {
        Random random = new Random();
        return random.nextInt(500000000) + 400000000;
    }

    private void getDate()
    {
        Date currentTime = new Date(); // 获取当前时间
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd"); // 定义日期格式
        SimpleDateFormat t = new SimpleDateFormat("hh:mm:ss"); // 定义日期格式
        String formattedDateTime = t.format(currentTime);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        //assertTrue( true );
        getDate();
    }
}
