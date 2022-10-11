# mx-api-client

This project contains a client library that can be used to call MX external account aggregation web services.

This project can be utilized in your Backbase integration services as a Maven dependency. Simply include the following
maven coordinates in the `dependency` section of your service's `pom.xml`

```aidl
    <groupId>com.backbase.accelerators</groupId>
    <artifactId>mx-api-client</artifactId>
    <version>1.0.0</version>
```

## Build this project

From the root directory of this project, run:

```mvn clean install```

This will compile the project.

| MX API        | Environment | URL                               |
|---------------|-------------|-----------------------------------|
| MDX Real Time | NON-PROD    | https://int-live.moneydesktop.com |
| MDX Real Time | PROD        | https://live.moneydesktop.com     |
| SSO           | NON-PROD    | https://int-sso.moneydesktop.com  |
| SSO           | PROD        | https://sso.moneydesktop.com      |

### Example usage - Defining `application.yml` configuration:

```yaml
mx:
  ssoBaseUrl: https://int-sso.moneydesktop.com
  realTimeBaseUrl: https://int-live.moneydesktop.com
  nexusBaseUrl: https://int-data.moneydesktop.com
  clientId: TBD
  mdApiKey: TBD
```

```java
@Data
@Configuration
@ConfigurationProperties("mx")
public class MxProperties {

    private String ssoBaseUrl;
    private String realTimeBaseUrl;
    private String nexusBaseUrl;
    private String clientId;
    private String mdApiKey;

    public MxRequestSettings toMxRequestSettings() {
        return new MxRequestSettings()
                .setSsoBaseUrl(ssoBaseUrl)
                .setRealTimeBaseUrl(realTimeBaseUrl)
                .setNexusBaseUrl(nexusBaseUrl)
                .setMdApiKey(mdApiKey)
                .setClientId(clientId);
    }
}
```

### Example usage - Defining a Spring Bean in Your Integration Service:

```java
@Configuration
public class MxApiClientConfiguration {

    @Bean
    public MxSsoClient mxSsoClient(MxProperties mxProperties) {
        return new MxSsoClient(
                HttpClient.newHttpClient(),
                mxProperties.toMxRequestSettings());
    }
    
    @Bean
    public MxNexusClient mxNexusClient(MxProperties mxProperties) {
        return new MxNexusClient(
                HttpClient.newHttpClient(),
                mxProperties.toMxRequestSettings());
    }
    
    @Bean
    public MxRealTimeClient mxRealTimeClient(MxProperties mxProperties) {
        return new MxRealTimeClient(
                HttpClient.newHttpClient(),
                mxProperties.toMxRequestSettings());
    }
}
```

### Example usage - Creating an Mx User Id:

There is a static utility class `com.backbase.acclerators.mx.util.MxUtil` that will create the mxUserId according to the 
pattern `"BB"_[Environment]_([SA ExternalID] + [Identity idp.sub]).hashCode()`.