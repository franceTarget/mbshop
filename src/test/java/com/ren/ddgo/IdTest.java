package com.ren.ddgo;

import org.junit.Test;

import com.ren.mbshop.common.utils.SnowflakeIdWorker;

public class IdTest {
	
	@Test
	public void test1(){
        String nextId = SnowflakeIdWorker.nextId();
        System.out.println(nextId);   
	}
}
