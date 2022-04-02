
Test Result Migration Java (TRM)
============
**Tool is designed for autocompleting Test Management Systems and Bug Tracking Systems by results of automation tests.**

*VERSION: Java + Maven + Jira + TestRail*

&nbsp;

# Table of contents

<br />

1. [Jira Adapter](#paragraph1)
   1. [Logics Description](#subparagraph11)
   2. [Creating test / demo Jira account (optional)](#subparagraph12)
   3. [Jira Settings](#subparagraph13)
   4. [Framework Settings](#subparagraph14)
   5. [Usage sample](#subparagraph15)

<br />

2. [TestRail Adapter](#paragraph2)
   1. [Logics Description](#subparagraph21)
   2. [Creating test / demo TestRail account (optional)](#subparagraph22)
   3. [TestRail Settings](#subparagraph23)
   4. [Framework Settings](#subparagraph24)
   5. [Usage sample](#subparagraph25)

&nbsp;

# 1. Jira Adapter <a name="paragraph1"></a>
## 1.1. Logics Description <a name="subparagraph11"></a>

In case test Failed in test automation FW, logic is:
~~~
if (test.status == Failed){
    if (!Jira has open issue with same summary){ 
        create issue in Jira with "To Do" status and with summary = PackageName.ClassName.TestName 
    } 
}
~~~


In case test Passed in test automation FW, logic is:
~~~
if (test.status == Passed){
    if (Jira has open issues with same summary){
        close ALL open issues in Jira with summary = PackageName.ClassName.TestName
    }
}
~~~
List of statuses that should be processed as "opened" can be tuned in Settings.




## 1.2. Creating test / demo Jira account (optional) <a name="subparagraph12"></a>
If you do not plan to check how framework works on test environment or if you are already
have test account in Jira, skip this step and proceed to next one - 1.3. Jira Settings

You can also try to use initial demo credentials if trial not expired yet
~~~
https://testsigman2.atlassian.net/
username: testsigman+2@gmail.com
pass for web login: ezHxlEAukYRKOYgl007c1E69
~~~

To create own test Jira Account open URL

https://www.atlassian.com/software/jira/free

Select email for admin's account

Create site

Select a project Template (for example, Scrum)

Select a project Type (for example, Team-managed Project)

Set Project Name and Project Key (for example, Test Project and TP)

In Backlog Page create any Issue (Story or Bug) and drug it to Sprint Area.
Now you can Start Sprint and to see issues on the Board.

Project is ready.



## 1.3. Jira Settings <a name="subparagraph13"></a>

### Create user for autotest reporting
Open Jira Settings and create special user for autotest reporting,
for example - autotests@yourdomain.com

This user will be used in "Created by" property of Bugs in Jira

### Create API token for user

Open link under new test user credentials

https://id.atlassian.com/manage-profile/security/api-tokens

and create API token for user

## 1.4. Framework Settings <a name="subparagraph14"></a>

### Modify pom.xml file

Add Maven dependencies for latest release versions of components (if project does not have them yet)


https://mvnrepository.com/artifact/org.testng/testng

https://mvnrepository.com/artifact/com.mashape.unirest/unirest-java


### Copy files to your Framework

Copy **JiraAdapter package** to

~~~
project\scr\test\java\JiraAdapter
~~~

Move then package to any other place you want by Refactoring option

### Modify Base Test class by add annotation for class

~~~
@Listeners(JiraAdapter.JiraListener.class)
public class TestSuite {
...
~~~
or if you already have listener
~~~
@Listeners({JiraAdapter.JiraListener, TestRailAdapter.TestRailListener.class})
public class TestSuite {
...
~~~

### Fill-in settings of your Jira Project
Setting are stored in class:
~~~
src\test\java\JiraAdapter\JiraSettings.class**
~~~

Open any Bug in Jira UI and it's URL looks like
~~~
https://{your-site-name}.atlassian.net/browse/{your-project-name}-505
~~~
Fill-in fields of **JiraSettings.class**
~~~
siteName = "your-site-name";
projectName = "your-project-name";
~~~

fill in username and token to fields:
~~~
userName = "autotests@yourdomain.com";
userToken = "your-jira-token-for-user";
~~~

Type the names of Jira statuses that you want to:
* tool accept as opened and do not open new issue with same summary if test Failed
* tool accept as opened and close all of them if test with same Summary is Passed

~~~
issueOpenStatus1 = "To Do";
issueOpenStatus2 = "In Progress";
issueOpenStatus3 = "Testing";
~~~

You need to specify transition ID for moving Jira issue to Status "Done" (it is specific to your Project's Workflow)

To get this Status ID, send GET API request

~~~
curl --request GET \
--url 'https://{your-domain}.atlassian.net/rest/api/3/issue/{projectName}-{any_BugID}/transitions
--user 'email@example.com:<api_token>' \
--header 'Accept: application/json'
~~~

In Response Body you will see smth like that
~~~
...
{
    "id": "31",
    "name": "Done",
    "to": {
...
~~~

In this example transitionID is 31,
so put this value into option field:

~~~
transitionIdDone = 31;
~~~

## 1.5. Usage sample <a name="subparagraph15"></a>

Usage is pretty simple.

In TestSuite class set value of Boolean **isTestShouldBePassed** according to the results you want to have

~~~
public class TestSuite {
public static boolean isTestShouldBePassed = false;
~~~

Then run one or all tests and get results in Jira web




# 2. TestRail Adapter <a name="paragraph2"></a>
## 2.1. Logics Description <a name="subparagraph21"></a>

After getting test result from autotest FW first step is to identify Test Run in TestRail, where result should be placed
~~~
if (TestRailSettings.isUseLastRun) {
   return getLastTestRunId();
} else {
   return TestRailSettings.testRunIdDefault;
}
~~~

The next step is to define TestCase ID for result publishing
~~~
if (test case with same name is found in TestRail){
   use this test case ID
}else{
   create new test case in TestRail
}
~~~

Finally, publishing test results to TestRail according to their status (Passed, Failed) with validation message and using attributes:
* Section (Suite) ID
* Test Case ID
* Test Run ID



## 2.2. Creating test / demo TestRail account (optional) <a name="subparagraph22"></a>

Open URL and create account

https://secure.gurock.com/customers/testrail/trial

Go to email inbox and confirm account by clicking the link

Open  home URL

https://testsigman1.testrail.io/index.php?/dashboard

and Add Project

Add one or few TestCase and TestRun:

* Menu - Dashboard - Test Cases - Add Test Case

* Menu - Dashboard - Test Runs - Add Test Run


## 2.3. TestRail Settings <a name="subparagraph23"></a>

* Menu - Administration - Site Settings - API

Select "Enable API" checkbox

Login to your TestRail with user name that you want to use for autotest result publishing

* Menu - {User Name} - My Settings - API KEYS - Add Key - Save Settings


## 2.4. Framework Settings <a name="subparagraph24"></a>

### Modify pom.xml file

Add Maven dependencies for latest release versions of components (if project does not have them yet)


https://mvnrepository.com/artifact/org.testng/testng

https://mvnrepository.com/artifact/com.mashape.unirest/unirest-java


### Copy files to your Framework

Copy **TestRailAdapter package** to

~~~
project\scr\test\java\TestRailAdapter
~~~

Move then package to any other place you want by Refactoring option

### Modify Base Test class by add annotation for class

~~~
@Listeners(TestRailAdapter.TestRailListener.class)
public class TestSuite {
...
~~~
or if you already have listener
~~~
@Listeners({JiraAdapter.JiraListener, TestRailAdapter.TestRailListener.class})
public class TestSuite {
...
~~~

### Fill-in settings of your TestRail Project
Setting are stored in class:
~~~
src\test\java\TestRailAdapter\TestRailSettings.class**
~~~
Open TestRail Dashboard web and it's URL looks like
~~~
https://{your-site-name}.testrail.io/index.php?/dashboard
~~~
Fill-in fields of **TestRailSettings.class**
~~~
siteName = "your-site-name";
~~~
If you have more than one Project in your account, specify the one you want to use
~~~
projectId = 1;
~~~
Set the UserName and Password (use API key here) for user that you want to publish testresults to TestRail
~~~
userName = "your-user-name";
userPassword = "your-api-keys";
~~~
Specify the text that should be added to TestRail for **Passed tests** as comment
~~~
testResultCommentForSuccessTest = "Automation test passed successfully";
~~~
Tune the logic according to your workflow.

Next parameter specifies the default TestRun where new results should be added (or overwritten if they are before)
~~~
testRunIdDefault = 3;
~~~
To get Run Id just open Dashboard - TestRuns and point mouse to TestRun
the URL of TestRun will be like this
~~~
https://{your-site-name}.testrail.io/index.php?/runs/view/5
~~~
so ID for this TestRun is 5

The next parameter allows you to use automatically Test Run that created last (has max ID)
~~~
isUseLastRun = true;
~~~
if this parameter is true, previous parameter is skipped in logic (testRunIdDefault)

The next parameter allows you to specify Test Suite (Test Section) where new Test Cases will
be created if there are no present yet in Project
~~~
testSectionIdDefault = 2;
~~~


## 2.5. Usage sample <a name="subparagraph25"></a>

Usage is pretty simple.

In TestSuite class set value of Boolean **isTestShouldBePassed** according to the results you want to have

~~~
public class TestSuite {
public static boolean isTestShouldBePassed = false;
~~~

Then run one or all tests and get results in TestRail web














