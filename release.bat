@echo off
set Tss=%TIME:~6,2%
set Tmm=%TIME:~3,2%
set Thh=%TIME:~0,2%
set Thh=%Thh: =0%
set folder=%Thh%.%Tmm%.%Tss%
md %BUILD_DIR%\bulid-%folder%
copy %BUILD_DIR%\*.jar %BUILD_DIR%\build-%folder%\
del /q %BUILD_DIR%\*.jar