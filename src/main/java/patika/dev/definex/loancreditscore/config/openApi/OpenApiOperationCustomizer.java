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
    /**
     * For each response that starts with a 2 (isSuccess()), get the customized schema
     * that wraps the available schema in the result object, and add success field to it
     * to match the BaseResponse Object
     *
     * @param operation     The operation to customize.
     * @param handlerMethod The method that is being documented.
     * @return The operation is being returned.
     */
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

    /**
     * Method that takes a schema and returns a new schema that wraps the original schema in a new object with
     * two properties: `success` and `result`
     *
     * @param objSchema The schema of the object that you want to wrap.
     * @return A schema object that contains the schema of the object being returned.
     */
    private Schema<?> customizeSchema(final Schema<?> objSchema) {
        final Schema<?> wrapperSchema = new Schema<>();
        wrapperSchema.addProperty("success", new BooleanSchema()._default(true));
        wrapperSchema.addProperty("result", objSchema);
        return wrapperSchema;
    }
}
