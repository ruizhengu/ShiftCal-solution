# ShiftCal - Practice Solution

In this document, the implementation process of the test case *setShifts* will be detailed, including the reasons for choosing a testing framework to perform the step or not.

## Environment

Emulator Device: Pixel 6

Android Version: API 26, Android 8.0

Application: *ShiftCal*

## Test Steps

### 1. Click the menu icon in the top-right corner.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/activity_calendar.xml*

```java
onView(withId(R.id.btnPopup)).perform(click());
```

**UIAutomator**

Creating UiDevice object.

```java
UiDevice device = UiDevice.getInstance(getInstrumentation());
```

**Please note**: It is recommended to add a waiting step between two actions from two different **windows**, including activities, menus, dialogs.

```java
device.findObject(By.res("de.nulide.shiftcal:id/btnPopup")).click();
device.wait(Until.findObject(By.text("Employers")), 5000);
```

### 2. Click "Employers".

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withText("Employers")).perform(click());
```

**UIAutomator**

```java
device.findObject(By.text("Employers")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabAddEmployer")), 5000);
```

### 3. Click the + icon on the bottom-right corner.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/activity_employers.xml*

```java
onView(withId(R.id.fabAddEmployer)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabAddEmployer")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/textView3")), 5000);
```

### 4. Enter "Sheffield" in the Name field (then press enter on the keyboard).

#### 4.1 Enter "Sheffield" in the Name field 

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_employer_creator.xml*

```java
onView(withId(R.id.scEditTextName)).perform(typeText("Sheffield"));
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/scEditTextName")).setText("Sheffield");
```

#### 4.2 Press enter

Option: UIAutomator

```java
device.pressEnter();
```

### 5. Click the ✓ icon.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/activity_employer_creator.xml*

```java
onView(withId(R.id.fabDoneEmployer)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabDoneEmployer")).click();
```

### 6. Press back to return to the homepage.

Options: Espresso & UIAutomator

**Espresso**

```java
pressBack();
```

**UIAutomator**

```java
device.pressBack();
```

### 7. Click the menu icon in the top-right corner.

Same as [Step 1](#1. Click the menu icon in the top-right corner.).

### 8. Click "Shifts".

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withText("Employers")).perform(click());
```

**UIAutomator**

```java
device.findObject(By.text("Shifts")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabAddShift")), 5000);
```

### 9. Click the + icon on the bottom-right corner.

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withId(R.id.fabAddShift)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabAddShift")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/scEditTextName")), 5000);
```

### 10. Enter "Weekday" in the Name field (then press enter on the keyboard).

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withId(R.id.scEditTextName)).perform(typeText("Weekday"));
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/scEditTextName")).setText("Weekday");
```

