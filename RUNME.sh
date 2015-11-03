#!/bin/bash

printf "\n\n------------------------ UPDATING FROM GITHUB ------------------------\n\n"

git pull origin master

printf "\n\n------------------------ GENERATING WEBSITE PART 1 ------------------------\n\n"

java GenerateWebsite
sleep 5

printf "\n\n------------------------ GENERATING WEBSITE PART 2 ------------------------\n\n"

java GenerateWebsite
sleep 5

printf "\n\n------------------------ BEGINNING GIT OPS ------------------------\n\n"

git add *
NOW=$(date)
git commit -m "AUTOMATICALLY GENERATED COMMIT FROM $USER ON $NOW"
git push origin gh-pages

printf "\n\n------------------------ COMPLETED UPLOAD TO gbdubs/student-union ------------------------\n\n"


printf "\n\n------------------------ Cloning into brandeisu.github.io ------------------------\n\n"

cp -r * ../brandeisu.github.io/
cd ../brandeisu.github.io
git add *
NOW=$(date) 
git commit -m "AUTOMATICALLY GENERATED COMMIT FROM $USER ON $NOW"
git push origin master

printf "\n\n------------------------ COMPLETED UPLOAD TO brandeisu.github.io ------------------------\n\n"

