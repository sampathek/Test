import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ogn.cache.OgnCacheManager;
import ogn.cache.engines.EngineType;
import ogn.cache.engines.MemCachedEngine;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;













import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class Test {

	private final static Logger LOGGER = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws Exception {
		
		DOMConfigurator.configure("log4j.xml");

		Test t1 = new Test(); 
		//t1.testXMemcached();
		t1.testOgnCacheManager();
		//t1.getRandomSeqInt();

		t1.regExpTest();
		
		/*System.out.println(new SimpleDateFormat("yyyy-MM-dd").parseObject("2014-02-13"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(formatter.parse("2014/03/13"));
		System.exit(0);*/
		
		//t1.nioTest();
		
		//t1.getGenericClass();
		
		/*Calendar c1 = Calendar.getInstance();
		c1.set(2014, 04, 29);
		
		Calendar c2 = Calendar.getInstance();
		c2.set(2014, 05, 01);
		
		System.out.println(t1.getDateDiff(c2.getTime(), c1.getTime()));*/
		
		//System.out.println(Math.abs(2));
		
		
		//t1.breakArray();
		/*BigDecimal x = new BigDecimal(65757657);
		BigDecimal y = new BigDecimal(0.000544);
		System.out.println( x.divide(y,4,RoundingMode.HALF_UP));
		System.out.println( x.divide(y,3));
		System.out.println( x.divide(y,RoundingMode.HALF_UP));
		System.out.println( x.divide(y));*/
		
		/*
		int x=010;
		System.out.println(x); // you get 9
		*/		
		
		
		//System.exit(0);
	}
	
	private int getRandomSeqInt() throws Exception{
		System.out.println(new Random().nextInt(100000));
		System.out.println(Math.abs(Calendar.getInstance().getTimeInMillis()));
		Thread.sleep(200);
		System.out.println(Calendar.getInstance().getTimeInMillis());
		
		String curTime = Calendar.getInstance().getTimeInMillis()+"";
		curTime = curTime.substring(curTime.length()-9);
		System.out.println(curTime);
		
		return Integer.parseInt(curTime);
		
	}

	private void breakArray() {
		Object[] objectArr = {"AA","BB","CC","DD","EE","FF","GG","HH","II","JJ"};
		try {
			List<Object> currencyWiseList = new ArrayList<>();
			List<Object> lineArray = new ArrayList<>();
			for (int x=0; x<objectArr.length; x++) {
				
				lineArray.add(objectArr[x]);
				if((x+1)%5==0){
					currencyWiseList.add(lineArray);
					lineArray = new ArrayList<>();
				}
			}
			System.out.println(currencyWiseList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getDateDiff(Date startDate, Date endDate){
		Calendar sDate = Calendar.getInstance();
		Calendar eDate = Calendar.getInstance();
		sDate.setTime(startDate);
		eDate.setTime(endDate);
		int difInDays = eDate.get(Calendar.DATE) - sDate.get(Calendar.DATE);
		
		
		String dateStart = "05/01/2014";
		String dateStop = "04/29/2014";
 
		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
 
		Date d1 = null;
		Date d2 = null;
 
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
 
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
 
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
 
			System.out.print(Math.abs(diffDays) + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return difInDays;
	}
	
	private void getGenericClass(){
		ArrayList<Integer> arrayList = new ArrayList<>();
		System.out.println(arrayList.getClass());
	}
	
	private void nioTest(){
		try {
			/*Path file = Paths.get("/var/log/availabilityResult.xml");
			Files.createFile(file);
			System.out.println(file.toFile().exists());*/
			
			String content = "This is the content to write into file";
			 
			File file = new File("/var/log/availabilityResult.xml");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter  fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void regExpTest(){
		String regExp = "^(.*)/(.*).city$";
		String test="http://dev.hotelbama.com/usa/new-york-ny.city";
		System.out.println("result="+test.matches(regExp));
	}

	private void testOgnCacheManager() {
		
		try {
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("1", "ONE");
			hashMap.put("2", "TWO");
			hashMap.put("3", "THREE");
			
			EngineType cacheManager =  OgnCacheManager.getCachEngine(OgnCacheManager.MEMCACHED);
			//cacheManager.setObject("key4", hashMap);
			System.out.println(cacheManager.getObject("CacheControl_en~3026~0~-2~20-Jun-2014~2~22-Jun-2014~1~"));
			//hashMap.put("4", "FOUR");
			//cacheManager.setObject("key4", hashMap);
			//System.out.println(cacheManager.getObject("key4"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	void testXMemcached() throws IOException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("localhost:11211"));
		MemcachedClient memcachedClient = builder.build();
		try {
			memcachedClient.set("key1", 0, "Hello Xmemcached");
			
			String value = memcachedClient.get("key1");
			System.out.println("key1=" + value);
			
			memcachedClient.delete("key1");
			
			value = memcachedClient.get("key1");
			System.out.println("key1=" + value);
			
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore
		}
		try {
			// close memcached client
			memcachedClient.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}
	}
	

}
