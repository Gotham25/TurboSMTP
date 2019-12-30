# TurboSMTP
Kotlin library to send mail using Turbo SMTP API
This is a wrapper for the web API provided by [serversmtp](http://serversmtp.com) mentioned [here](https://serversmtp.com/turbo-api/).

To use this module you have to have account at http://serversmtp.com . Basic account is free of charge

# Version

1.0.0-RELEASE


# Installation

### Gradle

```xml
<dependency>
    <groupId>com.github.gotham25</groupId>
    <artifactId>TurboSMTP</artifactId>
    <version>1.0.0-RELEASE</version>
    <type>pom</type>
</dependency>
```

### Maven

compile group: 'com.github.gotham25', name: 'TurboSMTP', version: '1.0.0-RELEASE'

(or)

implementation 'com.github.gotham25:TurboSMTP:1.0.0-RELEASE'


# Usage

### In Java
```java
  TurboMailer mailer = new TurboMailer.Builder()
                          .AuthUser(TURBO_SMTP_AUTH_USERNAME)
                          .AuthPass(TURBO_SMTP_AUTH_PASSWORD)
                          .build();
  String mailResponse = mailer.sendMail(
                                FROM_EMAIL_ADDRESS,
                                TO_EMAIL_ADDRESS,
                                SUBJECT,
                                CC,
                                BCC,
                                TEXT_EMAIL_BODY,
                                HTML_EMAIL_BODY, //html_content
                                CUSTOM_HEADERS,
                                MIME_RAW);
```

### In Kotlin
```kotlin
val mailer = TurboMailer.Builder()
                          .AuthUser(TURBO_SMTP_AUTH_USERNAME)
                          .AuthPass(TURBO_SMTP_AUTH_PASSWORD)
                          .build();
                          
val mailResponse = mailer.sendMail(
                                FROM_EMAIL_ADDRESS,
                                TO_EMAIL_ADDRESS,
                                SUBJECT,
                                CC,
                                BCC,
                                TEXT_EMAIL_BODY,
                                HTML_EMAIL_BODY,
                                CUSTOM_HEADERS,
                                MIME_RAW);
```

All responses will be as JSON string mentioned in the API doc for a successfull status(i.e 200)
For the rest of responses it's be as JSON response in below format,
```json
{
	"error": error_mesage,
	"errorCode": error_code
}
```
where
    error_message is a string and
    error_code is a integer indicating a statusCode for the response.

