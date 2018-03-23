package com.etree.tms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("TMS")
public class TmsProperties {
  private String headerApiKey;

public String getHeaderApiKey() {
	return headerApiKey;
}

public void setHeaderApiKey(String headerApiKey) {
	this.headerApiKey = headerApiKey;
}
}
