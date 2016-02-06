/*
 * Copyright IBM Corp. 2016
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

package com.ibm.bluemix.plugin.globalization

import java.io.File
import java.io.PrintStream
import java.util.List
import java.util.Properties

class GlobalizationStepsShell{
	
	// When dryRun is set, only print out the command to be executed
	public static boolean dryRun = false
	
	/**
	 * Takes input in the form of "Step name, Input properties file location, Output properties file location"
	 * @param args
	 * @return
	 */
	static main(String[] args) {
		System.out.println(args.length);
		if (args == null || args.length < 3)
		{
			System.out.println("Error: Step and file location for properties not specified")
			int i = 0
			for (String arg : args ) {
				System.out.println(" args["+i+"] "+arg)
			}
			System.exit(1)
		}
		//read properties from args[1]
		Properties properties = readProps(args[1])
		System.out.println("Running \""+args[0]+"\":")
		int returnCode = 0
		switch (args[0]) {
			case "SetupBluemix":
				returnCode = SetupBluemix(properties)
				break
			case "Translate":
				returnCode = Translate(properties)
				break
			default:
				System.out.println("Error: Specified Step does not exist")
				returnCode = 1
				break
		}
		
		writeProps(args[2], properties)
		
		
		System.exit(returnCode)
		
	}
	
	static int SetupBluemix(Properties inputProperties) {
		String bluemixUserId = inputProperties.getProperty("sBluemixUserId", "")
		String bluemixPassword = inputProperties.getProperty("sBluemixPassword", "")
		String bluemixAPI = inputProperties.getProperty("sBluemixAPI", "")
		String bluemixSpace = inputProperties.getProperty("sBluemixSpace", "")
		String bluemixOrg = inputProperties.getProperty("sBluemixOrg", "")
				
		List<String> command =["bash", "./_init.sh", "-a", bluemixAPI, "-u", bluemixUserId, "-p", bluemixPassword, "-s", bluemixSpace, "-o", bluemixOrg]	
		List<String> rst = new ArrayList<String>()
		int retcode = executeCommand(rst, command)
		for(String item : rst) {
			System.out.println(item)
		}
		
		if (retcode != 0)
		{
			System.out.println("Error _init.sh")
			return retcode
		}
		return 0
	}
	
	static int Translate(Properties inputProperties) {
		String jobType = inputProperties.getProperty("sJobType", "CREATE")
		String inputPattern = inputProperties.getProperty("sInputPattern", "")
		String sourceFolder = inputProperties.getProperty("sSourFolder", "")
		String download = inputProperties.getProperty("sDownload", "false")
		if(sourceFolder == null || sourceFolder.empty) sourceFolder = new File(".").getCanonicalPath()
		
		List<String> translate = ["bash", "./translate_me.sh"]
		List<String> rst = new ArrayList<String>()
		translate <<= "-r"
		translate <<= jobType
		if (inputPattern != null && !inputPattern.empty)
		{
			translate <<= "-s"
			translate <<= inputPattern
		}
		if (sourceFolder != null && !sourceFolder.empty) {
			translate <<= "-f"
			translate <<= sourceFolder
		}
		if (download != null && !download.empty) {
			translate <<= "-l"
			translate <<= download
		}
		
		int retcode = executeCommand(rst, translate)
		for(String item : rst) {
			System.out.println(item)
		}
		if (retcode != 0)
		{
			System.out.println("Error returned from translate")
			return retcode
		}
		return 0
		
	}
	
	static Properties readProps(String path) {
		FileInputStream is = new FileInputStream(new File(path))
		try {
			Properties props = new Properties()
			props.load(is)
			return props
		} finally {
			is.close()
		}
	}
	
	static void writeProps(String path, Properties props, String comment = null) {
		FileOutputStream os = new FileOutputStream(new File(path))
		try {
			props.store(os, comment)
		} finally {
			os.close()
		}
	}
	

	static int executeCommand(List rst, List<String> cmd) {
		println "'" + cmd.join("' '") + "'" // print the command
		if (dryRun) return 0
		File workFile = new File(System.getenv("PLUGIN_HOME"))
			
		ProcessBuilder builder = new ProcessBuilder(cmd)
		builder.redirectErrorStream(true)
		builder.directory(workFile);
		BufferedReader input = null
		try {
			Process process = builder.start()
			input = new BufferedReader(new InputStreamReader(process.getInputStream()))
			String line
			while ((line = input.readLine()) != null) {
				rst.add(line)
			}
			System.out.println("Starting to waitfor")
			int retcode = process.waitFor()
			System.out.println("Done waiting, got value "+retcode)
			return retcode
		}
		catch (Exception err) {
			err.printStackTrace()
		} finally {
			input.close()
		}
		return 2;
	}
}
