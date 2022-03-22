
Test Result Migration Java (TRM)
============
**Tool is designed for autocompleting Test Management Systems and Bug Tracking Systems by results of automation tests.**

*VERSION: Java + Maven + Jira + TestRail*



TABLE OF CONTENTS
------------
1. Jira Adapter
   1.1. Logics Description
   1.2. Creating test/demo Jira account (optional)
   1.3. Jira Settings
   1.4. Framework Settings
   1.5. Usage sample



# Table of Contents
## 1.Jira Adapter
1.1. [Logics Description](#JiraAdapter)
1.2. [Creating test/demo Jira account (optional)](#example2)
1.3. [Jira Settings](#third-example)
1.4. [Framework Settings](#fourth-examplehttpwwwfourthexamplecom)
1.5. [Usage sample](#usagesample)




What is Lorem Ipsum?
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.





2. TestRail Adapter
   Logics Description
   Creating test/demo TestRail account (optional)
   TestRail Settings
   Framework Settings
   Usage sample




What is Lorem Ipsum?
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.






### 1.1. Logics Description
### 1.2. Creating test/demo Jira account (optional)
### 1.3. Jira Settings
### 1.4. Framework Settings
### 1.5. Usage sample




DESCRIPTION
============

1. In case test Failed in test automation FW, logic is:
~~~
if (test.status == Failed){
    if (!Jira has open issue with same summary){ 
        create issue in Jira with "To Do" status and with summary = PackageName.ClassName.TestName 
    } 
}
~~~


1. In case test Passed in test automation FW, logic is:
~~~
if (test.status == Passed){
    if (Jira has open issues with same summary){
        close ALL open issues in Jira with summary = PackageName.ClassName.TestName
    }
}
~~~
List of statuses that should be processed as "opened" can be tuned in Settings.

SETUP
============


### 1. Jira Settings

### 1.1. Create user for autotest reporting
Open Jira Settings and create special user for autotest reporting,
for example - autotests@yourdomain.com

This user will be used in "Created by" property of Bug in Jira

### 1.2. Create API token for user
Open link
>https://id.atlassian.com/manage-profile/security/api-tokens

and create API token for user

### 2. Settings in your test framework

### 2.1. Modify pom.xml file

>Add dependencies for latest release versions of components (if project does not have them yet)
>
>https://mvnrepository.com/artifact/org.testng/testng
>
>https://mvnrepository.com/artifact/com.mashape.unirest/unirest-java
>




### 2.2 Copy files to your Framework
Copy JiraAdapter package to
project\scr\test\java\JiraAdapter

Move then package to any other place you want by Refactoring option

### 2.3 Modify Base Test class by add annotation for class
~~~
@Listeners(JiraAdapter.JiraListener.class)
public class TestSuite {
~~~
or if you already have listener
~~~
@Listeners({JiraAdapter.JiraListener.class, AnyOtherListener.class}) 
public class TestSuite {
~~~
### 2.4 Fill-in settings of your Jira Project
Setting are stored in class:

**src\test\java\JiraAdapter\JiraSettings.class**


Open any Bug in Jira UI and it's URL looks like

>//https:{your-site-name}.atlassian.net/browse/{your-project-name}-505

Fill-in fields of **JiraSettings.class**

>siteName = "your-site-name";
>
>projectName = "your-project-name";


fill in username and token to fields:
>userName = "autotests@yourdomain.com";
>
>userToken = "your-jira-token-for-user";

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
--url 'https://your-domain.atlassian.net/rest/api/3/issue/{projectName}-{any_BugID}/transitions
--user 'email@example.com:<api_token>' \
--header 'Accept: application/json'
~~~

*(example - https://testresmig.atlassian.net/rest/api/3/issue/TRM-28/transitions)*

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

>transitionIdDone = 31;


### 3. Run your tests
and enjoy


