# EBM rule management Protege plugin

This tool is a protege plugin which is used to add rules to classes in protege. This information can be exported and opened with the EBM tool. Rules are written using the cammunda editor in the DMN format and then imported into the plugin.

## Getting Started
### Prerequisites
- [Java](https://www.java.com/en/download/)
- [Maven](https://maven.apache.org/) 
    - [installing maven](https://www.mkyong.com/maven/how-to-install-maven-in-windows/)
- [Protege](https://protege.stanford.edu/)
- [Camunda modeler](https://camunda.com/products/modeler/) - to make rules
  
### Running
After the prerequisites are installed open the command prompt, change to the cloned folder from githut and run the following commands:
```sh
mvn clean package
```
to package the project into a jar
Then go into the 'target' folder and copy the jar. Paste this jar into the plugin folder of your protege installation. Then run protege. To use the plugin open the EBM tool tab

## Using the tool
First follow the instructions on how running the tool.
This plug-in relates decision tables (rules) to concepts/ classes in protege. To get started I recommend making a decision table by follwing this [tutorial](https://docs.camunda.org/get-started/dmn11/model/) (note that at the moment only Variables of type string work). After you've saved you need to open protege
![alt text](https://github.com/tom277/EBM_ruleManagement/blob/master/images/Capture.JPG)

## New Features
-

## Todos
- 


## License
This project is licensed under the MIT License
**Free Software**
