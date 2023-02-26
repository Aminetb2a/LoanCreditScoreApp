package patika.dev.definex.loancreditscore.service.user.util;

import org.springframework.stereotype.Component;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;

@Component
public class SmsGenerator {
    /**
     * Method to handle the content of the sms to be send to loan application users
     *
     * @param user usr
     * @return Sms content
     */

    public String generateSms(UserDTO user) {
        return String.format("Thank you for applying to our loan Mr(s) %s.\n\nThe result of you application is %s with a limit of %s.\nPlease contact our customer services for more details.\n\nThank you!",
                user.getSurname(), user.getLoanStatus().toString().toLowerCase(), user.getCreditLimit());
    }
}
