package patika.dev.definex.loancreditscore.config.openApi;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    /**
     * This method shows the application's information, description,
     * version and the owner's contact details.
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Loan Credit Score API")
                .description("Loan Credit Score API Simple Application")
                .contact(new Contact()
                        .name("Ahmed Amine T.B")
                        .email("aminetbo@gmail.com")
                        .url("https://aminetb2a.github.io/"))
                .version("0.0.1"));
    }

}
