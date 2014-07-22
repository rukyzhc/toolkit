package net.zhanghc.toolkit.net.core;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import net.zhanghc.toolkit.net.data.Url;

public class TestDownloader {

	public static void main(String[] args) throws Exception {
		Downloader downloader = new Downloader();
		Header[] headers = new Header[5];
		headers[0] = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers[1] = new BasicHeader("Referer", "http://bj.meituan.com/category/new/all/page1");
		headers[2] = new BasicHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6");
		headers[3] = new BasicHeader("Cookie","rvct=1; _ga=GA1.2.320870018.1388710793; als=1; rus=1; iuuid=76A3706DA0FDB615F0919076F3E17F89F57B524A3F2C45C3A0922C4FAAAA56BA; ci=1; abt=1401430351.1401465600%7CACE; ttgr=231136; SID=kvr8fs4vrkrknth2neq21dc3j7; rvd=4433680%2C24992693%2C1368%2C12612464%2C25145876; __mta=_utma%3D211559370.320870018.1401453105757.1401453108439.1401088872.12; __utma=211559370.320870018.1388710793.1401430350.1401452147.4; __utmb=211559370.7.9.1401452161817; __utmc=211559370; __utmz=211559370.1401357933.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=211559370.|2=usertype=1=1^3=dealtype=12=1^5=cate=new=1; __t=1401452161830.5.1401453108577.C3.Efloor.Bmeishi.Anone; uuid=91846b566a79351e8808.1388710812.3.0.1");
		headers[4] = new BasicHeader("If-Modified-Since","Fri, 30 May 2014 11:29:26 GMT");
		downloader.fetch(new Url("http://www.baidu.com"), headers);
	}

}
