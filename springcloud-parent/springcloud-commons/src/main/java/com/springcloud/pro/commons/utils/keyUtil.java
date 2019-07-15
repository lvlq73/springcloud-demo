package com.springcloud.pro.commons.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class keyUtil {

    /**
     * 生产ItemName随机函数
     * @param length
     * @return
     */
    public static String createkey( int length ){
        String base = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i < length; i++ )
        {
            int number = random.nextInt( base.length() );
            sb.append( base.charAt( number ) );
        }
        return sb.toString();
    }

    public static String createUuid(){
        String uuid=  UUID.randomUUID().toString().replace("-","");
        return uuid.toString();
    }

    /**
     * 创建大于17位的时间随机书
     * @param size
     * @return
     */
    public static String createTimeuuid(int size){
        String timeId=  DateUtil.dateToString(new Date(),DateUtil.YYYYMMDDHHSS_SSS );
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i < size-17; i++ )
        {
            int number = random.nextInt( 9 );
            sb.append( number );
        }
        return timeId+sb.toString();
    }
    public  static String CreateCode(int size){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for ( int i = 0; i < size; i++ )
        {
            int number = random.nextInt( 9 );
            sb.append( number );
        }
        return  sb.toString();
    }

    public static void main(String[] args){
        Date date=new Date();
        System.out.println(date.getTime());
    }
}
