#!/bin/bash

dir="/Users/y/IdeaProjects/bothsavage-cloud"

find "$dir" -depth -type d -path "*/bothsavage4cloud/bothsavage" -exec bash -c 'mv "$0" "${0%/bothsavage4cloud/bothsavage}/bothsavage"' {} \;
