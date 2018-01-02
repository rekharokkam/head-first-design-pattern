@set JDK_HOME=C:\bea\jdk150_06
@set ANT_JARS=C:\Rekha\Testing_Workspace\CoreJava\src\com\book\designpattern\compoundpattern\model2\build\libs
@set old_classpath=%classpath%
@set classpath=%ANT_JARS%\ant.jar;%JDK_HOME%\lib\tools.jar;%ANT_JARS%\optional.jar;%ANT_JARS%\xerces.jar
@rem java org.apache.tools.ant.Main -buildfile buildTest.xml -projecthelp
@echo %classpath%
@java org.apache.tools.ant.Main -buildfile buildTest.xml %1
@set classpath=%old_classpath%
