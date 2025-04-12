Project Setup Guide for Backend in IntelliJ

Setting Up Project Settings

Open the project, click on `File` and look for `Settings`.After opening the project and navigating to File, click on Settings.



Once in the settings, locate the Build, Execution, Deployment section. Then, find the Build Tools option and change it to Any Changes. Remember to click Apply at the button, followed by OK.



Next is Setting Up IvyIDEA

First step, you need to install IvyIDEA first. Double click “Shift“ in your keyboard and this will pop up. If you already have IvyIDEA in your intellij, please skip steps 1 to 3.



Once it pops up, type the word “plugin“ and click the third option.


After clicking the third option, type “ivIDEA“ and install it. (In my case I have already installed it)


Once you have installed it, go to project structure and click modules.



Next step, you will click the folder of your project and click the “plus sign“ button on the top then choose the ivyIDEA.



Once you have it, click the folder button here and choose the directory of your “ivy.xml” file.

Once you are done, on the bottom part you will check the “Use module specific ivy settings“ and then choose the “Use ivy default“. Click apply then ok.


Also please make sure that in project settings, you have done this.


Once you have done all of the steps above, right click the folder of your project and at the bottom you will see an option “IvyIDEA“ go to that option and another options will pop up. Choose the first one.



After clicking it, a folder of ivyIDEA should appear under External Libraries.


Once done, you will fix the errors. Click alt+enter and choose “Add ivyIDEA to classpath“. And you are done.



Marking the Root Type of Packages

First Right-click on the java folder in your project.
Select Mark Directory as > 'Sources Root'.

Then Right-click on the resource folder in your project.
Select Mark Directory as > 'Resources Root'.


Last Right-click on the test java folder in your project.
Select Mark Directory as > 'Test Sources Root'.




When an error like this occurs, go to File > Project Structure.


Under Project, select the correct JDK from the Project SDK dropdown. Click apply then ok.


Go back to our project and refresh it and it's done. 


