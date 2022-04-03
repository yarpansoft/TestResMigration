
<br />
<br />
<br />

# 3. PractiTest Adapter <a name="paragraph3"></a>
## 3.1. Logics Description <a name="subparagraph31"></a>

After getting test result from autotest FW we need to define an Instance in PractiTest, 
where result should be placed. 

If (according settings) we should not create new Instance, we get last existing 
and use it for result placing.

If we should create new Instance, we need:
* Test ID
* TestSet ID

If Test with same Name found, tool use its Test ID

If not, new Test with "PackageName.ClassName.MethodName" created and tools use its ID 
~~~
GET TestSet 
check Settings if use DEFAULT, SUITENAME or NEW_timestamp]
    if use SUITENAME, search for Suite Name
        if exist, return ID of last
        if not exist, create with Test Suite Name and return ID
    if NEW_timestamp, create TestSet with TimeStamp and return ID
    if NONE ABOVE, use DEFAULT, check Settings and Return TestSet ID
~~~

Finally, publishing test results to PractiTest according to their status (Passed, Failed) 
with validation message and using attributes:
* Instance ID
* Test ID
* TestSet ID

---

## 3.2. Creating test / demo PractiTest account (optional) <a name="subparagraph32"></a>

*If you do not plan to check how tool works on test environment or if you are already have test account ,
you can skip this step and proceed to next one - 3.3. PractiTest Settings*

Open URL and create account

https://www.practitest.com/free-trial-b/

Go to email inbox and confirm account by clicking the link

Open  home URL



---


## 3.3. PractiTest Settings <a name="subparagraph33"></a>

ENABLING API IN PROJECT

Ensure that API enable for Project. Click Gear icon in right-upper corner:

* Automation Tab - Automation settings - API - Enable API TOKEN to act as a differnent user (user impersonation)

Impersonation is needed to create tests and override fields such as author_id, changed-by-id

---
GETTING API TOKEN 

Login as Account Owner

* Account Settings - Manage Users - Account Owners and Personal API Tokens

Select or Create user for Autotest. For selected user:

* Personal Api Token - Change - Enable

Login as Autotest user

* Project Settings - Personal - User Settings - Personal API Token - Generate

More info about API tokens 
https://www.practitest.com/help/account/account-api-tokens/

---

## 3.4. Framework Settings <a name="subparagraph34"></a>

### Modify pom.xml file

Add Maven dependencies for latest release versions of components (if project does not have them yet)

https://mvnrepository.com/artifact/com.mashape.unirest/unirest-java


### Copy files to your Framework

Copy **PractiTestAdapter package** to

~~~
project\scr\test\java\PractiTestAdapter
~~~

Move then package to any other place you want by Refactoring option

---

### Modify Base Test class 
Add Listener for class
~~~
@Listeners(PractiTestAdapter.PractiTestListener.class)
public class TestSuite {
...
~~~
or if you already have listener
~~~
@Listeners({JiraAdapter.JiraListener.class, PractiTestAdapter.PractiTestListener.class})
public class TestSuite {
...
~~~
Also add to your @BeforeSuite method of base class
~~~
    @BeforeSuite
    public void selectTestSet(){
        System.out.println("TestSetId = " + PractiTestSettings.getSetId());
        PractiTestAdapter.PractiTestActions practiTestActions = new PractiTestAdapter.PractiTestActions();
        practiTestActions.selectTestSetId(this.getClass().getCanonicalName());
    }
~~~
This code may be added to @BeforeTest method instead 
if logic og TestSet creation/usage is similar for all Suites according to youe workflow

---

### Fill-in settings of your PractiTest Project
Setting are stored in class:
~~~
src\test\java\PractiTestAdapter\PractiTestSettings.class**
~~~
Open PractiTest and get URL from address bar 

According to your location, base WWW may be various
~~~
https://www.practitest.com/
https://eu1-prod-api.practitest.app/
~~~
Fill-in fields of **PractiTestSettings.class**
~~~
baseURL = "your-baseWWW" + "/api/v2/projects/";
~~~

---

GET PROJECT ID

Click Gear icon in right-upper corner

Project Tab - Project ID 12345 (used with API)
~~~
projectId = 12345;
~~~
GET USER ID

To get list of Users with IDs, send simple CURL request
~~~
curl -H "Content-Type: application/json" \
https://api.practitest.com/api/v2/users.json?api_token=YOUR_TOKEN
~~~
Find user you want to use for Autotest and fill-in parameter with user's ID
~~~
userId = 28799;
~~~
More details on
https://www.practitest.com/api-v2/#get-all-users-in-your-account

Specify the text that should be added to PractiTest for **Passed tests** as comment
~~~
testResultCommentForSuccessTest = "Automation test passed successfully";
~~~

---

TUNE THE LOGIC ACCORDING TO YOUR WORKFLOW

~~~
isCreateNewInstanceForResults = true;
~~~
This parameter specifies if create new Instance of "Test-TestSet" for publishing test results or use old last one
~~~
isTestSetUse_SuiteName = true;
~~~
This parameter specifies if you want to use Java package names and Class names as 
TestSets names in PractiTest 
~~~
isTestSetUse_NewTimeStamp = false;
~~~
This parameter specifies if you want to use current time stamp as
TestSets names in PractiTest
~~~
testSetId_Default = 527617;
~~~
Here you can point specific TestSet ID where test results will be published 
if none of logic above do not suite your workflow.

TestSet ID you can get from address bar after opening this TestSet in browser.

---

## 3.5. Usage sample <a name="subparagraph35"></a>

Usage is pretty simple.

In TestSuite class set value of Boolean **isTestShouldBePassed** according to the results you want to have

~~~
public class TestSuite {
public static boolean isTestShouldBePassed = false;
~~~

Then run one or all tests and get results in PractiTest web

<br />
<br />
<br />