#!/bin/bash
# 定义目标目录路径
TARGET_DIR="/Users/y/IdeaProjects/cadillac-cloud"

# 修改文件夹和文件名
find "$TARGET_DIR" -depth -name '*cadillac*' -execdir bash -c 'mv -- "$1" "${1//cadillac/cadillac}"' bash {} \;

# 修改所有文件的内容
LC_CTYPE=C && find "$TARGET_DIR" -type f -exec sh -c 'file -b --mime-type "$1" | grep -q text' sh {} \; -exec sed -i '' 's/cadillac/cadillac/g'  {} +
