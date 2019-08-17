package com.ren.mbshop;

import org.junit.Test;

import java.io.File;

public class FileTest {

    @Test
    public void test1(){
        File file = new File("http://localhost:8800/test.sql");
        String name = file.getName();
        System.out.println(name);
    }

}
