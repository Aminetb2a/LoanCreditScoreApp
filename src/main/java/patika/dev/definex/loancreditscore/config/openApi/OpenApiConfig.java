package patika.dev.definex.loancreditscore.config.openApi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Loan Credit Score API API", version = "0.1",
        contact = @Contact(name = "aminetb2a", email = "aminetbo@gmail.com"))
)
public class OpenApiConfig {

//    @Bean
//    public OpenAPI springOpenAPI() {
//        return new OpenAPI().info(new Info() //
//                .title("Loan Credit Score API") //
//                .description("Loan Credit Score API Simple Application") //
//                .version("0.0.1"));
//    }

}
