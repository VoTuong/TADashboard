**SOME FEATURES IN FRAMEWORK**

1. Run the parallel test case
2. Wrapper: Element and Action
3. Extent Report
4. Screenshot test case for failed
5. Base function in the package: utils, helpers
6. Read data test from Json file

### **SYSTEM REQUIREMENTS**

- **JDK Version**: jdk 11 or newer
- Chrome Browser, Edge Browser, Firefox Browser
- Download jar and setting Variable Environment as Java JDK
- **IntelliJ** IDE is used to be easy to change JDK version
- Download the lastest version and setting Variable Environment as Maven

### **HOW TO USE**

**1. Run parallel the test case**

- Run test case by XML files (**src/test/resources/testsuites/**)
- Run test case from Maven pom.xml file (mvn clean test)
- Switch parallel mode: testNG or Selenium Grid by changing **target** value in **_config.properties_** file

**2. Extent Report**

- Setup on **_src/main/java/extentreports_** and BaseTest

**3. Screenshot**

- Setup in **_config.properties_** file
  (**_src/test/resources/properties/config.properties_**)

**4. Base function in the package**

- **_src/main/java/com/tadashboard/utils_**
- **_src/main/java/com/tadashboard/helpers_**
- **_src/main/java/com/tadashboard/common_**
- **_src/main/java/com/tadashboard/control_**

**5. Read data test from JSON file**

- **JsonHelper** class select the json file path and call **"get"** method with **key**