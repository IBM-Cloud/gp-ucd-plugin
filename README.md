
IBM Globalization Pipeline plug-in for UrbanCode Deploy
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

IBM [Globalization Pipeline](https://www.ng.bluemix.net/docs/#services/GlobalizationPipeline/index.html#globalization) is an IBM Bluemix service that provides rapid machine translation for application developers.  The IBM Globalization Pipeline plug-in for UrbanCode Deploy integrates this service as an UrbanCode Deploy component that provides machine translation within your existing build and deploy process.  Using your English resource files as input, the service provides real-time machine translation for the following languages: Simplified Chinese, Traditional Chinese, French, German, Italian, Japanese, Korean, Portuguese, Spanish, and Arabic.  The machine translation output can be leveraged by invoking the [RESTful API](https://gp-beta-rest.ng.bluemix.net/translate/swagger/index.html) or downloading the translations.

## Download

Binary downloads are available here: [releases](https://github.com/IBM-Bluemix/gp-ucd-plugin/releases)

## Installation

See [Installing plug-ins in UrbanCode Deploy](https://developer.ibm.com/urbancode/docs/installing-plugins-ucd/) and follow the steps for installing an automation plug-in.

## Usage

The IBM Globalization Pipeline plug-in for UrbanCode Deploy contains two processes that need to be configured.

### SetupBluemixV2

#### Description

This process is for configuring your Bluemix account parameters.  When configured, the Globalization Pipeline plug-in will search your Bluemix account in the organization and space that you specify for any instances of the Globalization Pipeline service.  If the Globalization Pipeline service is not found, an instance of the service will be created automatically. 
If an instance is found that contains `UCD` in its name, that service instance will be given priority.

#### Parameters

Required fields are denoted by an * on the UrbanCode Deploy properties configuration page:

* **Name** A unique name for the service.
* **Bluemix UserID** The Bluemix ID for your account.
* **Bluemix Password** The Bluemix password for your account.
* **Bluemix Space** The space where you would like to create the Globalization Pipeline service instance.
* **Bluemix Org** The organization where you would like to create the Globalization Pipeline service instance.
* **Bluemix API Endpoint** The URL of a Bluemix API endpoint.  
   For example: *https://api.stage1.ng.bluemix.net*

### TranslateV2

#### Description

This process is for configuring the parameters that will be used to translate your resource files.  With the Globalization Pipeline plug-in for UrbanCode Deploy, you can create bundles, upload source files, and download translated files.

#### Parameters

* **Job Type** is the job that will be run.  
   `CREATE` is currently the only supported job type.
* **Input pattern** is the expression that is used to identify the source files to be translated.  
   For example: `*_en.properties` will match all files that end with `_en.properties`.
* **Source folder** is used to define the folder where source files can be found.   
   If not specified, the working folder will be selected by default.
* **Download translation** Select this option to download the translations to the source folder that is specified.

## History

Version 1

The following features are included in the initial beta release of the plug-in:

* Automatically request translation during the deployment
* Retrieve translations in real time without managing the resource files
* Easily add new languages without having to build or deploy


Support
===

Any questions about the use of this service can be posted to the developerWorks Answers site
using the tag "[Globalization](https://developer.ibm.com/answers/topics/globalization/)".

This plug-in is based on the [Globalization Pipeline Java Client](https://github.com/IBM-Bluemix/gp-java-client) which is a recommended integration method for the IBM [Globalization Pipeline](https://www.ng.bluemix.net/docs/#services/GlobalizationPipeline/index.html#globalization) service.
