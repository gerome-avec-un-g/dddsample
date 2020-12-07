package fr.geromeavecung.dddsample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="my.library")
public class LibraryApplicationPropertiesConfiguration {

    private String version = "UNKNOWN";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "LibraryApplicationPropertiesConfiguration{" +
                "version='" + version + '\'' +
                '}';
    }

}
