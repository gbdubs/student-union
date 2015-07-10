#!/bin/bash

echo "\n\n------------------------ UPDATING FROM GITHUB ------------------------\n\n"

git pull origin master

echo "\n\n------------------------ GENERATING WEBSITE ------------------------\n\n"

java GenerateWebsite

echo "\n\n------------------------ BEGINNING GIT OPS ------------------------\n\n"

git add *
NOW=$(date)
read -p "Commit description: " desc  
git commit -m "AUTOMATICALLY GENERATED COMMIT FROM $USER ON $NOW"
git push origin gh-pages

echo "\n\n------------------------ COMPLETED UPLOAD ------------------------\n\n"