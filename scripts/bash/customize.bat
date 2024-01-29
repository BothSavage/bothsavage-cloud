# 定义目标目录路径
$TARGET_DIR = "C:\Users\y\IdeaProjects\cadillac-cloud"

# 定义源和目标字符串
$SOURCE = "bothSavage"
$TARGET = "cadillac"

# 修改文件夹和文件名
Get-ChildItem -Path $TARGET_DIR -Recurse -Filter "*$SOURCE*" |
    Rename-Item -NewName {$_.Name -replace $SOURCE,$TARGET}

# 修改所有文件的内容
Get-ChildItem -Path $TARGET_DIR -Recurse -File |
    ForEach-Object {
        (Get-Content $_.FullName) |
        ForEach-Object { $_ -replace $SOURCE, $TARGET } |
        Set-Content -Path $_.FullName
    }
