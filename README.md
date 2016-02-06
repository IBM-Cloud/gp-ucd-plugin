
IBM Globalization Pipeline Service Plug-in for UrbanCode Deploy
===



<!--
/*    
 * Copyright IBM Corp. 2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
-->


## What is this?

IBM [Globalization Pipeline](https://www.ng.bluemix.net/docs/#services/GlobalizationPipeline/index.html#globalization) plugin for UrbanCode Deploy is a component for you to integrate machine translation into your build and deploy process. It provides real time machine translation for your English resource files, and allows you to integrate the translation using static or dynamic methods. 


## Installation

No special steps are required for installation. See [Installing plug-ins in UrbanCode Deploy](https://developer.ibm.com/urbancode/docs/installing-plugins-ucd/).

## Usage

### SetupBluemix 

#### Description

This step will look for the Globalization Pipeline service instance in your space and will create one if none existing. 
Tf existing many instances already, the instance contains UCD in its name will take prior priority.

#### Parameters

Fill in the * fileds according to the Bluemix page

* **Name** could be any name you like.
* **Bluemix UserID** is the login id of the Bluemix page.
* **Bluemix Password** is the login password of the Bluemix page.
* **Bluemix Space** is the space of the Bluemix page.
* **Bluemix Org** is the org of the Bluemix page.
* **Bluemix API Endpoint** is the url of bluemix API endpoint.  
   For eg: *https://api.stage1.ng.bluemix.net*

### Translate 

#### Description

This step will manage for the process of translating which may contain *create bundle*, *upload source file*, *download translated files*(optional).  
The bundle name has max 30 characters and is the same with the source file(May contain sub folder name if the source file in the sub folder of the specified source folder).

#### Parameters

* **Job Type** can only be **CREATE** currently.
* **Input pattern** is the expression to match the source files.  
   For eg: **_en.properties*, this will match all the files ending with _en.properties.
* **Source folder** is to define the folder of source files.   
   If not specified it will search in working folder as default.
* **Download translation** default is unchosen.  
   If chosen, it will download translated files to the source file folder.

## History

Version 1

The following features are included in the initial beta release of the plug-in:

* Automatically request the translation during the deployment
* Retrieve the translation on the fly without managing the resource files
* Easily add a new language without build or deployment


Support
===
You can post questions about using this service in the developerWorks Answers site
using the tag "[Globalization](https://developer.ibm.com/answers/topics/globalization/)".

This plugin is based on [Globalization Java Client](https://github.com/IBM-Bluemix/gp-java-client) which is a recommended integration method with IBM [Globalization Pipeline](https://www.ng.bluemix.net/docs/#services/GlobalizationPipeline/index.html#globalization)






