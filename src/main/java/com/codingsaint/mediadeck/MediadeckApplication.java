package com.codingsaint.mediadeck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableScheduling
public class MediadeckApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediadeckApplication.class, args);
	}

}


@Controller
class RouteController {
	@RequestMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}
}
