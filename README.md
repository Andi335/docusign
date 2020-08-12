# DigSig Prototype
## Interface for signing documents digital
* Entrypoint: src/main/java/com/forcont/digsigproto/DigSigApplication.java
### Running the Application

#### Configure
Following configurations taking place in `/src/main/resources/application.properties`
* Choose DigitalSignatureService-Profile `spring.profiles.active`
	* `docusign` *(default)*
	* `adobesign` *(not implemented yet, will throw exception)*
* Set WebServer-Port
	* `8008` *(default)*

#### Build 
* go to project root
* run `gradle build`

#### Start (command line)
* go to project root
* run `java -jar /build/libs/docusign-interface-[...]`

### DocuSign Interface


[...]


