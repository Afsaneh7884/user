package com.ecommerce.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {

		//SpringApplication.run(UserApplication.class, args);
		new SpringApplicationBuilder(UserApplication.class)
				.properties("server.port=8082")
				.run(args);

	}
}
//https://www.reebok.com/products/reebok-club-c-85-shoes-white-green-100050?op1=White%20/%20Royal%20/%20Gum&op2=M%203.5%20/%20W%205&op3=
//https://www.pacsun.com/reebok/off-white-club-c-85-vintage-shoes-0530491370016.html?dwvar_0530491370016_color=619&tileCgid=
