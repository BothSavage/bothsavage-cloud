#!/bin/bash
# 定义目标目录路径
TARGET_DIR="/Users/y/IdeaProjects/cadillac-cloud"

# 定义源和目标字符串
SOURCE="bothSavage"
TARGET="cadillac"

# 修改文件夹和文件名
find "$TARGET_DIR" -depth -name "*$SOURCE*" -execdir bash -c 'mv -- "$1" "${1//'$SOURCE'/'$TARGET'}"' bash {} \;

# 修改所有文件的内容
LC_CTYPE=C && find "$TARGET_DIR" -type f -exec sh -c 'file -b --mime-type "$1" | grep -q text' sh {} \; -exec sed -i '' "s/$SOURCE/$TARGET/g"  {} +
