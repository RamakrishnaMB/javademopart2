// This is the package name
package TicketPackage;

// Importing necessary libraries and annotations
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

// @SpringBootApplication is a convenience annotation that adds all of the following:
// @Configuration: Tags the class as a source of bean definitions for the application context.
// @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
// @ComponentScan: Tells Spring to look for other components, configurations, and services in the 'TicketPackage' package.
@SpringBootApplication

// @Component is a generic stereotype for any Spring-managed component.
@Component

// @SecurityScheme is an annotation used to define a security scheme that can be used by the operations.
// The parameters for this annotation are:
// - name: This is the name of the security scheme. It's an identifier and can be referenced elsewhere in the API definition.
// - scheme: This indicates the type of security scheme. In this case, it's "basic", which refers to Basic Authentication.
// - type: This indicates that the security scheme is for HTTP.
// - in: This indicates that the credentials are to be included in the Authorization header.
@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)

// This is the main class for the application
public class TicketApplication {
	// The main method, which is the entry point for the application
	public static void main(String[] args) {
		// SpringApplication.run() starts the application
		SpringApplication.run(TicketApplication.class, args);
	}
}
