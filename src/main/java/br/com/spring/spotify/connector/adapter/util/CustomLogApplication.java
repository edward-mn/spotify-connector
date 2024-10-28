package br.com.spring.spotify.connector.adapter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomLogApplication {
    private static final Logger log = LoggerFactory.getLogger(CustomLogApplication.class);

    private String stringFormater(String serviceType, String resourceType, String description, String status){
        return String.format(
                "%s | Service: %s | Resouce: %s | %s | Status: %s",
                StringUtils.PROJECT_DESCRIPTION, serviceType, resourceType, description, status);
    }

    private void setLogStart(String serviceType, String resourceType, String description){
        log.info(stringFormater(
                serviceType,
                resourceType,
                description,
                "Started"
        ));
    }

    private void setLogError(String serviceType, String resourceType, String description){
        log.error(stringFormater(
                serviceType,
                resourceType,
                description,
                "Failed"
        ));
    }

    public void setLogInfoHandlerError(String resourceType, String information){
        setLogError(StringUtils.CUSTOM_LOG_HANDLER_OBJECT_DESCRIPTION, resourceType, information);
    }

    public void setLogInfoWebClient(String resourceType, String destination){
        setLogStart(StringUtils.CUSTOM_LOG_WEB_CLIENT_DESCRIPTION, resourceType, "Forward to -> " + destination);
    }

    public void setLogErrorWebClient(String resourceType, String information){
        setLogError(StringUtils.CUSTOM_LOG_WEB_CLIENT_DESCRIPTION, resourceType,information);
    }
}
