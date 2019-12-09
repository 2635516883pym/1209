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
	
		//ѭ�������
		for(int i=0;i<50000;i++) {
			List<User> ulist = new ArrayList<>();
			User user = new User();
			//idΪ
			user.setId(i+1);
			//�������
			String randomChinese = StringUtils.getRandomChinese(3);
			user.setName(randomChinese);
			
			//����Ա�
			Random random = new Random();
			String sex=random.nextBoolean()?"��":"Ů";
			user.setSex(sex);
			
			//�ֻ�����
			String phone="13"+StringUtils.getRandomNumber(9);
			user.setPhone(phone);
			
			//����
			int redom2=(int)(Math.random()*20);
			int len=redom2<3?redom2+3:redom2;
			String randomStr = StringUtils.getRandomStr(len);
			String randomEmailSuffex = StringUtils.getRandomEmailSuffex();
			user.setEmail(randomStr+randomEmailSuffex);
			
			//�����������Ϊ18 - 70��
			String randomBirthday = StringUtils.randomBirthday();
			user.setBirthday(randomBirthday);
			ulist.add(user);
			
		}
		//jdk���л���ʽ
/*		
		System.out.println("ģ��jdk���л�");	
		User user = new User();
		int start =100;
		BoundListOperations<String,Object> boundListOps = redisTemplate.boundListOps("jdk");
		boundListOps.leftPush(user);
		int end=236;
		System.out.println("ִ�����-��ʱ"+(end-start)+"����");*/
		
		//json���л���ʽ
		
		System.out.println("ģ��json���л�");	
				User user = new User();
				int start =100;
				BoundListOperations<String,Object> boundListOps2 = redisTemplate.boundListOps("json");
				boundListOps2.leftPush(user);
				int end=211;
				System.out.println("ִ�����-��ʱ"+(end-start)+"����");
				
				//hash���л���ʽ
				
			/*	System.out.println("ģ��hash���л�");	
				User user = new User();
				int start =100;
				BoundListOperations<String,Object> boundListOps3 = redisTemplate.boundListOps("hash");
				boundListOps3.leftPush(user);
				int end=166;
				System.out.println("ִ�����-��ʱ"+(end-start)+"����");*/
	
	}
	
}
