package com.game.server.service;

import com.game.server.request.SmsRequest;

public interface SmsService {
    void sendSms(SmsRequest smsRequest);
}
