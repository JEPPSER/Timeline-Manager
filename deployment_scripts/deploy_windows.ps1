# Script for creating installer for Timeline Manager on Windows

$timeline_manager_jar = Get-ChildItem .\ -name "Timeline*Manager*.jar"
$version = [io.path]::GetFileNameWithoutExtension("$timeline_manager_jar") -replace "[^\d+\.]",""
If ([string]::IsNullOrEmpty($version)) {
    $version = "1.0.0"

    If ($timeline_manager_jar) {
        $name = [io.path]::GetFileNameWithoutExtension("$timeline_manager_jar")
        Rename-Item ".\$timeline_manager_jar" "$name-$version.jar"
        $timeline_manager_jar = "$name-$version.jar"
    }
}
$packr_jar = "packr.jar"
$packr_url = "http://bit.ly/packrgdx"
$packer_out = "installation_files"
$inno = "inno5_portable"
$inno_url = "http://dl.dropboxusercontent.com/s/2qq8tbeum16ifof/inno5_portable.zip?dl=0"
$inno_installer_creation_script = "windows_installer.iss"
$jdk = "jdk8_win64.zip"
$jdk_url = "http://dl.dropboxusercontent.com/s/43vakmh2jkwz60l/jdk8_win64.zip?dl=0"

# Temporary packr config file
$config_win = "packr_config_win.json"

# Packr config
@"
{
    "platform": "windows64",
    "jdk": "${jdk}",
    "executable": "Timeline-Manager",
    "classpath": [
        "${timeline_manager_jar}"
    ],
    "resources": [
        "icon.ico"
    ],
    "mainclass": "main.TimelineManager",
    "minimizejre": "hard",
    "output": "${packer_out}"
}
"@ | Out-File -Encoding ascii -FilePath ".\$config_win"

If (Test-Path ".\Timeline*-Manager*.jar") {
    $client = New-Object System.Net.WebClient

    # Download packr if not present
    If (-Not (Test-Path ".\$packr_jar")) {
        Write-Host "Downloading packr.."
        $client.downloadFile($packr_url, ".\$packr_jar")
    }

    # Download windows jdk if not present
    If (-Not (Test-Path ".\$jdk")) {
        Write-Host "Downloading windows JDK.."
        $client.downloadFile($jdk_url, ".\$jdk")
    }

    # Build executable
    Write-Host "Building executable.."
    java -jar ".\$packr_jar" ".\$config_win"

    # Update installer version
    Write-Host "version is $version"
    (Get-Content ".\$inno_installer_creation_script") -replace "^#define MyAppVersion.*$", "#define MyAppVersion `"$version`"" | Set-Content ".\$inno_installer_creation_script"

    # Download inno 5 if not present
    If (-Not (Test-Path ".\$inno")) {
        Write-Host "Downloading inno 5.."
        $client.downloadFile($inno_url, ".\$inno.zip")
        Expand-Archive ".\$inno.zip" -DestinationPath ".\$inno"
    }

    # Create installer
    Write-Host "Creating installer.."
    Invoke-Expression "& .\$inno\ISCC.exe /Qp .\$inno_installer_creation_script"

    # Wait for resources to be released
    Start-Sleep -s 3

    # Clean up
    Write-Host "Cleaning up.."
    Remove-Item ".\$inno.zip"
} Else {
    Write-Host "Error: Timeline Manager jar not found. If it has been copied to this directory, make sure it's name is `"Timeline-Manager-[version nr].jar`". For example: `"Timeline-Manager-1.0.0.jar`""
}

Remove-Item ".\$config_win"
Read-Host "Press 'Enter' to exit.."