## Use the LikeyListy Commit Message Template to Write Better Commit Messages

Save the template file gitmessage.txt to your local machine and setup as the default commit template with the below command:
```
git config commit.template <gitmessage.txt file path>
```

If you saved it to your home folder, try:
```
git config commit.template ~/gitmessage.txt
```

When you run the ```git commit``` command, an editor opens containing something like this:

```
#Subject line
<type>: (If applied, this commit will...) <subject>

Type can be 
    feat     (new feature)
    bug      (bug fix)
    refactor (refactoring production code)
    style    (formatting, missing semi colons, etc; no code change)
    docs     (changes to documentation)
    test     (adding or refactoring tests; no production code change)

Remember
   - Use a Maximum Of 50 Characters
   - Blank line between title and body
   - Capitalize the subject line
   - Use the imperative mood in the subject line
   - Do not end the subject line with a period
   - Separate subject from body with a blank line
   - Lines starting with '#' will be ignored

#Body
Remember:
   - Use the body to explain what and why vs. how
   - Can use multiple lines with "-" for bullet points in body
   - Use as many lines as you like
   - Try To Limit Each Line to a Maximum Of 72 Characters

See links to relevant web pages, issue trackers, blog articles, etc.
See: [Example Page](https://example.com/)

List all co-authors, so version control systems can connect teams.
Co-authored-by: Name <name@example.com>

Explain why and how this change is happening: goals etc.?
Why:
How:

Tags suitable for searching, such as hashtags, keywords, etc.
Tags:

#Footer
   - Associated issues, PRs, etc.
   - Ex: Resolves Issue #207, see PR #15, etc.
```