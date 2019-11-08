package com.example.demo;

import com.example.demo.hello.HelloSender1;
import com.example.demo.iptest.consultmessagemapper.ConsultMessageMapper;
import com.example.demo.iptest.entity.ConsultIPRegion;
import com.example.demo.iptest.entity.ConsultMessage;
import com.example.demo.iptest.mapper.ConsultIPRegionMapper;
import com.example.demo.iptest.service.IpRegionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	HelloSender1 helloSender1;
	@Autowired
	IpRegionService ipRegionService;
	@Autowired
	ConsultMessageMapper consultMessageMapper;

	@Autowired
	ConsultIPRegionMapper consultIPRegionMapper;



	@Test
	public void contextLoads() {
		helloSender1.send("ddd");

	}

	public static void main(String[] args) {
		Stream.iterate(1,n->n+2).limit(10).forEach(System.out::println);
		System.out.println("************************");
		Random random = new Random(100);
		Stream.generate(()->random.nextInt(100)).limit(10).forEach(System.out::println);
		System.out.println(Math.ceil(1.2));
	}



	@Test
	public void testq() throws ParseException, InterruptedException {
		ipRegionService.getIPRegionList();
	}

	@Test
	public void mapperTest (){
		//List<ConsultMessage> list = consultMessageMapper.listByPrimaryKey("15416773708468536751");
        List<ConsultMessage> list = new ArrayList<>();
        list.add(new ConsultMessage("15540498660483577708","1888","广州"));
      //  list.add(new ConsultMessage("15540367985618978205","1888","成都"));
		consultIPRegionMapper.updateList(list.get(0));
	}

	@Test
	public void phoneTest(){
		//ipRegionService.getTelCity("19115156220");
		ipRegionService.getTelCity("18682790711");
	}
}
