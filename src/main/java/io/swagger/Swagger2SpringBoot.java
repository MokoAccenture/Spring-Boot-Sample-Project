package io.swagger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "io.swagger")
@RestController
@EnableAutoConfiguration
public class Swagger2SpringBoot implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		if (arg0.length > 0 && arg0[0].equals("exitcode")) {
			throw new ExitException();
		}
	}
	@RequestMapping(value="/h", method=RequestMethod.GET)
	public String sayHello()
	{
		return "Welcome Moko";
	}
	
	@RequestMapping(value="/param/{name}", method=RequestMethod.GET)
	public String paramSample(@PathVariable("name") String str)
	{
		return "Hello " + str;
	}
	
	@RequestMapping(value="/notFound", method=RequestMethod.GET)
	public ResponseEntity<Void> notFoundSample()
	{
		 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	public static void main(String[] args) throws Exception {

		new SpringApplication(Swagger2SpringBoot.class).run(args);
	}

	class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}

	}
}
