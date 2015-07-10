#!/bin/bash

printf "\n\n------------------------ UPDATING FROM GITHUB ------------------------\n\n"

git pull origin master

printf "\n\n------------------------ GENERATING WEBSITE ------------------------\n\n"

java GenerateWebsite

printf "\n\n------------------------ BEGINNING GIT OPS ------------------------\n\n"

git add *
NOW=$(date)
read -p "Commit description: " desc  
git commit -m "AUTOMATICALLY GENERATED COMMIT FROM $USER ON $NOW"
git push origin gh-pages

printf "\n\n------------------------ COMPLETED UPLOAD ------------------------\n\n"