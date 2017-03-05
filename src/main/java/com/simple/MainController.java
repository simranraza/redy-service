/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simple;

/**
 *
 * @author zion
 */
//import java.util.concurrent.atomic.AtomicLong;
import java.io.IOException;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/Fibonacci")
    public String fibonacci(@RequestParam(defaultValue = "5") Integer n) {
        Integer m;

        if (n == 0) {
            return Integer.toString(0);
        }
        m = fib(Math.abs(n));
        return Integer.toString(m);
    }

    private Integer fib(Integer number) {
        if (number == 1 || number == 2) {
            return 1;
        }

        return fib(number - 1) + fib(number - 2);

    }
    @RequestMapping("/ReverseWords")
    public String reverseWords (@RequestParam String sentence){
        if (sentence.isEmpty())
            return "";
        String [] temp = sentence.split(" ");
        StringBuilder sb =  new StringBuilder();
        for(String t:temp)
        {
            sb.append(new StringBuilder(t).reverse());
            sb.append(" ");
        }
        return sb.toString();
    }
    
    @RequestMapping("/Token")
    public String token (){
        //00000000-0000-0000-0000-000000000000//
        //8-4-4-4-12//
        StringBuilder sb =  new StringBuilder();
        Random r = new Random();
        long  l = 0;
        
        l = getToken (8);
        sb.append(l);
        sb.append("-");
        
        l = getToken (4);
        sb.append(l);
        sb.append("-");
        
        l = getToken (4);
        sb.append(l);
        sb.append("-");
        
        l = getToken (12);
        sb.append(l);
        
        return sb.toString();
    }
    
    @RequestMapping("/TriangleType")
    public String triangleType(@RequestParam int a, @RequestParam int b, @RequestParam int c)
    {
        String tType="";
        if(a <=0 || b<=0 || c <=0)
            return "Error";
        if (a == b && b==c)
            tType = "Equilateral";
        else if (a==b || b==c || a==c)
            tType = "Isosceles";
        else
            tType = "Scalene";
        
        return tType;
    }
    private long getToken (int len){
        Random r = new Random();
        long val1 = 0;
        long val2 = 0;
        
        switch(len){
            case 4:
                val1 = 1000;
                val2 = 9000;
                val2 = r.nextInt((int)val2);
                break;
            case 5:
                val1 = 10000;
                val2 = 90000;
                val2 = r.nextInt((int)val2);
                break;
            case 8:
                val1 = 10000000;
                val2 = 90000000;
                val2 = r.nextInt((int)val2);
                break;
            case 12:
                val1 = 100000000000L;
                //val1 = 0;
                val2 = 900000000;
                val2 = r.nextInt((int)val2);
                break;
            default:
                val1 = 100;
                val2 = 900;
        }
        long  l = val1 + val2;
        return l;
    }

    @ExceptionHandler(NumberFormatException.class)
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.OK.value(), "The request is invalid.");
    }
}
