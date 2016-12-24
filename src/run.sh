#! /bin/bash

javac *.java

echo -e \\n
echo ========= Finding Path from 0 to 98 with congestion ==========

java ShortestPathFinding 1000.brite congested 0 98

echo -e \\n
echo ========= Finding Path from 0 to 98 without congestion ==========

java ShortestPathFinding 1000.brite noncongested 0 98

echo -e \\n
echo ========= braessWithoutAdditionalLink ==========

java ShortestPathFinding braessWithoutAdditionalLink

echo -e \\n
echo ========= braessWithAdditionalLink ==========

java ShortestPathFinding braessWithAdditionalLink
