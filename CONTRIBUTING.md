# Contributing

We're so excited you're interested in helping with LikeyListy! We are happy to help you get started, even if you don't have any previous open-source experience :blush:

## New to Open Source?

1. Take a look at [How to Contribute to an Open Source Project on GitHub](https://opensource.guide/how-to-contribute/#what-it-means-to-contribute )
2. Go through the LikeyListy [Code of Conduct](CODE_OF_CONDUCT.md)

## Where to ask Questions?

1. Check our [Github Issues](https://github.com/sharongreally/LikeyListy/issues) to see if someone has already answered your question. 
2. Refer to the LikeyListy [Issue Template](.github/issue_template.md) to submit a new issue. 

## Issues and Bugs

If you find a bug in the source code or a mistake in the documentation, you can help us by submitting an issue to the GitHub repository.

Even better: propose a fix with a pull request and link it to the issue!

For help with writing your issue refer to the LikeyListy [Issue Template](.github/issue_template.md)

## Development Setup  

### Prerequisites

- IDE: Android Studio 
- Code Style: [Google Java Style](https://google.github.io/styleguide/javaguide.html)
  In the latest Android Studio (as of this edit, 4.1.2) you can go to:
  File -> Settings -> Editor -> Code Style -> Java -> Scheme -> Settings Icon (Gear) -> Import Scheme...   and import the file GoogleStyle.xml there.

### Project Setup

1. [Fork](https://help.github.com/articles/fork-a-repo) the repository.
2. [Clone](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository#about-cloning-a-repository) it to your local machine.
3. Always base your contribution on the current main branch and write code on a branch in your fork.
4. Open the `<yourfork>/src/` directory in Android Studio.
5. Import the file GoogleStyle.xml into Android Studio.
6. You are now ready to contribute by modifying the code.

## Commits

1. Before making a commit, refer to the LikeyListy [Commit Template](COMMIT_TEMPLATE.md).
2. Commit and push until you are happy with your contribution.
3. Then make a [Pull Request](https://help.github.com/articles/using-pull-requests)

## Pull Request

1. Before submitting a pull request make sure all tests have passed.
2. Only change one specific thing per pull request. Pull Requests with multiple unrelated issues will not be accepted. Exceptions with documentation, spelling errors, etc.
3. Refer to the LikeyListy [Pull Request Template](.github/pull_request_template.md) before making a pull request.

## Keeping your repository fork up-to-date
1. Download Git
2. Clone your fork | git clone https://github.com/(Yourgithubname)/LikeyListy.git 
3. Add the Upstream URL | git remote add upstream https://github.com/SharonGreally/LikeyListy.git
4. Fetch the data | git fetch upstream
5. Updating the repo | git pull upstream master
