title Welcome to the El GranBazar launcher V 1.1

 color 
 cls
 @echo off
 set CLASSPATH=.\lib\*
 set path_class= . ../../../FONTS/lib/commons-lang3-3.12.0.jar ../../../FONTS/lib/commons-lang3-3.12.0-javadoc.jar ../../../FONTS/lib/commons-lang3-3.12.0-sources.jar ../../../FONTS/lib/commons-lang3-3.12.0-tests.jar ../../../FONTS/lib/commons-lang3-3.12.0-test-sources.jar ../../../FONTS/lib/opencsv-5.5.2.jar

:config
 cls
 echo.
 echo.
 echo Please specify the path where the "jar.exe" file is located.
 echo It is usually found in C:\Program Files\Java\jdk-11.0.12\bin\
 echo.
 echo.

 set /p newpath=

 set PATH=%PATH%;%newpath%

 cls
 echo.
 echo.
 echo The new PATH is:
 echo %PATH%
 echo.
 echo.
 pause
 goto menu

:menu
 cls
 echo.
 echo.
 echo These are the available commands:
 echo. 
 echo.
 echo 1.- Default compilation
 echo 2.- Clear jar files
 echo 3.- Finish Execution
 echo.

 set /p command=

 IF %command%==1 goto default_compilation
 IF %command%==2 goto clear
 IF %command%==3 goto exit_
 goto menu

:default_compilation
 cls
 echo.
 echo.
 echo What package do you want to compile?
 echo. 
 echo.
 echo 1.- Data
 echo 2.- Domain
 echo 3.- Test
 echo 4.- All
 echo 5.- Go to menu
 echo.
 echo.

 set /p command=

 IF %command%==1 call :default_data
 IF %command%==2 call :default_domain
 IF %command%==3 call :default_test
 IF %command%==4 goto all
 IF %command%==5 goto menu
 goto default_compilation

:all
 cls
 call :default_data
 call :default_domain
 call :default_test
 goto default_compilation

:default_data
 cls
 echo.
 echo We compile the files of the Data layer.
 echo.
 FOR /R ..\EXE\Data_Layer\ /D %%v IN (*) DO (
    FOR /R .\data\ %%f IN (Driver*.java) DO (
        IF Driver%%~nv == %%~nf (
            echo Compiling %%~nf
            echo Main-Class: data.%%~nf > MANIFEST.MF
            echo Class-Path: %path_class% >> MANIFEST.MF
            javac -encoding utf8 --source-path .\data\ -source 11 -target 11 -d %%v .\data\*.java 
            jar --create --file %%~nf.jar --manifest=MANIFEST.MF -C %%v .
            rmdir /s /q %%v\data 
            move /Y MANIFEST.MF %%v > nul
            move /Y %%~nf.jar %%v > nul
        )
    )
 )
 exit /B

:default_domain
 cls
 echo.
 echo We compile the files of the Domain layer.
 echo.
  FOR /R ..\EXE\Domain_Layer\ /D %%v IN (*) DO (
    FOR /R .\domain\ %%f IN (Driver*.java) DO (
        IF Driver%%~nv == %%~nf (
            echo Compiling %%~nf
            echo Main-Class: domain.%%~nf > MANIFEST.MF
            echo Class-Path: %path_class% >> MANIFEST.MF
            javac -encoding utf8 --source-path .\domain\ -source 11 -target 11 -d %%v .\domain\*.java .\data\*.java 
            jar --create --file %%~nf.jar --manifest=MANIFEST.MF -C %%v .
            rmdir /s /q %%v\data 
            rmdir /s /q %%v\domain 
            move /Y MANIFEST.MF %%v > nul
            move /Y %%~nf.jar %%v > nul
        )
    )
 )
 exit /B

:default_test
 cls
 echo.
 echo We compile the files of Test JUnit.
 echo.
 echo Compiling RunnerTest.java
 echo Main-Class: test.domain.RunnerTest > MANIFEST.MF
 echo Class-Path: ../FONTS/lib/junit-4.12.jar ../FONTS/lib/hamcrest-core-1.3.jar >> MANIFEST.MF
 javac -encoding utf8 --source-path .\test\domain\ -source 11 -target 11 -d ..\TESTS\ .\test\domain\*.java .\domain\User.java 
 jar --create --file RunnerTest.jar --manifest=MANIFEST.MF -C ..\TESTS\ .
 rmdir /s /q ..\TESTS\test\
 rmdir /s /q ..\TESTS\domain\
 move /Y RunnerTest.jar ..\TESTS\ > nul
 move /Y MANIFEST.MF ..\TESTS\ > nul
 exit /B

:clear
 cls 
 FOR /R ..\EXE\Data_Layer\ /D %%v IN (*) DO (
     del /f /q %%v\*.jar > nul
 )
 FOR /R ..\EXE\Domain_Layer\ /D %%v IN (*) DO (
     del /f /q %%v\*.jar > nul
 )
 FOR /R ..\TESTS\ %%f IN (*.jar) DO (
     del /f /q %%f > nul
 ) 
 echo.
 echo.
 echo All files deleted.
 echo.
 echo.
 pause
 goto menu

:exit_
 cls
 echo.
 echo Execution completed!
 echo.
 pause 
 exit