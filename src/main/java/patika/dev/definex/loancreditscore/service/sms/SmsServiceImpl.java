package patika.dev.definex.loancreditscore.service.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.config.prop.TwilioProperties;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final TwilioProperties twilioProperties;

    @Override
    public Message sendSms(String phoneNumber, String content) {
        login();
        Message message = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioProperties.getPhoneNumber()),
                content).create();
        return message;
    }

    private void login() {
        Twilio.init(twilioProperties.getUsername(), twilioProperties.getPassword());
    }
}
