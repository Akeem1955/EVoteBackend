package com.example.evote.service;


import com.example.evote.ticket.UserTicket;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.rest.verify.v2.service.VerificationCheckCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsService {
    private String account_sid = "Get Your Own :)";
    private String auth_token = "Get Your Own:)";
    private String verify_sid = "Get Your Own :)";
    private String twillo = "Phone num goes here";
    SmsService(){
        Twilio.init(account_sid, auth_token);
    }
    public void sendSms(String phoneNo){
        Verification.creator(
                verify_sid,phoneNo,"sms").create();
    }
    public boolean verifySms(String otp,String phone){


        VerificationCheck checker =  VerificationCheck.creator(verify_sid)
                .setTo(phone)
                .setCode(otp)
                .create();
        if(checker.getStatus().equals("approved")){
            return true;
        }
        System.out.println(checker.getStatus());
        return false;
    }

}
