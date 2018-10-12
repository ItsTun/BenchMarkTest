package com.example.tunhanmyae.benchmarktest.Core;

import com.example.tunhanmyae.benchmarktest.common.Common;

import java.util.Random;

public class SpeedTest {


    public static double UploadSpeed()
    {
        double speed = (double) (Common.min + Math.random() * (Common.max - Common.min));
        return  speed;



    }

    public static double DownloadSpeed()
    {
        double speed = (double) (Common.min + Math.random() * (Common.max - Common.min));
        return  speed;

    }

    public static double Ping()
    {
        double speed = (double) (Common.min + Math.random() * (Common.max - Common.min));
        return  speed;

    }



}
