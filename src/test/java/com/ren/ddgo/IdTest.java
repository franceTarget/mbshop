package com.ren.ddgo;

import com.alibaba.fastjson.JSONObject;
import com.ren.mbshop.common.utils.SnowflakeIdWorker;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class IdTest {

    @Test
    public void test1() {
        String nextId = SnowflakeIdWorker.nextId();
        System.out.println(nextId);
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "失败");
        String s = JSONObject.toJSONString(map);
        System.out.println(s);
    }

    @Test
    public void test3() {
        Calendar now = Calendar.getInstance();
        System.out.println(now.get(Calendar.YEAR));
        System.out.println(now.get(Calendar.MONTH) + 1);
        System.out.println(now.get(Calendar.DAY_OF_MONTH));
    }
}
