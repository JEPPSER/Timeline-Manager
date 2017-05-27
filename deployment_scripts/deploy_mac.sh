#!/bin/bash

timeline_manager_jar=$(basename `find . -name "Timeline*Manager*.jar"`)
version=$(basename "$timeline_manager_jar" ".jar" | sed 's/[^0-9.]*\([0-9.]*\).*/\1/')

packr_url="http://bit.ly/packrgdx"
packr_out="Timeline-Manager.app"
jdk_url="http://dl.dropboxusercontent.com/s/bvumozcg5r0o8qw/jdk8_mac.zip?dl=0"
jdk_mac="jdk8_mac.zip"

# packr config
cat <<EOF > ./packr_config_mac.json
{
    "platform": "mac",
    "jdk": "$jdk_mac",
    "executable": "Timeline-Manager",
    "classpath": [
        "$timeline_manager_jar"
    ],
    "mainclass": "main.TimelineManager",
    "resources": [
        "icons.icns"
    ],
    "minimizejre": "hard",
    "output": "$packr_out"
}
EOF

# Download packr if not present
if [[ ! -f ./packr.jar ]]
then
    echo "Downloading packr.."
    curl -L -# "$packr_url" -o "packr.jar"
fi

# Download mac JDK if not present
if [[ ! -f $jdk_mac ]] 
then
    echo "Downloading Mac JDK.."
    curl -L -# "$jdk_url" -o "$jdk_mac"
fi

# Create executable
echo "Packaging.."
java -jar packr.jar packr_config_mac.json

# Cleaning up
echo "Cleaning up.."
rm packr_config_mac.json "$jdk_mac"

echo "Done!"
