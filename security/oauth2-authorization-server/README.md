# OAuth2 Authorization Server
This is a simple project on how to set up an OAuth2 authorization server with JWT token.

## Testing authorization code flow

**Run the project**
```sh
./gradlew bootRun
```

**Browse to this link**
```
http://localhost:8080/oauth/authorize?response_type=code&client_id=clientId&state=1234
```

**Enter user name and password**

User name: ciri@thejavaman.dev

Password: pass@123

**After login, the browser will be redirect to `https://thejavaman.dev/`, get the code in uri**

Example: the redirect uri is `https://thejavaman.dev/?code=Yi4frZ&state=1234`. It mean the code is `Yi4frZ`

**Exchange the code for access token**
```sh
curl clientId:clientSecret@localhost:8080/oauth/token -d "code=Yi4frZ&grant_type=authorization_code&client_id=clientId&client_secret=clientSecret"
```

The response will be like this

```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTM2ODQzMDAsInVzZXJfbmFtZSI6ImNpcmlAdGhlamF2YW1hbi5kZXYiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiSGpoWXB0UmpmbWt4dG82TDVabEN0N19mQjhjIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJwaG9uZSIsImVtYWlsIl19.g4AX_fe32ae0h58OzFHCMGE6bt6LBb-JceZflDYnb_VKVYVmc3VlZ7Tij6MfuRbNbjIc5gh8Ad_4Z-gLiPPOl-9XU5_f_qUmhGs6MeKr9x5tPhDeXTNmTCVShoYf73WUMuguDZsJCHaaq-PSCuLBP9t71Jx89CdtPdrIqUhnpYz37a_YoHr3MYQ5hedZvKcJzx6fb382fG_RtLgaMxzRoeb88ji373rxVwC6JGSbmliKIjLmrSf-VqOfEDScQEW0cylaKHJRusei_XcIWdyGRga_w2GnJFqOhI2q-xtGA85SAzwK-X2uXgUlVCi0d7iztbXMJZBQqTrjBLogbcXMwA",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJjaXJpQHRoZWphdmFtYW4uZGV2Iiwic2NvcGUiOlsicGhvbmUiLCJlbWFpbCJdLCJhdGkiOiJIamhZcHRSamZta3h0bzZMNVpsQ3Q3X2ZCOGMiLCJleHAiOjE2MTYyMzMxMDAsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJHUXhoajlPdUdzR1ZFalpsWGVjbm9fQUVlSVEiLCJjbGllbnRfaWQiOiJjbGllbnRJZCJ9.WjNVmLgJBVc6Y44EBk525K47f5ti1MJeyGW86Rsd1hS-iAybPpSTEt4-VeLQynlbpgxMsh98CSRBg44ZOyluFOEmXqQCB_0Z5jUIV77sWUbMKwMVjz3Iv9YYV38fFKIW9KF8CyCFm1hjkXaUa2UN5ZYIuaHGx0b70Z_QNzfWaCIlsvbihbCmAYhb9gHsLytrONPkmdY-Uav-1mcQFlh0jV8OFv0xd0cyPzbWgbISD2qG_WC3NyQKpMtH3qpRNLMxc1Ra6F_aR0IRuw38146hZs_XUyLU1v82_p_toLjFt5FbTXXEk0TOnpcW7XxjCAkuaEx1ZhaciqKjOHvhM61mOQ",
  "expires_in": 43199,
  "scope": "phone email",
  "jti": "HjhYptRjfmkxto6L5ZlCt7_fB8c"
}

```

## Get the public key
```sh
# Password: pass@123
 keytool -list -rfc --keystore ./src/main/resources/jwt/jwt.jks | openssl x509 -inform pem -pubkey
```

## Generate the jks (key store) file by yourself
```sh
# Generate key store file
keytool -genkeypair -alias jwt -keyalg RSA -keypass password -keystore jwt.jks -storepass password

# Convert it to PKCS12 format
keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12
```

After generating jwt.jks file, put in in `resource/jwt`.

## Relevant articles:
[Spring Boot, OAuth2 và OpenID Connect](https://thejavaman.dev/spring-boot-oauth2-openid/)


