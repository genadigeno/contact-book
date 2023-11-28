package dga.contact.book;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.List;
@EnableCaching
@SpringBootApplication
public class DgaTestAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DgaTestAssessmentApplication.class, args);
	}

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:9898");
		devServer.setDescription("Server URL in Development environment");

		Contact contact = new Contact();
		contact.setEmail("genadimumladze44@gmail.com");
		contact.setName("Genadi Mumladze");
		contact.setUrl("https://www.linkedin.com/in/genadi-mumladze-4aa492170/");

		License mitLicense = new License()
				.name("MIT License")
				.url("https://choosealicense.com/licenses/mit/");

		Info info = new Info()
				.title("Digital Governance Agency - Contact Book")
				.version("1.0")
				.contact(contact)
				.description("This API exposes endpoints to manage tutorials.")
				.termsOfService("")
				.license(mitLicense);

		return new OpenAPI()
				.security(List.of(new SecurityRequirement().addList("bearerAuth")))
				.info(info)
				.servers(List.of(devServer));
	}

}
