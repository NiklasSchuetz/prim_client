package com.vs_prim.consumer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import java.util.Scanner;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);



	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();

		//STRING
		String res_string = restTemplate.getForObject("http://localhost:8080/string?nr=6", String.class);
		System.out.println("Das ergebnis als string ist: "+res_string);

		//Array
        String json_str = restTemplate.getForObject("http://localhost:8080/array?nr=6", String.class);

        System.out.println(json_str);
        JSONObject json_obj = new JSONObject(json_str);

        JSONArray json_arr = json_obj.optJSONArray("1");

        System.out.println(json_arr);
        //System.out.println(json_arr.toString());
        //for (int i = 0; i < json_arr.length(); i++) {
        //    int val = (int)(json_arr.get(i));
        //    System.out.println("als arraywerte: "+ i +" "+val);
        //}

		//Object
		//Primzahlen res_prim = restTemplate.getForObject("http://localhost:8080/struct?nr=6", Primzahlen.class);
        //System.out.print(res_prim.prim_string);



        //Scanner input = new Scanner(System.in);
		//System.out.println("Bis zu welcher oberen Grenze wollen sie die Primzahlen berechnen?");
		//int nr = input.nextInt();
		//System.out.println("Obere Grenze: "+nr+". Wählen sie: Array(1), ein String(2) oder eine Kombination(3)");
		//int wahl = input.nextInt();
	}











	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}


}
