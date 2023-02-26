package patika.dev.definex.loancreditscore.service.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.config.prop.TwilioProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final TwilioProperties twilioProperties;

    /**
     * Method to send sms using Twilio
     *
     * @param phoneNumber user's phone number
     * @param content     sms' content
     * @return Message Twilio's response
     */
    @Override
    public Message sendSms(String phoneNumber, String content) {
        login();
        Message message = null;
        try {
            message = Message.creator(new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioProperties.getPhoneNumber()),
                    content).create();
        } catch (Exception e) {
            log.error("Twilio exception: " + e);
        } finally {
            log.debug("Proceed with flow...");
        }
        return message;
    }

    /**
     * Method for login to Twilio
     */
    private void login() {
        Twilio.init(twilioProperties.getUsername(), twilioProperties.getPassword());
    }
}
