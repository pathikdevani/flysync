!include "MUI2.nsh"

Name "flysync"
BrandingText "pathik.linedeer.com"

# set the icon
!define MUI_ICON "logo.ico"

# define the resulting installer's name:
OutFile "..\dist\flysync-installer.exe"


# set the installation directory
InstallDir "$PROGRAMFILES\flysync\"

# app dialogs
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_INSTFILES

!define MUI_FINISHPAGE_RUN_TEXT "Start Flysync"
!define MUI_FINISHPAGE_RUN $INSTDIR\flysync.exe

!insertmacro MUI_PAGE_FINISH
!insertmacro MUI_LANGUAGE "English"

# default section start
Section

  # delete the installed files
  RMDir /r $INSTDIR

  # define the path to which the installer should install
  SetOutPath $INSTDIR

  # specify the files to go in the output path
  File /r ..\build\flysync\win32\*

  # create the uninstaller
  WriteUninstaller "$INSTDIR\Uninstall flysync.exe"

  # create shortcuts in the start menu and on the desktop
  CreateShortCut "$SMPROGRAMS\flysync.lnk" "$INSTDIR\flysync.exe"
  CreateShortCut "$SMPROGRAMS\Uninstall flysync.lnk" "$INSTDIR\Uninstall flysync.exe"
  CreateShortCut "$DESKTOP\flysync.lnk" "$INSTDIR\flysync.exe"
  
  
  Exec "$INSTDIR\service.exe install"

SectionEnd

# create a section to define what the uninstaller does
Section "Uninstall"
  Exec "$INSTDIR\service.exe uninstall"
  
  

  # delete the installed files
  RMDir /r $INSTDIR
  
  

  # delete the shortcuts
  Delete "$SMPROGRAMS\flysync.lnk"
  Delete "$SMPROGRAMS\Uninstall flysync.lnk"
  Delete "$DESKTOP\flysync.lnk"
  
  Delete "$INSTDIR\service.exe"
  

SectionEnd