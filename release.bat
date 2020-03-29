@echo off
set Tss=%TIME:~6,2%
set Tmm=%TIME:~3,2%
set Thh=%TIME:~0,2%
set Thh=%Thh: =0%
set currentTime=%Thh%.%Tmm%.%Tss%
set currentDay=%date:~,4%-%date:~5,2%-%date:~8,2%
md %BUILD_DIR%\build-%currentDay%-%currentTime%
copy %BUILD_DIR%\*.jar %BUILD_DIR%\build-%currentDay%-%currentTime%\
del /q %BUILD_DIR%\*.jar