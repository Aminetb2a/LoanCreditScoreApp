package patika.dev.definex.loancreditscore.config.openApi;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Map;
import java.util.Optional;


@Configuration
public class OpenApiOperationCustomizer implements OperationCustomizer {
    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        final io.swagger.v3.oas.models.responses.ApiResponses responses = operation.getResponses();

        for (Map.Entry<String, ApiResponse> response : responses.entrySet()) {
            if (response.getKey().startsWith("2")) {
                Optional.ofNullable(response.getValue())
                        .map(ApiResponse::getContent)
                        .ifPresent(content -> {
                            content.keySet().forEach(mediaTypeKey -> {
                                final MediaType mediaType = content.get(mediaTypeKey);
                                mediaType.schema(this.customizeSchema(mediaType.getSchema()));
                            });
                        });
            }
        }
        return operation;
    }

    private Schema<?> customizeSchema(final Schema<?> objSchema) {
        final Schema<?> wrapperSchema = new Schema<>();
        wrapperSchema.addProperties("success", new BooleanSchema()._default(true));
        wrapperSchema.addProperties("result", objSchema);
        return wrapperSchema;
    }
}
