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

### Using the tool
First follow the instructions on how running the tool.
This plug-in relates decision tables (rules) to concepts/ classes in protege. To get started I recommend making a decision table by follwing this [tutorial](https://docs.camunda.org/get-started/dmn11/model/) (note that at the moment only Variables of type string work). I also recommend making an onltolgy in protege ([tutoria](https://medium.com/@vindulajayawardana/ontology-generation-and-visualization-with-prot%C3%A9g%C3%A9-6df0af9955e0)). Now initialize the EBM tab, which is under window -> tab -> EBM rule management. You should be able to see this:
![alt text](https://github.com/tom277/EBM_ruleManagement/blob/master/images/Capture.JPG)
##### Adding Rules
- Press 'Add rule' (a pop up should appear)
- Press the 'Load' button find the desired decision table file (a file with a dmn extension)
- Fill in the remaining fields
- Press 'Add' button
##### Adding Relations
- Select the class to which you want to add a rule from the Class hierarchy view
- Press the 'Add relation' button 
- Press the 'Add Rules' button
- Selected the rules you want to add (multiple rules can be selected by holding the 'Ctrl' key)
- Press the 'Add' Button
##### Exporting
Once you've added all the rules and completed the ontolgy you can export the information to be used by the [EBM_tool](https://github.com/tom277/EBM_tool). 

- Ensure that the file is saved in RDF/XML format
- Press 'export' button
- choose where you want to save the file to
- choose a file name and add the .per extension (Ex: FileName.per)
- Now the file can be opened from the [EBM_tool](https://github.com/tom277/EBM_tool)

##### Adding Definitions and extra information
- Go to the Entities tab
- Select class to which you want to add aditional information
- On the view on the right select the Annotations tab
- Select the button '+' shown below

![alt text](https://github.com/tom277/EBM_ruleManagement/blob/master/images/Capture5.JPG)
- Choose the type of extra information that you want to add
- Alternatively make a new type of information
    - Currently the only types read by the [EBM_tool](https://github.com/tom277/EBM_tool) are: "definition", "comment" and "reference"

![alt text](https://github.com/tom277/EBM_ruleManagement/blob/master/images/Capture6.JPG)
## New Features
-

## Todos
- 


## License
This project is licensed under the MIT License
**Free Software**

## Acknowledgments
- HOANG THUAN, N. (2016). ESTABLISH CROWDSOURCING AS AN ORGANISATIONAL BUSINESS PROCESS: A DESIGN SCIENCE APPROACH. Ph.D. Victoria University of Wellington. --  Used for the ontology shown as example
