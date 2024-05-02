package com.scaler.productservicemorningbatch;

import org.springframework.web.bind.annotation.*;

//This controller supports srest ApI's(Http request)
//The request comming to end point/hello, transfer them to this controller.
@RestController
@RequestMapping("/hello")
public class SampleController {
    @GetMapping("/{name}/{number}")
    public String sayHello(@PathVariable("name") String name,@PathVariable("number") int number){
       StringBuilder stringBuilder=new StringBuilder();
       for(int i=0;i<number;i++){
           stringBuilder.append("Hello "+name+"\n");
       }
       return stringBuilder.toString();
    }

}
