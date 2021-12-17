# 项目概况![image-20211217195108738](C:\Study\java\ProjectItem\bookManage\img\image-20211217195108738.png)

## 前端规范111

阅读bookManageFrontDev中readme.md

## 后端规范

阅读bookManageBackDev中readme.md

## git团队协作

创建一个git裸服务器 （git init --bare）

git clone git@github.com:MLYNDHG/bookManage.git

可以进行你的开发工作了

①提交：git add  你要提交的文件或者直接（git add --all）

①提交：git commit -m "此次提交的备注"

②推送版本至服务器 ：git remote 

②推送版本至服务器 ：git push origin main

③git pull从服务器下拉代码

（注：）每次提交之前一定、一定、一定要先git pull



### 合并pull request两种方式

1.fork到自仓库  克隆到自己电脑  每次提交进行new pull request，仓库所有者进行合并

在别人的提交请求的最后一个commit上，创建分支temp

切换到temp分支

然后在这个分支上git rebase mater

切换到master然后 git merge temp

2.在manage access 中添加团队成员  即可直接修改主仓库了  不需要fork与合并
