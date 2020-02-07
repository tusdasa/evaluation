@echo off
for /f "delims=" %%t in ('hg log --limit 1') do set str=%%t
echo %str%
::call c1.bat %str%