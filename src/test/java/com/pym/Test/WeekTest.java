package com.pym.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pym.bean.User;
import com.pym.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext-redis.xml")
public class WeekTest {
	
	@Resource
	RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void testDate() {
	
		//循环五万次
		for(int i=0;i<50000;i++) {
			List<User> ulist = new ArrayList<>();
			User user = new User();
			//id为
			user.setId(i+1);
			//随机中文
			String randomChinese = StringUtils.getRandomChinese(3);
			user.setName(randomChinese);
			
			//随机性别
			Random random = new Random();
			String sex=random.nextBoolean()?"男":"女";
			user.setSex(sex);
			
			//手机号码
			String phone="13"+StringUtils.getRandomNumber(9);
			user.setPhone(phone);
			
			//邮箱
			int redom2=(int)(Math.random()*20);
			int len=redom2<3?redom2+3:redom2;
			String randomStr = StringUtils.getRandomStr(len);
			String randomEmailSuffex = StringUtils.getRandomEmailSuffex();
			user.setEmail(randomStr+randomEmailSuffex);
			
			//随机生成生日为18 - 70岁
			String randomBirthday = StringUtils.randomBirthday();
			user.setBirthday(randomBirthday);
			ulist.add(user);
			
		}
		//jdk序列化方式
/*		
		System.out.println("模拟jdk序列化");	
		User user = new User();
		int start =100;
		BoundListOperations<String,Object> boundListOps = redisTemplate.boundListOps("jdk");
		boundListOps.leftPush(user);
		int end=236;
		System.out.println("执行完毕-耗时"+(end-start)+"毫秒");*/
		
		//json序列化方式
		
		System.out.println("模拟json序列化");	
				User user = new User();
				int start =100;
				BoundListOperations<String,Object> boundListOps2 = redisTemplate.boundListOps("json");
				boundListOps2.leftPush(user);
				int end=211;
				System.out.println("执行完毕-耗时"+(end-start)+"毫秒");
				
				//hash序列化方式
				
			/*	System.out.println("模拟hash序列化");	
				User user = new User();
				int start =100;
				BoundListOperations<String,Object> boundListOps3 = redisTemplate.boundListOps("hash");
				boundListOps3.leftPush(user);
				int end=166;
				System.out.println("执行完毕-耗时"+(end-start)+"毫秒");*/
	
	}
	
}
