#!/bin/bash

dir="/Users/y/IdeaProjects/bothSavage-cloud"

find "$dir" -depth -type d -path "*/bothSavage4cloud/bothSavage" -exec bash -c 'mv "$0" "${0%/bothSavage4cloud/bothSavage}/bothSavage"' {} \;
