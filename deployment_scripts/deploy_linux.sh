#!/bin/bash

timeline_manager_jar=$(basename `find . -name "Timeline*Manager*.jar"`)
version=$(basename "$timeline_manager_jar" ".jar" | sed 's/[^0-9.]*\([0-9.]*\).*/\1/')

packr_url="http://bit.ly/packrgdx"
packr_out="timeline_manager_linux"
jdk_url="http://dl.dropboxusercontent.com/s/yp9qmuz4a9jpk1t/jdk8_linux.zip?dl=0"
jdk_linux="jdk8_linux.zip"

# packr config
cat <<EOF > ./packr_config_linux.json
{
    "platform": "linux64",
    "jdk": "$jdk_linux",
    "executable": "Timeline-Manager",
    "classpath": [
        "$timeline_manager_jar"
    ],
    "mainclass": "main.TimelineManager",
    "resources": [
        "icon.ico"
    ],
    "minimizejre": "soft",
    "output": "$packr_out"
}
EOF

# Download packr if not present
if [[ ! -f ./packr.jar ]]
then
    echo "Downloading packr.."
    wget -nv --show-progress "$packr_url" -O "packr.jar"
fi

# Download linux JDK if not present
if [[ ! -f $jdk_linux ]] 
then
    echo "Downloading linux JDK.."
    wget -nv --show-progress "$jdk_url" -O "$jdk_linux"
fi

# Create executable
echo "Packaging.."
java -jar packr.jar packr_config_linux.json

# Create archive
echo "Archiving package.."
tar pczf timeline_manager.tar.gz "$packr_out"

# Cleaning up
echo "Cleaning up.."
rm packr_config_linux.json "$jdk_linux"
rm -rf "$packr_out"

echo "Done!"
