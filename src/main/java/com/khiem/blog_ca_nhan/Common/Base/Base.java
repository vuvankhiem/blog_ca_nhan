package com.khiem.blog_ca_nhan.Common.Base;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Base {

     //get current date
     public String getDate(){
          Date date = new Date();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
          String dateFormat = simpleDateFormat.format(date);
          return dateFormat;
     }
     //Random code
     public String randomCode(int count){
          return RandomStringUtils.randomAlphanumeric(count);
     }
}
