# chatbot


### swagger
pom.xml
<br>
<version>2.6.2</version> -> <version>2.5.2</version>
```
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.6.1</version>
		</dependency><!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.6.1</version>
		</dependency>
```

config/SwaggerConfig.java
```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```
controller/BotController.java
```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```
service/AmazonService.java
```java
@Service
public class AmazonService {

    public String searchProducts(String keyword) {
        return "Searched for:" + keyword;
    }
}
```
controller/BotController.java
```java
@Service
@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    AmazonService amazonService;

    @RequestMapping(value = "/amazon", method = RequestMethod.GET)
    public ResponseEntity<?> getOneStudent(@RequestParam String keyword)
    {
        return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
    }
}
```
http://localhost:8080/swagger-ui.html#
<br>
commit - with swagger
###Find the search api
service/AmazonService.java
```java
    private String getProductHtml(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.amazon.com/s?k=" + keyword +"&crid=1FVOAPA9AELRZ&sprefix=ipod%2Caps%2C181&ref=nb_sb_noss_1")
                .method("GET", null)
                .addHeader("authority", "www.amazon.com")
                .addHeader("cache-control", "max-age=0")
                .addHeader("rtt", "100")
                .addHeader("downlink", "10")
                .addHeader("ect", "4g")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"98\", \"Google Chrome\";v=\"98\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"macOS\"")
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36")
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-mode", "navigate")
                .addHeader("sec-fetch-user", "?1")
                .addHeader("sec-fetch-dest", "document")
                .addHeader("referer", "https://www.amazon.com/")
                .addHeader("accept-language", "en-US,en;q=0.9,he;q=0.8")
                .addHeader("cookie", "session-id=131-3987483-5342545; session-id-time=2082787201l; i18n-prefs=USD; sp-cdn=\"L5Z9:IL\"; skin=noskin; ubid-main=133-9581942-3780819; session-token=9wuRyGDfGYRNIQddpLSfu/7ZXPkW7pQoYBvMwmGsIhnfY51Iqj+MGS377K23lkwtdjhFx7pU6lcOwFpb0U6WneR7bB7A16Fx7VbQbOEVXnmyIo1i6GXJCcO4dDDR8dN3t5hl/PRi/+vWZPCxL7pYX7A0IOLl74C6MykvrYSxmA29bWSf8tfTif60UEjxD0kk; csm-hit=tb:TTHKG9BMH5E1XG8YFBGP+s-PMZSXATEMDSYE2A3YAS8|1643843190534&t:1643843190534&adb:adblk_yes; session-token=\"2xJZqprgyjzxD7aWv5lvF6L2Jj6wbj75+0bl0405+iQ/teLGzDnuRJLFAIT+TZQlkgfSAzqMtgIwEeHBFVD0aip6GkUe+j61VFbW6p2RqtI4ZpKzPhLXUan7/XN3IT54f1XxTmwQcSjyB2GQ/9kA6/4K7i8kFxf9gYWBIaSqklC4vZX3A8WEtWGUcQxKUx1PP7imQpBv5nT5pijsa/fk2A==\"")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
```
