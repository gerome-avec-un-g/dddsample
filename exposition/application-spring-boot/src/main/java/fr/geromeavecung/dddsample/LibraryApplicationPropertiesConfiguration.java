package fr.geromeavecung.dddsample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="my.library")
public class LibraryApplicationPropertiesConfiguration {

    private String version = "UNKNOWN";

    private String environment = "UNKNOWN";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "LibraryApplicationPropertiesConfiguration{" +
                "version='" + version + '\'' +
                ", environment='" + environment + '\'' +
                '}';
    }
}
