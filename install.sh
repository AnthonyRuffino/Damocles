#!/bin/bash
mvn clean install
#rm -rf $MCS/plugins/Damocles
cp target/*dependencies.jar $MCS/plugins/Damocles.jar
