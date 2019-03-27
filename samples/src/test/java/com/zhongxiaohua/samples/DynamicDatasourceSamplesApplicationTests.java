package com.zhongxiaohua.samples;

import com.alibaba.fastjson.JSON;
import com.zhongxiaohua.samples.domain.po.Demo;
import com.zhongxiaohua.samples.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDatasourceSamplesApplication.class)
public class DynamicDatasourceSamplesApplicationTests {
	@Autowired
	private DemoService demoService;

	@Test
	public void insertTest() {
		Demo demo = new Demo();
		demo.setName("测试");
		demoService.insert(demo);
	}

	@Test
	public void listByAllTest() {
		System.out.println(JSON.toJSONString(demoService.listByAll()));
	}

	@Test
	public void insertAndSelectTest() {
		Demo demo = new Demo();
		demo.setName("测试a");
		System.out.println(JSON.toJSONString(demoService.insertAndSelect(demo)));
	}
}
