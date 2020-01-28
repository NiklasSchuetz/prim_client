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


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);



	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();
		String base_url = "http://localhost:8080/";

		//Dialog
		Scanner input = new Scanner(System.in);


		while (true) {
			System.out.println("\nBis zu welcher oberen Grenze wollen sie die Primzahlen berechnen?");
			int nr = input.nextInt();
			System.out.println("\nWählen sie: Array(1), ein String(2), eine Kombination(3) oder beenden(4) ");
			int wahl = input.nextInt();

			if (wahl == 1) {
				//Array
				String json_str = restTemplate.getForObject(base_url+"array?nr="+nr, String.class);
				JSONObject json_obj = new JSONObject(json_str);
				JSONArray json_arr = json_obj.optJSONArray("array");
				int[] int_arr = new int[json_arr.length()];
				for (int i = 0; i < json_arr.length(); ++i) {
					int_arr[i] = json_arr.optInt(i);
				}
				System.out.println("Das Ergebnis als Array: ");
				for (int i=0; i<int_arr.length; i++) {
					System.out.print(int_arr[i]+" ");
				}


			} else if (wahl == 2) {
				//STRING
				String res_string = restTemplate.getForObject(base_url+"string?nr="+nr, String.class);
				System.out.println("Das ergebnis als string ist: " + res_string);

			} else if (wahl == 3) {
				//Object
				String json_str = restTemplate.getForObject(base_url+"struct?nr="+nr, String.class);
				JSONObject json_obj = new JSONObject(json_str);
				List<Integer> list = new ArrayList<>();
				JSONArray json_arr = json_obj.optJSONArray("array");

				for (int i = 0; i < json_arr.length(); ++i) {
					list.add(json_arr.optInt(i));
				}


				Primzahlen prim = new Primzahlen(list, json_obj.optString("string").
						                               replace("[", "").
						                               replace("]", " "));

				System.out.println(prim.toString());


			} else { break; }
		}

	}











	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}


}
