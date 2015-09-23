# <div align="center"> <u>Git Manager </div></u>

## Objective  
  
The Git Manager is a tool to act as a complete and convenient interface between GitHub and Processing, and to integrate the awesomeness of version control with Processing. It is meant to be able to provide not only all the offline features of Git, but also the power of collaboration and numerous other features that GitHub brings to the table. Though the tool is primarily meant to enable the user to use all of GitHub’s features via the tool,  the user will be provided the flexibility to use the tool for Git operations alone, if he/she so chooses (for example, add, commit, branch, etc.). The tool's webpage maybe viewed [**here**](http://joelmoniz.com/git-manager/).

## Installation Instructions

### The Easy Way: Installating the Git Manager via the Contribution Manager
The easiest way to install the Git Manager is from within the PDE's Tools Manager:

* Go to `Tools > Add Tool...`
* Click on `Git Manager`, and click on the `Install` button in the bottom pane

### The Hard Way: Manually Installating the Git Manager
To use Git Manager from within the Processing IDE (like a normal tool), do the following:

* Create a folder named "tools" inside your Sketchbook folder (if not already present). The location of the sketchbook folder is shown in Processing's Preferences dialog.
* Download the latest release ([GitManager.zip](https://github.com/joelmoniz/Git-Manager/releases/tag/latest)) and extract it into the "tools" folder. Ensure that in the tools folder, GitManager folder has src, tools, etc. within it, and not another sub-folder named GitManager.  
* Start Processing  

## Instructions to Build Git Manager
To compile Git Manager, do the following:

1. Clone Git Manager into a location of your choice:  
Change the directory to where you would like to clone Git Manager to, and the run the  following command in the Git Bash Terminal:   
`git clone https://github.com/joelmoniz/Git-Manager.git`  
Note: If you would like to contribute to the Git Manager, you'll have to fork it instead.  
2. Import the cloned project into Eclipse
3. Open the build.properties file in the resources folder, and change the property "sketchbook.location" to wherever your Processing sketchbook is located, as instructed in the file
4. Now, to show the Ant View, go to Window > Show View > Ant
5. Drag the resources/build.xml file onto the Ant view there
6. Press the 'Play' button, and voilà!

<!--  
>##Sources  
  
>The following is a list of sources that are being (or have been) used while creating this tool. Though the tool is not complete, this list will keep building up as the tool progresses so that I don't miss a source out:  
>
* [Processing Source Code](https://github.com/processing/processing)  
>Since I wanted the look and feel of Git Manager to be similar to Processing, a significant part of especially the Expertise Level Menu have been done with Processing's source-code used as a reference.
* [StackOverflow](http://stackoverflow.com/)  
>For every time I get stuck... If I listed down the number of times I've (already) opened this web-site when creating this tool, I'd probably spend a good half-an-hour counting :p  
* [Online Java Documentation](http://docs.oracle.com/javase/tutorial/uiswing/)  
>For every single time that I forget a complicated syntax :)
-->



<!---
The following describes how to set up a Processing tool project in Eclipse and build it successfully, and to make your tool ready for distribution.

## Import to Eclipse

There are two options to import the template project into Eclipse: using a Git [fork](https://help.github.com/articles/fork-a-repo) or using a downloaded package. If you are not familiar with Git or GitHub, you should opt for the downloaded package.

### Option A: GitHub

1. Fork the template repository to use as a starting point.
  * Navigate to https://github.com/processing/processing-tool-template in your browser.
  * Click the "Fork" button in the top-right of the page.
  * Once your fork is ready, open the new repository's "Settings" by clicking the link in the menu bar on the right.
  * Change the repository name to the name of your tool and save your changes.
  * NOTE: GitHub only allows you to fork a project once. If you need to create multiple forks, you can follow these [instructions](http://adrianshort.org/2011/11/08/create-multiple-forks-of-a-github-repo/).
1. Clone your new repository to your Eclipse workspace.
  * Open Eclipse and select the File â†’ Import... menu item.
  * Select Git â†’ Projects from Git, and click "Next >".
  * Select "URI" and click "Next >". 
  * Enter your repository's clone URL in the "URI" field. The remaining fields in the "Location" and "Connection" groups will get automatically filled in.
  * Enter your GitHub credentials in the "Authentication" group, and click "Next >".
  * Select the `master` branch on the next screen, and click "Next >".
  * The default settings on the "Local Configuration" screen should work fine, click "Next >".
  * Make sure "Import existing projects" is selected, and click "Next >".
  * Eclipse should find and select the `processing-tool-template` automatically, click "Finish".
1. Rename your Eclipse project.
  * In the Package Explorer, right-click (ctrl-click) on the folder icon of the `processing-tool-template` project, and select Refactor â†’ Rename... from the menu that pops up. 
  * Give the project the name of your tool, and click "OK".
  
### Option B: Downloaded Package

1. Download the latest Eclipse template from [here](https://github.com/processing/processing-tool-template/releases). **Don't unzip the ZIP file yet.**
1. Create a new Java project in Eclipse. 
  * From the menubar choose File â†’ New â†’ Java Project. 
  * Give the project the name of your tool.
  * Click "Finish".
1. Import the template source files.
  * Right-click (ctrl-click) onto the folder icon of your newly created project in the Package Explorer and select "Import..."`" from the menu that pops up. 
  * Select General â†’ Archive File, and click "Next >".
  * Navigate to the ZIP file you downloaded earlier in step 1, and click "Finish".
  
## Set Up and Compile
  
1. Add Processing to the project build path.
  * Open your project's "Properties" window. 
  * Under "Java Build Path", select the "Libraries" tab and then "Add External JARs...".
  * Locate and add Processing's `pde.jar` to your build path. If you're planning on using features of Processing's `PApplet` class, you'll need to add `core.jar` to the project's build path as well. It is recommended to make copies of `core.jar` and `pde.jar` in your Eclipse workspace in a `libs` folder. If this folder does not exist yet, create it. Read the [section below](#AddingJARs) regarding where to find the `core.jar` and `pde.jar` files.
  * Confirm the setup with "OK".
1. Edit the tool properties.
  * Open the `resources` folder inside of your Java project and double-click the `build.properties` file. You should see its contents in the Eclipse editor.
  * Edit the properties file, making changes to items 1-4 so that the values and paths are properly set for your project to compile. A path can be relative or absolute.
  * Make changes to items under 5. These are metadata used in the automatically generated HTML, README, and properties documents.
1. Compile your tool using Ant.
  * From the menu bar, choose Window â†’ Show View â†’ Ant. A tab with the title "Ant" will pop up on the right side of your Eclipse editor. 
  * Drag the `resources/build.xml` file in there, and a new item "ProcessingTools" will appear. 
  * Press the "Play" button inside the "Ant" tab.
1. BUILD SUCCESSFUL. The tool template will start to compile, control messages will appear in the console window, warnings can be ignored. When finished it should say BUILD SUCCESSFUL. Congratulations, you are set and you can start writing your own tool by making changes to the source code in folder `src`.
1. BUILD FAILED. In case the compile process fails, check the output in the console which will give you a closer idea of what went wrong. Errors may have been caused by
  * Incorrect path settings in the `build.properties` file. Things are most likely to go wrong at item 2, where you specify the path to `pde.jar` and other included JARs.
  * Error "Javadoc failed". If you are on Windows, make sure you are using a JDK instead of a JRE in order to be able to create the Javadoc for your tool. JRE does not come with the Javadoc application, but it is required to create tools from this template.

After having compiled and built your project successfully, you should be able to find your tool in Processing's sketchbook folder, examples will be listed in Processing's sketchbook menu. Files that have been created for the distribution of the tool are located in your Eclipse's `workspace/yourProject/distribution` folder. In there you will also find the `web` folder which contains the documentation, a ZIP file for downloading your tool, a folder with examples as well as the `index.html` and CSS file.

To distribute your tool please refer to the [Tool Guidelines](https://github.com/processing/processing/wiki/Tool-Guidelines).

## Source code

If you want to share your tool's source code (and we know you do), we recommend using an online repository available for free at [Google Code](http://code.google.com) or [GitHub](http://github.com).

## <a name='AddingJARs'/>Adding pde.jar and other .jar files to your classpath

The `pde.jar` file contains classes responsible for creating the Processing Development Environment itself. It must be part of your classpath when building a tool. The `core.jar` file does not necessarily need to be added to your classpath, however, you will need to include it if you plan on creating any external tool windows based on `PApplet`. On Windows and Linux, these files are located in the Processing distribution folder inside a folder named "lib". On Mac OS X, right-click `Processing.app` and use `Show Package Contents` to see the guts. The `lib` folder is at `Contents` â†’ `Resources` â†’ `Java` â†’ `lib`. For further information about the classes in `pde.jar`, you can see the source [here](http://code.google.com/p/processing/source/browse/trunk/processing#processing/app) and the developer documentation [here](http://processing.googlecode.com/svn/trunk/processing/build/javadoc/everything/index.html).

If you created a `libs` folder as described above, put the libraries you need to add to your classpath in there. In the `Properties` of your Java project, navigate to `Java Build Path` â†’ `Libraries`, and click `Add External JARs`. Select the `.jar` files from the `libs` folder that are required for compiling you project. Adjust the `build.properties` file accordingly.

The `libs` folder is recommended but not a requirement, nevertheless you need to specify where your `jar` files are located in your system in order to add them to the classpath.

In case a tool depends on systems libraries, put these dependencies next to the `jar` file. For example processing's `opengl.jar` library depends on JOGL hence the DLLs (for Windows) or jnilibs (for OS X) have to be located next to the `opengl.jar` file.

## The JDK, the JRE, Ant, & javadoc

For more information about these, see the relevant sections in the [Eclipse Library Template](https://github.com/processing/processing-library-template) README.

-->
