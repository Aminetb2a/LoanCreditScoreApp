package patika.dev.definex.loancreditscore.service.sms;


import com.twilio.rest.api.v2010.account.Message;

public interface SmsService {
    Message sendSms(String phoneNumber, String content);
}
