###Guide for project update using git
First time when you clone remote project to your local, please create your own branch. 
```sh
$ git checkout -b (your new branch name)
```
>Do command **$ git status** to check which branch are you on before editing your code.
>If not, type **$ git checkout (your branch name)** to swicth onto your branch.
>Make sure editing all code on your branch and do not make any modification on master branch.

#####These are the steps every time before pushing your project to remote Github repository.
If you have new added files remember do **$ git add.** firstly to add your new files to your branch. Otherwise just do followings directly.
```sh
(on your branch)
$ git commit -a '(your comments)'
$ git checkout master
(on master branch)
$ git pull
$ git checkout (your branch name)
(on your branch)        
$ git rebase master
$ git push origin (your branch name)
```

If you have conflict after $ git rebase master, please solve the conflict and then do
```sh
$ git rebase --continue
```
After push your branch to the remote Github repository, you will see your branch on remote repository.

So next, send pull request and admin will merge your branch to master after checking your code.
