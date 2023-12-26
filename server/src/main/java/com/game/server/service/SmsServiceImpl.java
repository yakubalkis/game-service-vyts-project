package com.game.server.service;

import com.game.server.config.TwilioConfig;
import com.game.server.request.SmsRequest;
import com.twilio.rest.api.v2010.account.MessageCreator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);
    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsServiceImpl(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
        String message = smsRequest.getMessage();

        MessageCreator creator =  Message.creator(to, from, message);
        creator.create();
        LOGGER.info("Send sms " + smsRequest);
    }
}
