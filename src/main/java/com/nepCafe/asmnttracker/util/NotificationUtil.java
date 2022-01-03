package com.nepCafe.asmnttracker.util;

import org.nepCafe.kafka.client.contants.NotificationConstants;

public class NotificationUtil {

    public static String mapEmpStatusToAction(String empStatus){

        switch (empStatus.toUpperCase()){

            case ASTConstants.PROCESSING:
                return NotificationConstants.PROCESSING_CONSULTANT;
            case ASTConstants.TRAINING:
                return NotificationConstants.TRAINING_CONSULTANT;
            case ASTConstants.MARKETTING:
                return NotificationConstants.MARKETTING_CONSULTANT;
            case ASTConstants.ONBOARDING:
                return NotificationConstants.ONBOARDING_CONSULTANT;
            case ASTConstants.ACTIVE:
                return NotificationConstants.ACTIVE_CONSULTANT;
            case ASTConstants.TERMINATED:
                return NotificationConstants.TERMINATED_CONSULTANT;
            default:
                return null;
        }
    }

}
