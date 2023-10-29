package com.airnz.email.util;

import com.airnz.email.model.EmailAddress;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailHelper {

    public String [] listToArray(List<EmailAddress> emailAddressList){
        if (emailAddressList != null && emailAddressList.size()> 0) {
            List<String> emailAddresses = new ArrayList<String>();
            emailAddressList.forEach(addr -> emailAddresses.add(addr.getEmailAddress()));
            return emailAddresses.toArray(String[]::new);
        }
        return new String[]{};
    }

    public Boolean isEmailValid(String emailAddress){
        return EmailValidator.getInstance().isValid(emailAddress);
    }

}
