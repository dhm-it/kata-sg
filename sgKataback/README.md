# BankKata Back End
 maven project , devolepped with spring boot and spring security 
## Testing with self signed certificates:

 1. **Generating the Server Keystore:**
`keytool -genkeypair -alias secure-server -keyalg RSA -dname "CN=localhost,OU=myorg,O=myorg,L=mycity,S=mystate,C=es" -keypass secret -keystore server-keystore.jks -storepass secret`

 2. **Generating the Client Keystore:** 
`keytool -genkeypair -alias secure-client -keyalg RSA -dname "CN=codependent-client,OU=myorg,O=myorg,L=mycity,S=mystate,C=es" -keypass secret -keystore client-keystore.jks -storepass secret`

 3. **Import the supported client's public certificates intro the server truststore:**
  - **Export the client public certificate**: `keytool -exportcert -alias secure-client -file client-public.cer -keystore client-keystore.jks -storepass secret`
  - **Import it in the server truststore**: `keytool -importcert -keystore server-truststore.jks -alias clientcert -file client-public.cer -storepass secret`

 4. **Import the server's public certificate into the client truststores:**
   - **Export the server public certificate**: `keytool -exportcert -alias secure-server -file server-public.cer -keystore server-keystore.jks -storepass secret`
   - **Import it in the client truststore**: `keytool -importcert -keystore client-truststore.jks -alias servercert -file server-public.cer -storepass secret` 